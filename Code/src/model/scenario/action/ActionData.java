package model.scenario.action;

import java.util.HashMap;

import javafx.beans.property.StringProperty;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;

public class ActionData {
	private StringProperty messageProperty;
	private GameMap map;
	private HashMap<String,Enemy> elementsList;
	
	public ActionData(GameMap map,StringProperty messageProperty,HashMap<String,Enemy> elementsList) {
		this.map = map;
		this.messageProperty = messageProperty;
		this.elementsList = elementsList;
	}
	
	public StringProperty messageProperty() {
		return this.messageProperty;
	}
	
	public GameMap getMap() {
		return this.map;
	}
	
	public HashMap<String,Enemy> getElementsList(){
		return this.elementsList;
	}
}
