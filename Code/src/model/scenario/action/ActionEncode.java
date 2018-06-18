package model.scenario.action;

import model.character.enemy.Enemy;
import model.scenario.Scenario;
import resources.additionalClass.Conversion;

public class ActionEncode {
	

	private char action ;
	private char generalType;
	private String specificType;
	private String info;
	private int idCase;
	
	public ActionEncode(String[] params) {
		try {
			this.action = Conversion.toChar(params[0]);
			if(action != Action.NOTHING) {
				this.generalType = Conversion.toChar(params[1]);
				this.specificType = params[2];
				this.info = params[3];
				this.idCase = Conversion.toInt(params[4]);
			}		
		}catch(Exception e) {
			
		}
	}
	
	public char getAction() {
		return this.action;
	}
	
	public char getGeneralType() {
		return this.generalType;
	}
	
	public String getSpecificType() {
		return this.specificType;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public int idCase() {
		return this.idCase;
	}

	
	public static String encodeToMonster(String name,Enemy monster) {
		String value = Scenario.EXTERNALSEPARATOR + Action.CREATION;
		value += Scenario.INTERNALSEPARTOR + Action.MONSTER;
		value += Scenario.INTERNALSEPARTOR + monster.getName();
		value += Scenario.INTERNALSEPARTOR + name;
		value += Scenario.INTERNALSEPARTOR + monster.getCellId();
		
		return value;
	}
	
	public static String encodeToItem(int cellId,String itemName) {
		
		String value = Scenario.EXTERNALSEPARATOR + Action.CREATION;
		value += Scenario.INTERNALSEPARTOR + Action.ITEM;
		value += Scenario.INTERNALSEPARTOR + itemName;
		value += Scenario.INTERNALSEPARTOR + " ";
		value += Scenario.INTERNALSEPARTOR + cellId;
		
		return value;
		
	}
	
	public static String encodeToNPC(int cellId,String NPCType) {
		
		String value = Scenario.EXTERNALSEPARATOR + Action.CREATION;
		value += Scenario.INTERNALSEPARTOR + Action.NPC;
		value += Scenario.INTERNALSEPARTOR + NPCType;
		value += Scenario.INTERNALSEPARTOR + " ";
		value += Scenario.INTERNALSEPARTOR + cellId;
		
		return value;

		
	}
	
}
