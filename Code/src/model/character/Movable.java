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
	
	private final static HashMap<Movable,Character> MOVABLETYPE;
	private int speed;
	private GameMap map;
	
	static {
		MOVABLETYPE = new HashMap<Movable,Character>();
	}
	
	public static final char getType(Movable movable) {
		return MOVABLETYPE.get(movable);
	}
	
	
	public Movable(char type,int speed,GameMap map) {
		if(speed < 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		else if(!UsefulMethods.isCharInCharList(type, HEROTYPE,ENEMYTYPE))
			throw new IllegalArgumentException("Undefined type");
		this.speed = speed;
		MOVABLETYPE.put(this, type);
		this.map = map;
	}
	
	
	
	public final boolean setMap(GameMap newMap) {
		boolean set = (this.map == null);
		if(set)
			this.map = newMap;
		return set;
	}
	
	
	public final void deleteFromMap() {
		this.map = null;
	}
		
	public final int getSpeed() {
		return this.speed;
	}
	
	public Move turn() {
		
		return null;
	}
	
	
}
