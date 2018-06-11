package model.scenario.action;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.beans.property.StringProperty;
import model.character.enemy.BlueFairy;
import model.gameMap.GameMap;
import model.scenario.Evenement;

public class ActionData {
	private StringProperty messageProperty;
	private GameMap map;
	private HashMap<String,BlueFairy> elementsList;
	private ArrayList<Integer> finishedEvents;
	
	public ActionData(GameMap map,StringProperty messageProperty,HashMap<String,BlueFairy> elementsList,ArrayList<Integer> finishedEvents) {
		this.map = map;
		this.messageProperty = messageProperty;
		this.elementsList = elementsList;
		this.finishedEvents = finishedEvents;
	}
	
	public StringProperty messageProperty() {
		return this.messageProperty;
	}
	
	public GameMap getMap() {
		return this.map;
	}
	
	public HashMap<String,BlueFairy> getElementsList(){
		return this.elementsList;
	}
	
	public ArrayList<Integer> getFinishedvents(){
		return this.finishedEvents;
	}
	
}
