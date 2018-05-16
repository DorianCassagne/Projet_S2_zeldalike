package model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import model.map.GameMap;
import model.movable.GameCharacter;

public class Game {
	GameMap gameMap;
	ArrayList<GameCharacter> characterList;
	public Game() {
		gameMap=new GameMap();
		characterList=new ArrayList<GameCharacter>();
	}
	
	public IntegerProperty[] getChangedList() {
		return gameMap.getMapChange();
	}
}
