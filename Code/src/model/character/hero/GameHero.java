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
import model.character.item.pvItem.HealthEnum;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public abstract class GameHero extends GameCharacter {
	
	private final static int DEFAULTCYCLE = 25;
	private final static double DEFAULTCOEFFICIENT = 1.5;
	private final static int DEFAULTHP = 300;
	private final static int DEFAULTMP = 200;
	private final static int DEFAULTDEF = 40;
	private final static int DEFAULTIMAGE = 8;
	private final static AttackItem DEFAULTATKITEM = new AttackItem(809);
	
	private IntegerProperty def;
	private IntegerProperty hp;
	private IntegerProperty mp;
	private CopyOfHeroStats safeStats;
	private int maxHP;
	private IntegerProperty attackItemImage;
	private ArrayList<Launcher> attackList;
	private int attackType;
	
	public GameHero(GameMap map, int startRow, int startColumn, int defaultImage,int startDef) {
		super(map, startRow, startColumn, DEFAULTCYCLE, DEFAULTCOEFFICIENT, defaultImage);
		initialiseProperties(startDef);
		initAttack();
	}
				
	public GameHero(GameMap map, int startRow, int startColumn) {
		this(map, startRow, startColumn,DEFAULTIMAGE,DEFAULTDEF);
	}

	
	private void initAttack() {
		this.attackList = new ArrayList<Launcher>();
		this.setAttackIndex(0);
		this.attackList.add(new DefaultAttackLauncher(DEFAULTATKITEM));
	}
	

	
	private  void initialiseProperties(int def) {
		
		this.hp = new SimpleIntegerProperty(DEFAULTHP);
		this.def = new SimpleIntegerProperty(def);
		this.attackItemImage = new SimpleIntegerProperty(0);
		this.mp = new SimpleIntegerProperty(DEFAULTMP);
		this.safeStats = new CopyOfHeroStats(this.hp,this.def,this.attackItemImage,this.mp);
		this.maxHP = DEFAULTHP;
	}

	public void addLauncher(Launcher launcher) {
		if(launcher !=  null)
			this.attackList.add(launcher);
	}
	
	public CopyOfHeroStats getHeroStats() {
		return this.safeStats;
	}
	
	public void setMaxHp(HealthEnum healthItem) {
		if(healthItem != null) {
			this.maxHP = DEFAULTHP + healthItem.getMoreHp();
		}
	}
	
	@Override
	public void launchAttack(Movement direction) {
		this.attackList.get(this.attackType).launch(this.getMyMap(), direction, this.getRow(), this.getColumn());
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
		else if(newHP > this.maxHP)
			newHP = this.maxHP;
		this.hp.set(newHP);
	
	}

	
	@Override
	public boolean isAlive() {
		return this.hp.get() > 0;
	}
	
	private void setAttackIndex(int newAttackIndex) {
		int nextAttackIndex = newAttackIndex;
		if(newAttackIndex >= this.attackList.size()) {
			nextAttackIndex = 0;
		}
		this.attackType = nextAttackIndex;
		updateAttackImage();
	}
	
	private void updateAttackImage() {
		if(this.attackType < this.attackList.size()) {
			int newImageValue = this.attackList.get(this.attackType).getImage();
			this.attackItemImage.set(newImageValue);
		}
	}

	protected void changeAttack() {
		setAttackIndex(this.attackType + 1);
	}
}
