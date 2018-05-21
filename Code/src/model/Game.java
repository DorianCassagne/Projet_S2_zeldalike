package model;

import model.gameMap.GameMap;

public class Game {
	private final static String mapName = "smallMap.csv";
	
	private GameMap myMap;
	
	public Game() {
		myMap = new GameMap(mapName);
	}
	
	public int getBackgroundId(int cellId) {
		return this.myMap.getBackgroundImage(cellId);
	}
	
	public Integer[] getNewPlayers() {
		return this.myMap.getNewCharList();
	}
	
	public boolean end() {
		return false;
	}
	
	public int getCharCell(Integer charKey) {
		return this.myMap.getCharacterPosition(charKey);
	}
	
}
