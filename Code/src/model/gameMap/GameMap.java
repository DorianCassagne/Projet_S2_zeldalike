package model.gameMap;

import java.util.HashMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.Movable;
import model.gameMap.cell.Cell;

public class GameMap {
	public final static int LINELENGTH = 8;
	public final static int CATEGORYLENGTH = 100 * LINELENGTH ;
	public final static int STARTCHARINDEX = 0 * LINELENGTH;
	public final static int STARTITEMINDEX = 100 * LINELENGTH;
	public final static int STARTATTACKINDEX = 200 * LINELENGTH;
	public final static int STARTWALKABLEINDEX = 300 * LINELENGTH;
	public final static int STARTNONWALKABLEINDEX = 400 * LINELENGTH;
	public final static int CHANGEPARTWITHCELLID = 9;
	
	private IntegerProperty changeProperty;
	private Cell[] cells ;
	private HashMap<Integer,Movable> movableList;
	
	public GameMap(String mapPath) {
		int[] values = MapReader.readAndConvertMapFile(mapPath);
		initialiseCells(values);
	
	}
	
	public Move[] turn() {
		
	}
	
	
	private void initialiseCells(int[] values) {
		this.cells = new Cell[MapReader.MAPLENGTH*MapReader.MAPLENGTH];
		Cell currentCell;

		for(int i = 0 ; i < values.length ;i++) {
			final int cellId = i;
			currentCell = cells[cellId];
			currentCell = new Cell(values[cellId]);
			currentCell.changeProperty().addListener(
					(obs,oldValue,newValue)->{
						this.changeProperty.set(newValue.intValue()<< CHANGEPARTWITHCELLID + cellId);
					}
			);

		}
	}
}
