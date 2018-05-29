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
	
	private static int movableId;
	private final IntegerProperty changeProperty;
	private final IntegerProperty safeChangeProperty;
	private Cell[] cells ;
	private HashMap<Movable,Integer> movableList;
	private ArrayList<NewMovable> addedCharacter;//Caractères qui sont ajoutés récement mais pas encore récupérés
	private ArrayList<Integer> removedMovable;//Caractère qui seront retiré au prochain tour
	private ArrayList<PendingMovable> pendingMovable;//Caractères qui vont être ajouté au prochain tour
	private ArrayList<Integer> triggeredCells;
	
	static {
		movableId = 0;
	}
	
	//Crée une map en se référant à un fichier csv qui initilialise les fond des cases
	//Dans ce cas là il n'est autorisé qu'un layer, déclenche une exception si le fichier n'est pas valid
	public GameMap(String mapPath) {
		int[] values = MapReader.readAndConvertMapFile(mapPath);
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
	 * Change la case du personnage, la case de départ et d'arrivés doivent être passées en paramètre pour réduire le temps de calcul
	 * Renvoie vrai si le changement a été bien effectué, cela veut dire que la case destination accepte le personnage en movement
	 * Renvoie faux si la case d'arrivé ou de départ n'est pas une case disponible ou incorrecte.
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
	 * D'abord unnotify tous les ennemis qui était notifiés
	 * Notifie tous les ennemis sur la case d'une portée x 
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
	
	//Renvoie la liste des movements effectués pendant un tour
	synchronized public Move[] turn() {
		System.out.println("True");
		updateMovableList();
		ArrayList<Move> moves = executeTurn();
		Move[] movesArray = new Move[moves.size()];
		movesArray = moves.toArray(movesArray);
		return movesArray;
	}
	
	//Renvoie la liste des nouveaux caractères introduit dans le jeu
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
	* Ajoute un caractère à la Map,le caractère s'ajoute à la map en s'ajoutant à une case
	* Les caractères à ajouter sont ajouter dans une liste qui doit être lû au moment du tour
	* Renvoie faux si la case d'arrivée n'est pas correcte ou non disponible
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
	 * Ajoute une attaque à la map en indiquant sa case de départ
	 * Déclenche une erreur si l'attaque n'a pas pû être placé sur la case, car l'identifiant de case est incorrecte
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
	 * Renvoie une valeur qui représente l'effet emis par l'attaque dans une case donnée
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
	 * Retire un caractère de la liste courante dans la map
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
	public int getBackgroundImage(int cellId) {
		return this.cells[cellId].getBackgroundRepresentation();
	}

	
	/*
	 * Cette méthode ajoute un déplaçable à la liste des déplaçable en lui attribuant un identifiant unique
	 */
	private void addMovable(Movable movable,int cellId) {
		this.pendingMovable.add(new PendingMovable(movable,cellId));
	}
	
	
	/*
	 * Identifie un movableEnAttente et applique les actions suivant les conditions suivants : 
	 * -Si Le déplaçable est déjà sur la map, on l'enlève
	 * -Si le déplaçable n'est pas déjà dans la map, on l'ajoute
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
	 * Fait jouer un tour à tous les déplaçable sur la map et renvoie la liste de leurs actions
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
	
	
	
	//Met à jour la liste des déplaçable en les ajoutants à la hashmap des déplaçable courrants
	private void updateMovableList() {
		for(PendingMovable movable : this.pendingMovable) {
			identifieMovable(movable);
		}
		this.pendingMovable.clear();
	}

	
	
	//Initialise l'ensemble des celluls de la map
	private void initialiseCells(int[] values) {
		this.cells = new Cell[MapReader.MAPLENGTH*MapReader.MAPLENGTH];

		for(int i = 0 ; i < values.length ;i++) {
			final int cellId = i;
			cells[cellId] = new Cell(values[cellId]);
			cells[cellId].changeProperty().addListener(
					(obs,oldValue,newValue)->{
						this.changeProperty.set(newValue.intValue()<< CHANGEPARTWITHCELLID + cellId);
					}
			);

		}
	}
	
	public boolean containHero(int id) {
		return cells[id].containsHero();
	}
	
	public boolean isWalkableAt(int id) {
		return this.cells[id].isWalkable();
	}
	
	public static int convertToCellId(int row,int column) {
		return row * MapReader.MAPLENGTH + column;
	}
	
	/*
	 * Vérifie si les paramètres en entrés désigne une case avaible sur la map
	 */
	public static boolean isInMap(int row,int column) {
		return row >= 0 && row < MapReader.MAPLENGTH && column >= 0 && row < MapReader.MAPLENGTH;
	}
}

