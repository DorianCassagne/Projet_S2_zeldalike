package model.movable;

import model.map.GameMap;

public abstract class GameCharacter extends Movable{
	
	public GameCharacter(GameMap map, int key) {
		super(map, key);
	}	
	
}
