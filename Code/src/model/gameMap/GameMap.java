package model.gameMap;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.Movable;
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
	private ArrayList<NewMovable> addedCharacter;
	private ArrayList<Movable> removedMovable;
	
	
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
		this.addedCharacter = new ArrayList<NewMovable>();
		this.movableList = new HashMap<Movable,Integer>();
		this.removedMovable = new ArrayList<Movable>();
	}
	
	
	public boolean changeCell(GameCharacter character,int currentRow,int currentColumn,int endRow,int endColumn) {
		int endCellId = convertToCellId(endRow,endColumn);
		boolean changed = this.cells[endCellId].isWalkable();
		if(changed) {
			int startCellId = convertToCellId(currentRow,currentColumn);
			this.cells[startCellId].removeMovable();
			this.cells[endCellId].addMovable(character);
			character.setCellId(endRow, endColumn);
		}
		
		return changed;
		
	}
	
	
	//Renvoie la liste des movements effectués pendant un tour
	public Move[] turn() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Move move;
		Iterator<Movable> iterator = this.movableList.keySet().iterator();
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
	public NewMovable[] getNewCharList() {
		NewMovable[] newChars = new NewMovable[this.addedCharacter.size()];
		newChars = this.addedCharacter.toArray(newChars);
		this.addedCharacter.clear();
		return newChars;
	}
	
	
	public int[] getRemovedCharacter() {
		int[] removedCharacter = new int[this.removedMovable.size()];
		for(int i = 0 ; i < this.removedMovable.size();i++) {
			removedCharacter[i] = this.movableList.get(this.removedMovable.get(i));		
			this.movableList.remove(this.removedMovable.get(i));
		}
		
		this.removedMovable.clear();
		return removedCharacter;
	}

	
	
	//Ajout un caractère à la map en le plaçant sur la case reçue en paramètre
	//Renvoie vrai si l'ajout est un succés, sinon renvoie faux
	public boolean addCharacter(GameCharacter movable,int row,int column) {
		int cellId = row*MapReader.MAPLENGTH + column;
		boolean correctlyPlaced = this.cells[cellId].addMovable(movable);
		if(correctlyPlaced) {
			addMovable(movable,cellId);
		}
		return correctlyPlaced;
	}
	
	
	
	
	public void  addAttack(Attack attack,int row,int column) {
		this.addMovable(attack,convertToCellId(row, column));
		
	}
	
	public void playAttack(Attack attack,int endCell) {
		byte value = this.cells[endCell].attack(attack);
		System.out.println(value);
	}
	
	
	public void delMovable(Movable movable,int row,int column) {
		if(this.movableList.containsKey(movable)) {
			Integer id = this.movableList.get(movable);
			this.removedMovable.add(movable);
		}
		
	}
	
	//Renvoie l'indice du fond
	public int getBackgroundImage(int cellId) {
		return this.cells[cellId].getBackgroundRepresentation();
	}

	
	private void addMovable(Movable movable,int cellId) {
		this.movableList.put(movable,movableId);	 
		this.addedCharacter.add(new NewMovable(movableId,cellId,movable.getDefaultImage()));
		movableId++;
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
