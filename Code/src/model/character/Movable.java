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
	private int moveCoefficient;
	
	
	public Movable(GameMap map,int cycle, int row, int column,int moveCoefficient) {
		if(cycle < 0 && moveCoefficient > 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		this.moveCoefficient = moveCoefficient;
		this.cycle = cycle;
		this.tour = 0;
		this.row = row;
		this.column = column;
		this.map = map;
	}
	
	protected final int getRow() {
		return this.row;
	}
	
	protected final int getColumn() {
		return this.column;
	}
	
	protected final GameMap getMyMap() {
		return this.map;
	}
	
	public final void setCellId(int row,int column) {
		this.row = row;
		this.column = column;
	}	
	
	
	private void oneTurn() {
		if(!this.isAlive())
			this.removeCharacter();
		else if(this.tour != cycle)
			this.tour++;
	}
	
	
	private boolean canAct() {
		this.oneTurn();
		boolean canAct = false;
		if(this.tour == cycle) {
			this.tour = 0;
			canAct = true;
		}
		return canAct;
	}
	
	
	public final Move turn() {
		Move move = null ;
		if(this.canAct()) {
			move = this.act();
		}
		return move;
	}
	
	protected  final int getMoveCycle() {
		return this.cycle / this.moveCoefficient;
	}
	
	
	protected abstract void removeCharacter();
	
	
	public abstract boolean isAlive();
	public abstract int getDefaultImage() ;
	public abstract Move act();
	
}
