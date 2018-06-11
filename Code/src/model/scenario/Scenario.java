package model.scenario;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Supplier;

import javafx.beans.property.StringProperty;
import model.character.enemy.BlueFairy;
import model.gameMap.GameMap;
import model.scenario.action.Action;
import model.scenario.action.ActionData;
import model.scenario.condition.Condition;
import resources.additionalClass.SeparatorFileReader;

public class Scenario {
	
	private final static String SCENARIOPATH = "/resources/Scenario/";
	private final static String EXTERNALSEPARATOR = "->";
	private final static String INTERNALSEPARTOR = "-";
	private final static int CONDITIONINDEX = 0;
	private final static int CYCLE = 10;
	private final static String CONDITIONINTERNALSEPARATOR = ":";
	
	private int counter;
	private ArrayList<Evenement> events ;
	private HashMap<String,BlueFairy> elementsList;
	private ActionData data;
	private ArrayList<Integer> finishedEvents;
	
	public Scenario(String filename,StringProperty textMessages,GameMap map) {
		this.elementsList = new HashMap<String,BlueFairy>();
		this.finishedEvents = new ArrayList<Integer>();
		this.data = new ActionData(map,textMessages,elementsList,finishedEvents); 
		this.events = new ArrayList<Evenement>();
		BufferedReader reader = SeparatorFileReader.openTextFile(SCENARIOPATH + filename);
		ArrayList<ArrayList<String[]>> scenario = SeparatorFileReader.readFileWithTwoSeparator(reader, EXTERNALSEPARATOR, INTERNALSEPARTOR);
		this.counter = 0;
		readScenario(scenario);
	}
	
	
	private void readScenario(ArrayList<ArrayList<String[]>> scenario) {
		for(int i = 0; i < scenario.size();i++) {
			try{
				Supplier<Boolean> condition = getCondition(scenario.get(i));
				Supplier<Boolean>[] actions = this.getActions(scenario.get(i));
				this.events.add(new Evenement(i,condition,actions));
			}catch(Exception e) {
				throw new IllegalArgumentException("ERROR FOUND AT LINE "+ (i+1) + " \nMessage : " + e.getMessage());
			}
		}
	}
	
	private Supplier<Boolean> getCondition(ArrayList<String[]> scenarioPart){
		Supplier<Boolean> supplier;
		if(scenarioPart.size() > 1) {
			String[] vals = scenarioPart.get(CONDITIONINDEX);
			if(vals.length > 0) {
				supplier = Condition.createCondition(vals[0].split(CONDITIONINTERNALSEPARATOR),data);
				for(int i = 1;i < (vals.length+1)/2 ;i++) {
					supplier = Condition.createCondition(vals[2*i].split(CONDITIONINTERNALSEPARATOR),vals[2*i - 1],supplier, data);
					
				}
			}else
				throw new IllegalArgumentException("YOU MUST PROVIDE AT LEAST ONE CONDITION ");
		}
		else
			throw new IllegalArgumentException("Not Sufficient Elements FOR CONDITION " + scenarioPart.size());
		return supplier;
	}
	
	
	private Supplier<Boolean>[] getActions(ArrayList<String[]> scenarioPart){
		Supplier<Boolean>[] suppliers = new Supplier[scenarioPart.size() - CONDITIONINDEX];
		
		for(int i = CONDITIONINDEX + 1 ; i < suppliers.length ;i++) {
			suppliers[i] = Action.TakeAction(scenarioPart.get(i), this.data);
		}
		
		return suppliers;
		
	}
	
	private boolean canRun() {
		boolean canRun = false;
		if(this.counter == CYCLE) {
			this.counter = 0;
			canRun =true;
		}
		else
			this.counter++;
		return canRun;
	}
	
	public void run() {
		Evenement currentEvent;
		if(this.canRun()) {
			int i = 0;
			while(i < events.size()) {
				currentEvent = events.get(i);  
				if(currentEvent.evaluate()) {
					System.out.println("The current event id is : "+i);
					this.events.remove(i);
					this.finishedEvents.add(currentEvent.getId());
				}
				i++;
			}

		}
	}
}
