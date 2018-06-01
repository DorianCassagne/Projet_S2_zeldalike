package model.character;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
import resources.additionalClass.UsefulMethods;

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
	private IntegerProperty safeImageValueProperty;
	private int baseCycle;
	private IntegerProperty imageValueProperty;
	private int defaultImage;
	
	public Movable(GameMap map,int cycle, int row, int column,double moveCoefficient,int defaultImage) {
		if(cycle < 0 || moveCoefficient < 0 )
			throw new IllegalArgumentException("Invalid Parameter");
		setCellId(row, column);
		this.map = map;
		this.moveCoefficient = moveCoefficient;
		this.cycle = cycle;
		this.baseCycle=cycle;
		this.tour = 0;	
		this.defaultImage = defaultImage;
		initialiseImageProperty();
	}
	
	private void initialiseImageProperty() {
		this.imageValueProperty = new SimpleIntegerProperty(this.defaultImage);
		this.safeImageValueProperty = UsefulMethods.copyIntegerProperty(imageValueProperty );
	}
	
	protected void setImage(Movement movement) {
		this.imageValueProperty.set(this.defaultImage + movement.getIndex());
	}
			
	public final IntegerProperty getImageValueProperty() {
		return this.safeImageValueProperty;
	}
	
	protected void setWait(int cycle) {
		if(cycle < 0)
			throw new IllegalArgumentException("CYCLE MUST BE GREATER THAN 0");
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
		
		if(Statics.isInMap(row, column)) {
			this.row = row;
			this.column = column;
		}
		else
			throw new IllegalArgumentException ("THE POSITION OF THE CHARACTER IS WRONG");	
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
	public abstract Move act();
	
}
