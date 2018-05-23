package model.gameMap;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.Movable;
import model.character.attack.Attack;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.NewCharacter;
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
	private ArrayList<NewCharacter> addedCharacter;
	
	static {
		movableId = 0;
	}
	
	//Crée une map en se référant à un fichier csv qui initilialise les fond des cases
	//Dans ce cas là il n'est autorisé qu'un layer
	public GameMap(String mapPath) {
		int[] values = MapReader.readAndConvertMapFile(mapPath);
		this.changeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty.bind(this.changeProperty);
		initialiseCells(values);
		this.addedCharacter = new ArrayList<NewCharacter>();
		this.movableList = new HashMap<Movable,Integer>();
	}
	
	
	public boolean changeCell(GameCharacter character,int currentRow,int currentColumn,int endRow,int endColumn) {
		int endCellId = convertToCellId(endRow,endColumn);
		boolean changed = this.cells[endCellId].isWalkable();
		if(changed) {
			int startCellId = convertToCellId(currentRow,currentColumn);
			this.cells[startCellId].removeMovable();
			this.cells[endCellId].addMovable(character);
			character.setCellId(endRow, endColumn);
			System.out.println("success Move " + this.cells[endCellId].getBackgroundRepresentation());
		}
		
		return changed;
		
	}
	
	
	//Renvoie la liste des movements effectués pendant un tour
	public Move[] turn() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		for(Movable movable : this.movableList.keySet()) {
			move = movable.turn();
			if(move != null) {
				move.setMovableId(this.movableList.get(movable));
				moves.add(move);
			}
		}
		Move[] movesArray = new Move[moves.size()];
		movesArray = moves.toArray(movesArray);
		return movesArray;
	}
	
	//Renvoie la liste des nouveaux caractères introduit dans le jeu
	public NewCharacter[] getNewCharList() {
		NewCharacter[] newChars = new NewCharacter[this.addedCharacter.size()];
		newChars = this.addedCharacter.toArray(newChars);
		this.addedCharacter.clear();
		return newChars;
	}
	
	
	//Ajout un caractère à la map en le plaçant sur la case reçue en paramètre
	//Renvoie vrai si l'ajout est un succés, sinon renvoie faux
	public boolean addCharacter(GameCharacter movable,int row,int column) {
		int cellId = row*MapReader.MAPLENGTH + column;
		boolean correctlyPlaced = this.cells[cellId].addMovable(movable);
		if(correctlyPlaced) {
			this.movableList.put(movable, movableId);
			this.addedCharacter.add(new NewCharacter(movableId,cellId,movable.getDefaultImage()));
			movableId++;
		}
		return correctlyPlaced;
	}
	
	//Enleve une attaque de la map 
	//TODO à discuter
	public void delAttack(Attack attack) {
		this.movableList.remove(attack);
	}

	
	
	//Renvoie l'indice du fond
	public int getBackgroundImage(int cellId) {
		return this.cells[cellId].getBackgroundRepresentation();
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
	
	public static int convertToCellId(int row,int column) {
		return row * MapReader.MAPLENGTH + column;
	}
}
