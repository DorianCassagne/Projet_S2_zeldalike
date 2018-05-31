package model.gameMap;

import java.util.ArrayList;

import java.util.HashMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.Movable;
import model.character.PendingMovable;
import model.character.attack.Attack;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.NewMovable;
import model.gameMap.cell.Cell;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class GameMap {
	public final static int LINELENGTH = 8;
	public final static int CATEGORYLENGTH = 100 * LINELENGTH ;
	public final static int STARTCHARINDEX = 0 * LINELENGTH;
	public final static int STARTITEMINDEX = 100 * LINELENGTH;
	public final static int STARTATTACKINDEX = 200 * LINELENGTH;
	public final static int STARTWALKABLEINDEX = 300 * LINELENGTH;
	public final static int STARTNONWALKABLEINDEX = 400 * LINELENGTH;
	public final static int CHANGEPARTWITHCELLID = 9;
	public final static int STEP = 1;
	private final static int ITEMVALUEINDEX = 1;
	
	private GameCharacter hero;
	private static int movableId;
	private final IntegerProperty changeProperty;
	private final IntegerProperty safeChangeProperty;
	private Cell[] cells ;
	private HashMap<Movable,Integer> movableList;
	private ArrayList<NewMovable> addedCharacter;//Caract�res qui sont ajout�s r�cement mais pas encore r�cup�r�s
	private ArrayList<Integer> removedMovable;//Caract�re qui seront retir� au prochain tour
	private ArrayList<PendingMovable> pendingMovable;//Caract�res qui vont �tre ajout� au prochain tour
	private ArrayList<Integer> triggeredCells;
	static {
		movableId = 0;
	}
	
	//Cr�e une map en se r�f�rant � un fichier csv qui initilialise les fond des cases
	//Dans ce cas l� il n'est autoris� qu'un layer, d�clenche une exception si le fichier n'est pas valid
	public GameMap(String ... mapPath) {
		int[][] values = MapReader.readAndConvertMapFile(mapPath);
		this.changeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty.bind(this.changeProperty);
		initialiseCells(values);
		this.addedCharacter = new ArrayList<NewMovable>();
		this.movableList = new HashMap<Movable,Integer>();
		this.removedMovable = new ArrayList<Integer>();
		this.pendingMovable = new ArrayList<PendingMovable>();
		this.triggeredCells = new ArrayList<Integer>();

	}
	
	
	/*
	 * Change la case du personnage, la case de d�part et d'arriv�s doivent �tre pass�es en param�tre pour r�duire le temps de calcul
	 * Renvoie vrai si le changement a été bien effectu�, cela veut dire que la case destination accepte le personnage en movement
	 * Renvoie faux si la case d'arrivée ou de départ n'est pas une case disponible ou incorrecte.
	*/
	
	public boolean changeCell(GameCharacter character,int currentRow,int currentColumn,int endRow,int endColumn) {
		int endCellId = convertToCellId(endRow,endColumn);
		boolean changed = false;
		if(isInMap(endRow,endColumn) ) {
			changed = this.cells[endCellId].isWalkable();
			if(changed ) {
				int startCellId = convertToCellId(currentRow,currentColumn);
				if(isInMap(currentRow,currentColumn)) {
					if(GameCharacter.getType(character) == GameCharacter.HEROTYPE)
						notifyCells(endCellId);
					this.cells[startCellId].removeMovable();
					this.cells[endCellId].addMovable(character);
					character.setCellId(endRow, endColumn);
				}
				else
					changed = false;
			}
		}
		return changed;	
	}
	
		
	/*
	 * D'abord unnotify tous les ennemis qui �tait notifi�s
	 * Notifie tous les ennemis sur la case d'une port�e x 
	 * 
	 */
	public void notifyCells(int cellId) {
		this.unnotify();
		int portee = 1;
		Movement movement;
		int attackCellId;
		for(int i = 0 ; i < portee ; i++) {
			for(int j = 0 ; j < Movement.values().length ;j++) {
				movement = Movement.values()[j]; 
				attackCellId = cellId + movement.getHorizontalIncrement();
				attackCellId += movement.getVerticalIncrement() * MapReader.MAPLENGTH;
				this.cells[attackCellId].notifyEnemy(Movement.values()[(j+2)%4]);
				this.triggeredCells.add(attackCellId);
			}
		}
	}
	
	private void unnotify() {
		for(Integer cellId : this.triggeredCells) {
			this.cells[cellId].unnotifyEnemy();
		}
	}
	
	//Renvoie la liste des movements effectu�s pendant un tour
	synchronized public Move[] turn() {
		updateMovableList();
		ArrayList<Move> moves = executeTurn();
		Move[] movesArray = new Move[moves.size()];
		movesArray = moves.toArray(movesArray);
		return movesArray;
	}
	
	//Renvoie la liste des nouveaux caract�res introduit dans le jeu
	public NewMovable[] getNewCharList() {
		NewMovable[] newChars = new NewMovable[this.addedCharacter.size()];
		newChars = this.addedCharacter.toArray(newChars);
		this.addedCharacter.clear();
		return newChars;
	}
	
	
	public int[] getRemovedCharacter() {
		int[] removedCharacter = new int[this.removedMovable.size()];
		for(int i = 0 ; i < removedCharacter.length;i++) {
			removedCharacter[i] = this.removedMovable.get(i);		
		}
		this.removedMovable.clear();
		return removedCharacter;
	}

	
	/*
	* Ajoute un caract�re � la Map,le caract�re s'ajoute � la map en s'ajoutant � une case
	* Les caract�res � ajouter sont ajouter dans une liste qui doit �tre l� au moment du tour
	* Renvoie faux si la case d'arriv�e n'est pas correcte ou non disponible
	*/
	public boolean addCharacter(GameCharacter movable,int row,int column) {
		
		boolean correctlyPlaced = false;
		if(isInMap(row,column)) {
			int cellId = convertToCellId(row, column);
			correctlyPlaced = this.cells[cellId].addMovable(movable);
			if(correctlyPlaced) {
				addMovable(movable,cellId);
			}
		}
		return correctlyPlaced;
	}
	
	
	
	/*
	 * Ajoute une attaque � la map en indiquant sa case de d�part
	 * D�clenche une erreur si l'attaque n'a pas p� �tre plac� sur la case, car l'identifiant de case est incorrecte
	 */
	
	public void  addAttack(Attack attack,int row,int column) {
		if(isInMap(row,column)) {
			int cellId = convertToCellId(row,column);
			this.addMovable(attack,cellId);
		}
		else {
			throw new IllegalArgumentException("ENDCELL NOT FOUND");
		}
	}
	
	
	/*
	 * Renvoie une valeur qui repr�sente l'effet emis par l'attaque dans une case donn�e
	 */
	public byte playAttack(Attack attack,int row,int column) {
		
		//TODO
		
		byte value = Attack.NOTPLAYED;
		if(isInMap(row,column)) {
			int endCellId = convertToCellId(row, column);
			value = this.cells[endCellId].attack(attack);
		}else {
			System.out.println("The attack will be destroyed");
			this.delAttack(attack);
		}
		return value;
	}
	
	/*
	 * Retire un caract�re de la liste courante dans la map
	 */
	public void delCharacter(GameCharacter character,int row,int column) {
		if(this.deleteMovableFromList(character)) {
			int cellId = convertToCellId(row, column);
			this.cells[cellId].removeMovable();
		}
	}
	
	public void delAttack(Attack attack) {
		this.deleteMovableFromList(attack);
	}
	
	private boolean deleteMovableFromList(Movable movable) {
		boolean deleted = this.movableList.containsKey(movable);
		if(deleted) {
			this.pendingMovable.add(new PendingMovable(movable));
		}
		return deleted;
	}
	
	
	/*
	 * Renvoie l'indice du fond
	 */
	public Integer[] getLayerForCell(int cellId) {
		return this.cells[cellId].getCellBackgroundLayer();
	
	}

	
	/*
	 * Cette m�thode ajoute un déplaçable � la liste des d�pla�able en lui attribuant un identifiant unique
	 */
	private void addMovable(Movable movable,int cellId) {
		this.pendingMovable.add(new PendingMovable(movable,cellId));
	}
	
	
	/*
	 * Identifie un movableEnAttente et applique les actions suivant les conditions suivants : 
	 * -Si Le d�pla�able est d�j� sur la map, on l'enl�ve
	 * -Si le d�pla�able n'est pas d�j� dans la map, on l'ajoute
	 */
	private void identifieMovable(PendingMovable pending) {
		Movable currentMovable = pending.getMovable();
		if(this.movableList.containsKey(currentMovable)){
			this.removedMovable.add(this.movableList.get(currentMovable));
			this.movableList.remove(currentMovable);
		}
		else {
			this.movableList.put(pending.getMovable(), movableId);
			this.addedCharacter.add(new NewMovable(movableId, pending.getCellId(),pending.getMovable().getDefaultImage()));
			movableId++;
		}
	}

	
	/*
	 * Fait jouer un tour � tous les d�pla�able sur la map et renvoie la liste de leurs actions
	 */
	private ArrayList<Move> executeTurn() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		for(Movable movable : this.movableList.keySet()) {
			move = movable.turn();
			if(move != null) {
				move.setMovableId(this.movableList.get(movable));
				moves.add(move);
			}
		}
		return moves;
	}
	
	
	
	//Met � jour la liste des d�pla�able en les ajoutants � la hashmap des d�pla�able courrants
	private void updateMovableList() {
		for(PendingMovable movable : this.pendingMovable) {
			identifieMovable(movable);
		}
		this.pendingMovable.clear();
	}

	
	
	private int[] getItemValue(int[][] values) {
		
		return values[ITEMVALUEINDEX - 1];
		
	}
	
	private int[][] getBackgroundValues(int[][] values) {
		int length = MapReader.MAPLENGTH * MapReader.MAPLENGTH;
		int[][] backValues = new int[length][values.length - 1];
		
		for(int j = 0 ; j < length ; j++) {
			for(int i = ITEMVALUEINDEX ; i < values.length ;i++) {
				backValues[j][i - ITEMVALUEINDEX] = values[i][j];
			}
		}
		
		return backValues;
	}
	
	//Initialise l'ensemble des celluls de la map
	
	private void initialiseCells(int[][] values) {

		int length = MapReader.MAPLENGTH*MapReader.MAPLENGTH;
		this.cells = new Cell[length];
		int[][] backValues = this.getBackgroundValues(values);
		int[] itemValue = this.getItemValue(values);
		
		for(int i = 0 ; i < length ;i++) {
			final int cellId = i;
			System.out.println(cellId);
			cells[cellId] = new Cell(backValues[i],itemValue[i]);			
			cells[cellId].changeProperty().addListener(
					(obs,oldValue,newValue)->{
						this.changeProperty.set(newValue.intValue()<< CHANGEPARTWITHCELLID + cellId);
					}
			);

		}
	}
	
	public boolean isWalkableAt(int id) {
		return this.cells[id].isWalkable();
	}
	
	public static int convertToCellId(int row,int column) {
		return row * MapReader.MAPLENGTH + column;
	}
	
	public static int convertToRow(int id) {
		return id/MapReader.MAPLENGTH;
	}
	
	public static int convertToColomn(int id) {
		return id%MapReader.MAPLENGTH;
	}
	
	/*
	 * V�rifie si les param�tres en entr�s d�signe une case avaible sur la map
	 */
	public static boolean isInMap(int row,int column) {
		return row >= 0 && row < MapReader.MAPLENGTH && column >= 0 && row < MapReader.MAPLENGTH;
	}
}

