package model.scenario.action;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.function.BiConsumer;

import javafx.beans.property.StringProperty;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;

public class ActionData {
	private StringProperty messageProperty;
	private GameMap map;
	private HashMap<String,Enemy> attackList;
	private ArrayList<Integer> finishedEvents;
	private HashMap<Integer,String> itemList;
	private BiConsumer<String, String> createAction;
	
	public ActionData(GameMap map,StringProperty messageProperty,HashMap<String,Enemy> elementsList,HashMap<Integer,String> itemList,ArrayList<Integer> finishedEvents,BiConsumer<String, String> actionCreator) {
		
		this.map = map;
		this.messageProperty = messageProperty;
		this.attackList = elementsList;
		this.finishedEvents = finishedEvents;
		this.createAction = actionCreator;
		this.itemList = itemList;
		
	}
	
	public StringProperty messageProperty() {
		return this.messageProperty;
	}
	
	public GameMap getMap() {
		return this.map;
	}
	
	public HashMap<String,Enemy> getAttackList(){
		return this.attackList;
	}
	
	public HashMap<Integer,String> getItemList(){
		return this.itemList;
	}
	
	public ArrayList<Integer> getFinishedvents(){
		return this.finishedEvents;
	}
	
	public void createAction(String condition,String action) {
		if(condition != null && action != null) {
			this.createAction.accept(condition, action);
		}
	}
	
}
