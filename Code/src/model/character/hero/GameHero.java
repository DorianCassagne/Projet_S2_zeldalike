package model.character.hero;


import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.attack.dynamic.DefaultAttackLauncher;
import model.character.attack.dynamic.Launcher;
import model.character.item.attack.AttackItem;
import model.character.item.def.DefenseItemEnum;
import model.character.item.hp.HealEnum;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;
import resources.additionalClass.UsefulMethods;

public abstract class GameHero extends GameCharacter {
	
	private final static int DEFAULTCYCLE = 20;
	private final static double DEFAULTCOEFFICIENT = 1.5;
	private final static int DEFAULTHP = 300;
	private final static int DEFAULTDEF = 40;
	
	protected final static int DEFAULTIMAGE = 8;
	public static final char MOVEUP = 'u';
	public static final char MOVEDOWN = 'd';
	public static final char MOVELEFT = 'l';
	public static final char MOVERIGHT = 'r';
	public static final char STAY = ' ';
	public static final char ATTACKUP = '8';
	public static final char ATTACKRIGHT = '6' ;
	public static final char ATTACKDOWN = '2' ;
	public static final char ATTACKLEFT = '4' ;
	public static final char CHANGEATTACK = 'c';
	private final static AttackItem DEFAULTATKITEM = new AttackItem(809);
	
	private IntegerProperty defenseImage;
	private IntegerProperty def;
	private IntegerProperty hp;
	private IntegerProperty atk;
	private IntegerProperty safeHP;
	private IntegerProperty safeDef;
	private IntegerProperty safeAtk;
	
	private ArrayList<Launcher> attackList;
	private IntegerProperty attackType;
	
	public GameHero(GameMap map, int startRow, int startColumn, int defaultImage,int startHP,int startDef) {
		super(map, startRow, startColumn, DEFAULTCYCLE, DEFAULTCOEFFICIENT, defaultImage);
		initialiseProperties(startHP,startDef);
		this.attackList = new ArrayList<Launcher>();
		this.attackList.add(new DefaultAttackLauncher(DEFAULTATKITEM));
		this.attackType = new SimpleIntegerProperty(0);
	}
	
	protected void setNextAttack(int index) {
		if(index >= 0 && index < this.attackList.size())
			this.attackType.set(index);
	}
	
	protected int getIndexAttack() {
		return this.attackType.get();
	}
		
	public void addLauncher(Launcher launcher) {
		this.attackList.add(launcher);
	}
	
	protected int getAttackQuantity() {
		return this.attackList.size();
	}
	
	public GameHero(GameMap map, int startRow, int startColumn) {
		this(map, startRow, startColumn,DEFAULTIMAGE,DEFAULTHP,DEFAULTDEF);
	}

	
	private  void initialiseProperties(int hp,int def) {
		this.hp = new SimpleIntegerProperty(hp);
		this.def = new SimpleIntegerProperty(def);
		this.safeHP = UsefulMethods.copyIntegerProperty(this.hp);
		this.safeDef = UsefulMethods.copyIntegerProperty(this.def);
	}
	
	public final IntegerProperty hpProperty() {
		return this.safeHP;
	}
	
	
	public final IntegerProperty defProperty() {
		return this.safeDef;
	}
	
	@Override
	public void launchAttack(Movement direction) {
		this.attackList.get(this.attackType.get()).launch(this.getMyMap(), direction, this.getRow(), this.getColumn());
	}

	
	public void heal(HealEnum healer) {
		this.setHP(this.hp.get() + healer.getHeal());
	}
	
	public void setBasicAtk(AttackItem attack) {
		if(attack != null)
			this.attackList.set(0, new DefaultAttackLauncher(attack));
		else
			throw new IllegalArgumentException("THE ATTACK CHANGE IS NULL");
	}
	
	public void setBasicDef(DefenseItemEnum defenseItem) {
		if(defenseItem != null) {
			this.def.set(defenseItem.getMoreDef() + DEFAULTDEF);
		}
	}
	
	@Override
	public void getDmg(Attack attack) {
		this.hp.set(this.hp.get() - GameCharacter.calculateDamage(attack.getDamage(), this.def.get()));
	}
	
	
	private void setHP(int newHP) {
		
		if(newHP < 0) 
			newHP = 0;
		else if(newHP > DEFAULTHP)
			newHP = DEFAULTHP;
		this.hp.set(newHP);
		

	}
	


	@Override
	public boolean isAlive() {
		return this.hp.get() > 0;
	}

	
}
