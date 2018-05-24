package model.character;

public class PendingMovable {
	private Movable movable;
	private int cellId;
	public PendingMovable(Movable movable,int cellId) {
		this.movable = movable;
		this.cellId = cellId;
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
	
}
