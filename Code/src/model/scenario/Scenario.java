package model.scenario;

import java.io.BufferedReader;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import javafx.beans.property.StringProperty;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;
import model.scenario.action.Action;
import model.scenario.action.ActionData;
import model.scenario.action.ActionEncode;
import model.scenario.condition.Condition;
import resources.additionalClass.SeparatorFileReader;
import resources.additionalClass.SeparatorFileWriter;

public class Scenario {
	
	private final static String SCENARIOPATH = "/resources/Scenario/";
	private final static String SCENARIOSAVEPATH = System.getProperty("user.home") + "/Scenario_";
	public final static String EXTERNALSEPARATOR = "->";
	public final static String INTERNALSEPARTOR = "-";
	private final static int CONDITIONINDEX = 0;
	private final static int EVENTSTOSKIPINDEX = 1;
	public final static int CYCLE = 10;
	private final static String CONDITIONINTERNALSEPARATOR = ":";
	
	private ArrayList<Evenement> events ;
	private HashMap<String,Enemy> enemyList;
	private HashMap<Integer,String> itemList;
	private HashMap<Integer,String> NPCList;
	
	private ActionData data;
	private ArrayList<Integer> finishedEvents;
	
	public Scenario(String filename,String saveName,StringProperty textMessages,GameMap map) {
		
		this.enemyList = new HashMap<String,Enemy>();
		this.finishedEvents = new ArrayList<Integer>();
		this.itemList = new HashMap<Integer,String>();
		this.NPCList = new HashMap<Integer,String>();
		initData(map,textMessages);
		
		this.events = new ArrayList<Evenement>();
		
		
		
		readSave(writeScenario(saveName,false));
		readScenario(writeScenario(SCENARIOPATH + filename,true),true);
		
	}
	
	private void initData(GameMap map,StringProperty textMessages) {
		//N'accepte qu'une seule condition
		BiConsumer<String,String> callBack = (conditionString,actionString)->{
			
			String[] condition= conditionString.split(Scenario.CONDITIONINTERNALSEPARATOR);
			String[] action = actionString.split(INTERNALSEPARTOR);
			try {
				Supplier<Boolean> cond = Condition.createCondition(condition, data);
				Supplier<Boolean>[] act = new Supplier[1];
				
				act[0] =  Action.TakeAction(action, data);
				
				events.add(new Evenement(events.size() ,cond,act));
			}catch(Exception e) {
				System.err.println("ACTION CREATION FAILED");
			}
		};
		
		
		this.data = new ActionData(map,textMessages,enemyList,itemList,finishedEvents,callBack,this.NPCList); 
		
	}
	
	private ArrayList<ArrayList<String[]>> writeScenario(String fileName,boolean internal) {
		ArrayList<ArrayList<String[]>> scenario = null;
		
		if(fileName != null) {
			BufferedReader reader = SeparatorFileReader.openTextFile( fileName,internal);
			scenario = SeparatorFileReader.readFileWithTwoSeparator(reader, EXTERNALSEPARATOR, INTERNALSEPARTOR);
	
		}
	
		return scenario;
	}
	
	
	
	private void readScenario(ArrayList<ArrayList<String[]>> scenario,boolean toCheckOld) {
		for(int i = 0; i < scenario.size();i++) {
			try{
				if(!this.finishedEvents.contains(i + 1) || !toCheckOld) {
					Supplier<Boolean> condition = getCondition(scenario.get(i));
					Supplier<Boolean>[] actions = this.getActions(scenario.get(i));
					if(!toCheckOld)
						this.events.add(0,new Evenement(Integer.MAX_VALUE,condition,actions));
					else
						this.events.add(0,new Evenement(i,condition,actions));
				}
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
		if(this.data.getCounter() == CYCLE) {
			this.data.setScenarioCounter(0);
			canRun = true;
		}
		else
			this.data.increaseCounter();
		return canRun;
	}
	
	public void run() {
		Evenement currentEvent;

		if(this.canRun()) {
			int i = 0;
			while(i < events.size()) {
				currentEvent = events.get(i);  

				if(currentEvent.evaluate()) {
					
					this.events.remove(i);
					this.finishedEvents.add(currentEvent.getId());
				
				}
				i++;
			}

		}
	}
	
	
	public String saveScenario() {
		final StringBuilder scenarioCode = new StringBuilder("N");
		
		DateFormat dateFormat = new SimpleDateFormat("HHmmssddMMyyyy");
		String path = SCENARIOSAVEPATH + dateFormat.format(new Date()) + ".txt";
		
		enemyList.forEach((name,monster)->{
			if(monster.isAlive()) {
				scenarioCode.append( ActionEncode.encodeToMonster(name,monster) );
			}
		});
		
		itemList.forEach((cellId,itemName)->{
			if(this.data.getMap().containsItemAt(cellId)){
				scenarioCode.append(ActionEncode.encodeToItem(cellId,itemName));
			}
		});
		
		this.NPCList.forEach((cellId,NPCPers)->{
			scenarioCode.append(ActionEncode.encodeToNPC(cellId,NPCPers));
		});

		
		scenarioCode.append("\n");
		
		this.finishedEvents.forEach(e->{
			scenarioCode.append(e + INTERNALSEPARTOR);
		});
		
		
		
		if(!SeparatorFileWriter.writeToFile(path,scenarioCode.toString(),false)) {
			 path = null;
		}
		
		return path;
		
				
		
	}
	
	
	public void readSave(ArrayList<ArrayList<String[]>> savedScenario) {
		
		
		if(savedScenario != null) {
			
			//J'enlève la deuxième ligne du scenario
			String[] finishedLines = savedScenario.get(EVENTSTOSKIPINDEX).get(0);
			
			savedScenario.remove(EVENTSTOSKIPINDEX);
			
			try {
				
				for(String finishedEventLine : finishedLines) {
					this.finishedEvents.add(Integer.parseInt(finishedEventLine));
				}
				
			}catch(NumberFormatException e) {
				System.err.println("Cannot read SaveFile" );
			}
			
			
			//Je crée les monstres déjà présents sur la map : 
			this.readScenario(savedScenario,false);
		}
	}
	
	
}
