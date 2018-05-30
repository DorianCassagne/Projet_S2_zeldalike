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
	private double moveCoefficient;
	private int baseCycle;
	
	
	public Movable(GameMap map,int cycle, int row, int column,double moveCoefficient) {
		if(cycle < 0 && moveCoefficient > 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		this.moveCoefficient = moveCoefficient;
		this.cycle = cycle;
		this.baseCycle=cycle;
		this.tour = 0;
		this.row = row;
		this.column = column;
		this.map = map;
	}
	
	protected void setWait(int cycle) {
		if(cycle < 0 && moveCoefficient > 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		this.cycle = cycle;
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
			this.cycle=this.baseCycle;
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
		Double d = this.cycle / this.moveCoefficient;
		return d.intValue();
	}
	
	//Omar n'étais pas d'accord
	public final int getX() {
		return this.column;
	}
	
	//Omar n'étais pas d'accord
	public final int getY() {
		return this.row;
	}
	
	
	protected abstract void removeCharacter();
	
	
	public abstract boolean isAlive();
	public abstract int getDefaultImage() ;
	public abstract Move act();
	
}
