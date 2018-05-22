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
	
	private final static HashMap<Movable,Character> MOVABLETYPE;
	private int speed;
	
	
	static {
		MOVABLETYPE = new HashMap<Movable,Character>();
	}
	
	public static final char getType(Movable movable) {
		return MOVABLETYPE.get(movable);
	}
	
	
	public Movable(char type,int speed) {
		if(speed < 0)
			throw new IllegalArgumentException("Speed must be greater than 0");
		else if(!UsefulMethods.isCharInCharList(type, HEROTYPE,ENEMYTYPE))
			throw new IllegalArgumentException("Undefined type");
		this.speed = speed;
		MOVABLETYPE.put(this, type);
	}
	
		
	public final int getSpeed() {
		return this.speed;
	}
	
	public Move turn() {
		
		return null;
	}
	
	public abstract int getDefaultImage() ;
	
	
}
