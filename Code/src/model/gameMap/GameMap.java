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
	
	public GameMap(String mapPath) {
		int[] values = MapReader.readAndConvertMapFile(mapPath);
		this.changeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty.bind(this.changeProperty);
		initialiseCells(values);
		this.addedCharacter = new ArrayList<NewCharacter>();
	}
	
	public Move[] turn() {
		ArrayList<Move> moves = new ArrayList<Move>();
		for(Movable movable : this.movableList.keySet()) {
			moves.add(movable.turn());
		}
		Move[] movesArray = new Move[moves.size()];
		movesArray = moves.toArray(movesArray);
		return movesArray;
	}
	
	public NewCharacter[] getNewCharList() {
		Integer[] newChars = new Integer[this.addedCharacter.size()];
		newChars = this.addedCharacter.toArray(newChars);
		this.addedCharacter.clear();
		return newChars;
	}
	
	
	
	public boolean addCharacter(GameCharacter movable,int row,int column) {
		int cellId = row*MapReader.MAPLENGTH + column;
		boolean correctlyPlaced = this.cells[cellId].addMovable(movable);
		if(correctlyPlaced) {
			this.movableList.put(movable, movableId);
			this.addedCharacter.add(new NewCharacter(movableId,cellId,movable.get));
			movableId++;
		}
		return correctlyPlaced;
	}
	
	
	public void delAttack(Attack attack) {
		this.movableList.remove(attack);
	}
	
	
	public int getCharacterPosition(Integer charKey) {
		return this.getMovablePosition(this.findCharacter(charKey));
	}
	
	private Movable findCharacter(Integer charKey) {
		for(Movable movable : movableList.keySet()) {
			if(this.movableList.get(movable) == charKey) {
				return movable;
			}
		}
		return null;
	}
	
	private int getMovablePosition(Movable movable) {
		int cellId = -1 ;
		int counter = 0;
		while(cellId == -1 && counter < this.cells.length) {
			if(this.cells[counter].contains(movable)) {
				cellId = counter; 
			}
		}
		return cellId;
	}
	
	public int getBackgroundImage(int cellId) {
		return this.cells[cellId].getBackgroundRepresentation();
	}
	
	
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
}
