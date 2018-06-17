package model.character;
/*
 * Classe des PendingMovable
 * 
 */
import javafx.beans.property.IntegerProperty;

public class PendingMovable {
	
	private Movable movable;
	private int cellId;
	private IntegerProperty imageChangeProperty;
	
	public PendingMovable(Movable movable,int cellId,IntegerProperty imageChangeProperty) {
		this.movable = movable;
		this.cellId = cellId;
		this.imageChangeProperty = imageChangeProperty;
	}
	
	public PendingMovable(Movable movable) {
		this.movable = movable;
	}
	
	public int getCellId() {
		return this.cellId;
	}
	
	public Movable getMovable() {
		return this.movable;
	}
	
	public IntegerProperty getImageProperty() {
		return this.imageChangeProperty;
	}
	
}
