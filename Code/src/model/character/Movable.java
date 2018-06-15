package model.character;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
import model.scenario.action.Action;
import resources.additionalClass.UsefulMethods;

public abstract class Movable {

	private int row;
	private int column;
	private GameMap map;
	private int cycle;
	private int tour ;
	private double moveCoefficient;
	private IntegerProperty safeImageValueProperty;
	private IntegerProperty imageValueProperty;
	private int defaultImage;
	private int baseCycle;
	
	public Movable(GameMap map,int cycle, int row, int column,double moveCoefficient,int defaultImage) {
		if(cycle < 0 || moveCoefficient < 0 )
			throw new IllegalArgumentException("Invalid Parameter");
		setCellId(row, column);
		this.map = map;
		this.moveCoefficient = moveCoefficient;
		this.cycle = cycle;
		this.tour = 0;	
		this.baseCycle=cycle;
		this.defaultImage = defaultImage;
		initialiseImageProperty();
	}
	
	protected void setWait(int cycle) {
		if(cycle < 0 && moveCoefficient > 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		this.cycle = cycle;
	}
	
	private void initialiseImageProperty() {
		this.imageValueProperty = new SimpleIntegerProperty(this.defaultImage);
		this.safeImageValueProperty = UsefulMethods.copyIntegerProperty(imageValueProperty );
	}
	
	protected void setImage(Movement movement) {
		this.imageValueProperty.set(this.defaultImage + movement.getIndex());
	}
	
	protected void setImage(int i) {
		this.imageValueProperty.set(this.defaultImage +i);
	}
			
	public final IntegerProperty getImageValueProperty() {
		return this.safeImageValueProperty;
	}
	
	
	public final int getRow() {
		return this.row;
	}
	
	public final int getColumn() {
		return this.column;
	}
	
	public final int getCellId() {
		return Statics.convertToCellId(this.getRow(), this.getColumn());
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
		
	protected final void setMap(GameMap newMap) {
		if(this == GameCharacter.getHero()) {
			this.map = newMap;
		}
	}
	
	public void removeCharacter(Action action) {
		if(action != null && action.isActive()) {
			this.removeCharacter();
		}
	}

	public final void setCoefficient(double newCoefficient) {
		this.moveCoefficient = newCoefficient;
	}
	
	protected abstract void removeCharacter();	
	public abstract boolean isAlive();
	protected abstract Move act();
	
}
