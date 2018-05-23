package model.character;

import java.util.HashMap;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import resources.additionalClass.UsefulMethods;

public abstract class Movable {
	public final static char HEROTYPE = 'H';
	public final static char ENEMYTYPE = 'E';
	public final static int DEFAULTIMAGEINDEX = 0;
	public final static int TOPIMAGEINDEX = 0;
	public final static int LEFTIMAGEINDEX = 3;
	public final static int RIGHTIMAGEINDEX = 1;
	public final static int BOTTOMIMAGEINDEX = 2;	
	private final static HashMap<Movable,Character> MOVABLETYPE;
	
	private int speed;
	private int row;
	private int column;
	private GameMap map;
	private int cycle;
	private int tour ;
	
	static {
		MOVABLETYPE = new HashMap<Movable,Character>();
	}
	
	public static final char getType(Movable movable) {
		return MOVABLETYPE.get(movable);
	}
	
	
	public Movable(GameMap map,char type,int speed,int cycle, int row, int column) {
		if(speed < 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		else if(!UsefulMethods.isCharInCharList(type, HEROTYPE,ENEMYTYPE))
			throw new IllegalArgumentException("Undefined type");
		this.cycle = cycle;
		System.out.println("cycle = "+ cycle);
		this.tour = 0;
		this.speed = speed;
		this.row = row;
		this.column = column;
		MOVABLETYPE.put(this, type);
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
	
	
	public final int getSpeed() {
		return this.speed;
	}
	
	public void oneTurn() {
		if(this.tour != cycle)
			this.tour++;
	}
	
	protected boolean canAct() {
		boolean canAct = false;
		if(this.tour == cycle) {
			this.tour = 0;
			canAct = true;
		}
		return canAct;
	}
	
	public abstract Move turn();
	public abstract int getDefaultImage() ;
	
	
}
