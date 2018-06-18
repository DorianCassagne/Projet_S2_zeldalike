package model.scenario.action;


import java.util.function.Supplier;


import model.character.GameCharacter;
import model.character.attack.statics.DynamicLauncher;
import model.character.enemy.Enemy;
import model.character.enemy.EnemyFactory;
import model.character.hero.GameHero;
import model.character.item.Item;
import model.character.item.factory.ItemFactory;
import model.character.item.mapChange.MapChangerEnum;
import model.character.npc.TalkingNPC;
import model.gameMap.additional.Statics;

public class Action {
	/*
	 * Syntaxe action
	 *  Action-TypeGeneral-TypeParticulier-Info-IdCase
	 * 	S/C/D/A-I/M/m/A/W-' '/{type_monstre}/{type_item}-contenuMessage/idMonstre-CELLID
	 *  Pour NPC : C-N-IDIMAGE-MESSAGE-CELLID
	 */
	public final static char CREATION = 'C';
	public final static char ITEM = 'I';
	public final static char WALKABLE = 'W';
	public final static char MONSTER=  'M';
	public final static char DROP = 'D';
	public final static char SHOW = 'S';
	public final static char ADD = 'A';
	public final static char ATTACK = 'A';
	public final static char NOTHING = 'N';
	public final static char NPC = 'N';
	public final static char HERO = 'H';
	public final static char DELAY = 'd';
	public final static char MAP = 'M';
	
	
	private static ActionData actionData;
	private char generalType;
	private String specificType;
	private String info;
	private int cellId;
	private boolean isActive;
	
	private Action(char generalType,String specificType,String info,int cellId) {
		this.generalType = generalType;
		this.specificType = specificType;
		this.info = info;
		this.cellId = cellId;
		this.isActive = true;
	}

	
	public boolean isActive() {
		return this.isActive;
	}

	
	private Supplier<Boolean> createShowConsumer(){
		Supplier<Boolean> supplier = (()->{
			actionData.messageProperty().set(this.info);
			return true;
		});
		return supplier;
	}
	
	private Supplier<Boolean> createCreation(){
		Supplier<Boolean> supplier ;

		switch(this.generalType) {
		case ITEM :
			supplier = ()->createItem();
			break;
		case MONSTER:
			supplier = ()->createMonster();
			break;
		case NPC : 
			supplier = ()->createNPC();
			break;
		default:
			throw new IllegalArgumentException("ERROR IN CREATION WHILE PROCESSING GENERAL TYPE" + this.generalType);
		}
		
		return supplier;
	}

	private boolean createMonster() {
		boolean added = true;
		
		try {	
			Enemy enemy = EnemyFactory.MonsterFactory(this.specificType, actionData.getMap(), Statics.convertToRow(this.cellId),Statics.convertToColomn(this.cellId));
			actionData.getAttackList().put(this.info,enemy);
		}catch(Exception e) {
			added = false;
			System.err.println("ENEMY FACTORY FAILED BECAUSE OF " + this.specificType + e.getMessage());
		}
		
		return added;
	}
	
	
	private boolean createNPC() {
		
		try {
		
			new TalkingNPC(actionData.messageProperty(), this.info,this.specificType,actionData.getMap(),Statics.convertToRow(this.cellId),Statics.convertToColomn(this.cellId));
			 actionData.getNPCList().put(this.cellId, this.specificType);
		}catch(Exception e) {
			System.err.println("ERROR ON TALKING NPC, MAYBE THE ID WAS NOT A NUMBER : " + this.specificType);
		}
		
		return true;
	}
	
	private boolean createItem() {
		Item item = ItemFactory.getItem(this.specificType.toUpperCase());

		boolean created = actionData.getMap().addItem(item,this.cellId);

		if(created) {
			actionData.getItemList().put(this.cellId,this.specificType.toUpperCase());
		}
		
		return created;
	}
	
	private Supplier<Boolean> establishDrop() {
		Supplier<Boolean> supplier;
		switch(this.generalType) {
		case MONSTER : 
			supplier = ()->{
				boolean isFound = actionData.getAttackList().get(this.info) != null;
				if(isFound) {
					Enemy enemy = actionData.getAttackList().get(this.info);
					enemy.removeCharacter(this);
					actionData.getAttackList().remove(enemy);
				}
				return isFound;
			
			};
			break;
			
		case WALKABLE : 
			supplier = () -> {
				try {
					actionData.getMap().clearBackgroundConstraint(this.cellId,this,Integer.parseInt(this.info));
					actionData.getNPCList().remove(this.cellId);
				}catch(NumberFormatException e) {
					System.err.println("ERROR ON ESTABLISH DROP() OF WALKABLE"); 
				}
				return true;
			};
			break;
		
		case ITEM : 
			supplier = ()-> {
				actionData.getMap().removeItemAt(this.cellId);
				return true;
		};
		
		case HERO : 
			supplier = ()-> {
				GameHero.getHero().removeCharacter(this);
				return true;
			};
		break;
		
		default : 
				throw new IllegalArgumentException("DROP ACTION FAILED BECAUSE OF " + this.generalType);
		}
		return supplier;
	}
	
	
	private Supplier<Boolean> establishAdd(){
		Supplier<Boolean> supplier;
		switch(this.generalType) {
		case ITEM :
			supplier = ()->addItemToHero();
			break;
		case ATTACK:
			supplier = ()->addAttackToHero();
			break;
		case MONSTER :
			supplier = ()->addCharacter(actionData.getAttackList().get(this.info));
			break;
		case HERO :
			supplier = ()->addCharacter(GameHero.getHero());
			break;
		default :
			throw new IllegalArgumentException("ERROR GENERAL TYPE AT ADD :  "+this.generalType);
		}
		return supplier;
	}
	
	private boolean addCharacter(GameCharacter character) {
		boolean added = character != null && actionData.getMap().addCharacter(character, character.getRow(), character.getColumn());
		return added;
	}
	
	private boolean addItemToHero() {
		Item item = ItemFactory.getItem(this.specificType);
		return item.effectOn(GameCharacter.getHero());
	}
	
	private boolean addAttackToHero() {
		
		try {
			DynamicLauncher dynamicLaunch = new DynamicLauncher(Integer.parseInt(this.specificType));
			GameHero.getHero().addLauncher(dynamicLaunch);
		}catch(Exception e) {
			System.err.println("ERROR ON ADD ATTACK TO HERO BECAUSE OF SPECIFIC TYPE : " + this.specificType);
		}
		return true;
	}
	
	private boolean changeMap() {
		GameHero.getHero().setMapChange(MapChangerEnum.valueOf(this.specificType));
		return true;
	}
	
	private boolean delay() {
		
		actionData.getMap().setActionDelay(this,this.cellId);
		try {
			actionData.setScenarioCounter(Integer.parseInt(this.info));
		}catch(NumberFormatException e) {
			System.err.println("ERROR ON DELAY BECAUSE THE INFO IS NOT AN INT " + this.info);
		}
		return true;
	}
	
	public static Supplier<Boolean> TakeAction(String[] action,ActionData data) {
		ActionEncode encode = new ActionEncode(action);
		Action actionParams = new Action(encode.getGeneralType(),encode.getSpecificType(),encode.getInfo(),encode.idCase());
		actionData = data;
		Supplier<Boolean> supplier;
		switch(encode.getAction()) {
		case SHOW :
			supplier = actionParams.createShowConsumer();
			break;
		case CREATION:
			supplier = actionParams.createCreation();
			break;
		case DROP :
			supplier = actionParams.establishDrop();
			break;
		case ADD :
			supplier = actionParams.establishAdd();
			break;
		case NOTHING:
			supplier = ()->(true);
			break;
		case MAP:
			supplier = ()->actionParams.changeMap();
			break;
		case DELAY:
			supplier =()->actionParams.delay();
			break;
		default :
			throw new IllegalArgumentException("UNKNOWN ACTION NAME : " + encode.getAction());
		}
		return supplier;
		
	}
		
	
}
