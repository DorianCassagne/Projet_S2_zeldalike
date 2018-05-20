package model.gameMap;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.gameMap.cell.Cell;

public class GameMap {
	public final static int LINELENGTH = 8;
	public final static int CATEGORYLENGTH = 100 * LINELENGTH ;
	public final static int STARTCHARINDEX = 0 * LINELENGTH;
	public final static int STARTITEMINDEX = 100 * LINELENGTH;
	public final static int STARTATTACKINDEX = 200 * LINELENGTH;
	public final static int STARTWALKABLEINDEX = 300 * LINELENGTH;
	public final static int STARTNONWALKABLEINDEX = 400 * LINELENGTH;

	
	private IntegerProperty changeProperty;
	private Cell[] cells ;
	public GameMap() {
		this.changeProperty = new SimpleIntegerProperty();
	
	}
	
	public void initialiseCell() {
		this.cells = new Cell[MapReader.MAPLENGTH];
		for(int row = 0;row < MapReader.MAPLENGTH;row++) {
			for(int column = 0 ; column < MapReader.MAPLENGTH; column++) {
				this.cells[row * MapReader.MAPLENGTH + column ] = new Cell(1);
				this.cells[row * MapReader.MAPLENGTH + column ].changeProperty().addListener(
						(obs,oldValue,newValue)->{
							changeProperty.set(15);
						}
						);
				
			}
		}
	}
}
