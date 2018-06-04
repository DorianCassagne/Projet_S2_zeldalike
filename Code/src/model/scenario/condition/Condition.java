package model.scenario.condition;

import java.util.function.Supplier;
import model.character.GameCharacter;
import model.gameMap.additional.Statics;
import model.scenario.action.ActionData;

public class Condition {
	/* 
	 *Syntaxe de la case : C-IdCase-' '/!-I/W;
	 *Syntaxe pour Enemy : M-IdMonster-' '/!-NBLIFE
	 *Syntaxe pour Hero : H-IdCase/{R/L/U/D}-' '/!-C
	 */

	
	private final static char CELL = 'C';
	private final static char HERO = 'H';
	private final static char MONSTER = 'M';
	private final static char NEGATION = '!';
	private final static char CONTAINSITEM = 'I';
	private final static char WALKABLE = 'W';

	private static ActionData actionData;

	private String id;
	private boolean conditionValue;
	private int lifePoint;
	private char detail;
	
	private Condition(String id,char conditionValue,int lifePoint,char detail) {
		this.id = id;
		this.conditionValue = conditionValue == NEGATION;
		this.detail =detail;
		this.lifePoint = lifePoint;
	}
	
	private Supplier<Boolean> handleCell(){
		Supplier<Boolean> supplier = null;
		switch(this.detail) {
		case CONTAINSITEM:
			supplier = ()->this.checkForItem();
			break;
		case WALKABLE:
			this.checkForWalkable();
			break;
		}
		return supplier;
	}
	
	private boolean checkForItem() {
		boolean thereIsItem = false;
		try {
			int cellId = Integer.parseInt(this.id);
			thereIsItem = actionData.getMap().containsItemAt(cellId);
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException("CHECKFORITEM FAILED BECAUSE OF INCORRECT CELL ID : " + this.id);
		}
		return thereIsItem ^ this.conditionValue;
	
	}
	
	private boolean checkForWalkable() {
		
		boolean walkable = false;
		try {
			int cellId = Integer.parseInt(this.id);
			walkable = actionData.getMap().isWalkableAt(cellId);
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException("CHECKFORWALKABLE FAILED BECAUSE OF INCORRECT ID : "+this.id);
		}
		return walkable ^ this.conditionValue;
		
	}
	
	
	private Supplier<Boolean> handleMonster(){
		Supplier<Boolean> supplier = ()->{
			boolean isGreater = false;
			try {
				isGreater = actionData.getElementsList().get(this.id).getHp() <= this.lifePoint;
			}catch(NullPointerException e) {
				
			}
			return isGreater ^ this.conditionValue;
		};
		return supplier;
	}	
	
	
	private Supplier<Boolean> handleHero(){
		Supplier<Boolean> supplier = ()->{
			int cellId = Statics.convertToCellId(GameCharacter.getHero().getRow(), GameCharacter.getHero().getColumn());
			int destId;
			try{
				destId = Integer.parseInt(this.id);			
			}catch(NumberFormatException e) {
				throw new IllegalArgumentException("ERROR WHILE CONVERTING HERO CELL ID : "+this.id);
			}
			return (destId == cellId)^this.conditionValue;
		};
		return supplier;
	}
	
	
	
	public final static Supplier<Boolean> createCondition(String[] conditionString,ActionData data){		
		ConditionEncode encoder = new ConditionEncode(conditionString);
		Condition condition = new Condition(encoder.getId(),encoder.getConditionValue(),encoder.getLifePoint(),encoder.getDetail());
		actionData = data;
		Supplier<Boolean> supplier;
		System.out.println("The length of Condition is "+conditionString.length);
		switch(encoder.getCategory()) {
		case CELL :
			supplier = condition.handleCell();
			break;
		case MONSTER:
			supplier = condition.handleMonster();
			break;
		case HERO:
			supplier = condition.handleHero();
			break;
		default:
			throw new IllegalArgumentException("ERROR WHILE CREATING CONDITION BECAUSE OF " + encoder.getCategory());
		}
		return supplier;
		
	}
	
	public final static Supplier<Boolean> createCondition(String[] conditionString,String previousConditionEffect,Supplier<Boolean> lastCondition,ActionData data){		
		Supplier<Boolean> s;
		Supplier<Boolean> thisCondition = createCondition(conditionString,data);
		System.out.println("The previous effect is : " + previousConditionEffect);
		if(previousConditionEffect.equalsIgnoreCase("OR")) {
			s = ()->{
				return lastCondition.get() || thisCondition.get();
			};
		}
		else if(previousConditionEffect.equalsIgnoreCase("AND")) {
			s = () -> {
				return lastCondition.get() && thisCondition.get(); 
			};
		}
		else {
			throw new IllegalArgumentException("PLEASE BE SURE THAT THE PREVIOUS CONDITION IS EITHER AND OR OR : "+previousConditionEffect);
		}
		return s;
		
	}

	
}
