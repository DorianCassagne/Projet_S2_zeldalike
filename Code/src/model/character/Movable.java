package model.character;


import model.gameMap.GameMap;
import model.gameMap.move.Move;

public abstract class Movable {

	public final static int DEFAULTIMAGEINDEX = 0;
	public final static int TOPIMAGEINDEX = 0;
	public final static int LEFTIMAGEINDEX = 3;
	public final static int RIGHTIMAGEINDEX = 1;
	public final static int BOTTOMIMAGEINDEX = 2;	
	
	private int row;
	private int column;
	private GameMap map;
	private int cycle;
	private int tour ;
	
	
	
	public Movable(GameMap map,int cycle, int row, int column) {
		if(cycle < 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		this.cycle = cycle;
		this.tour = 0;
		this.row = row;
		this.column = column;
		this.map = map;
	}
	
	protected int getRow() {
		return this.row;
	}
	
	protected int getColumn() {
		return this.column;
	}
	
	protected GameMap getMyMap() {
		return this.map;
	}
	
	public void setCellId(int row,int column) {
		this.row = row;
		this.column = column;
	}	
	
	
	public void oneTurn() {
		if(!this.isAlive())
			this.getMyMap().delMovable(this, this.row, this.column);
		if(this.tour != cycle)
			this.tour++;
	}
	
	protected int getCycle() {
		return this.cycle;
	}
	
	protected boolean canAct() {
		boolean canAct = false;
		if(this.tour == cycle) {
			this.tour = 0;
			canAct = true;
		}
		return canAct;
	}
	
	
	
	public abstract boolean isAlive();
	public abstract Move turn();
	public abstract int getDefaultImage() ;
	
	
}
