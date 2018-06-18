package model.scenario.action;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.function.BiConsumer;

import javafx.beans.property.StringProperty;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;
import model.scenario.Scenario;

public class ActionData {
	
	/*
	 * Représente les données du scénario courant
	 * 
	 * Cette classe a pour résponsabilités : 
	 * -Retourner la map (voir description)
	 * -Retourner le messageProperty (voir classe Game)
	 * -Retourner elementList (voir description)
	 * -Retourner les événements finis
	 * 
	 * 
	 */
	
	//Un attribut partagé entre le game et le scénario, sert nécessairement à l'affichage des messages
	private StringProperty messageProperty;
	
	//La map courante, sur laquelle se déroule le scénario
	private GameMap map;
	
	//La liste des éléments crées par le scénario
	//Attention Chaque ennemie crée doit avoir un identifiant unique, sinon l'ancien ne sera plus référencé
	private HashMap<String,Enemy> attackList;
	
	private ArrayList<Integer> finishedEvents;
	private HashMap<Integer,String> itemList;
	private HashMap<Integer,String> NPCList;
	private BiConsumer<String, String> createAction;
	private Integer counter;
	
	
	/*
	 * Crée un objet du type Action data
	 * 
	 * @param GameMap map : la map courante dédiée au scénario
	 * @param StringProperty messageProperty : la propriété utilisée pour l'affichage des messages
	 * @param HashMap<String,enemy> elementsList : la liste qui contient tous les ennemis créé par le scénario.
	 * @param ArrayList<Integer> finishedEvents : la liste des événements achevés
	 * 
	 * @throws IllegalArgumentException : Si l'un des paramètres est null
	 */
	
	public ActionData(GameMap map,StringProperty messageProperty,HashMap<String,Enemy> elementsList,HashMap<Integer,String> itemList,ArrayList<Integer> finishedEvents,BiConsumer<String, String> actionCreator,HashMap<Integer,String> NPCList) {
		
		this.map = map;
		this.messageProperty = messageProperty;
		this.attackList = elementsList;
		this.finishedEvents = finishedEvents;
		this.createAction = actionCreator;
		this.itemList = itemList;
		this.counter = 0;
		this.NPCList = NPCList;
		
	}
	
	
	/*
	 * Getters
	 */
	
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
	
	public int getCounter() {
		return this.counter;
	}
	
	public void setScenarioCounter(int counter) {
		if(counter < Scenario.CYCLE) {
			this.counter = counter;
		}
		else {
			this.counter = -counter;
		}
	}
	
	public void increaseCounter() {
		this.counter++;
	}
	
	public HashMap<Integer,String> getNPCList(){
		return this.NPCList;
	}
	
}
