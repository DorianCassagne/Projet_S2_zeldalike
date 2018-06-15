package model.character.hero;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.GameStatus;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.attack.dynamic.DefaultAttackLauncher;
import model.character.attack.dynamic.Launcher;
import model.character.item.attack.AttackItem;
import model.character.item.attack.AttackItemEnum;
import model.character.item.def.DefenseItemEnum;
import model.character.item.hpPotion.HealEnum;
import model.character.item.mp.MPItemEnum;
import model.character.item.mpPotion.MPPotionItemEnum;
import model.character.item.pvItem.HealthEnum;

public class HeroStats {
	private IntegerProperty def;
	private IntegerProperty defImage;
	private IntegerProperty hp;
	private IntegerProperty mp;
	private IntegerProperty attackItemImage;
	private IntegerProperty attackValue;
	private IntegerProperty maxHP;
	private IntegerProperty maxMP;
	
	private CopyOfHeroStats safeStats;
	private ArrayList<Launcher> attackList;
	private int attackType;


	
	public HeroStats(GameStatus gameStatus) {

		this.initialiseProperties(gameStatus);
		this.initAttack(gameStatus);
		this.safeStats = new CopyOfHeroStats(this.hp,this.def,this.defImage,this.attackItemImage,this.attackValue,this.mp,this.maxHP,this.maxMP);
	
	}

	
	/*
	 * Attaques
	 */
	
	private void initAttack(GameStatus gameStatus) {
		this.attackList = new ArrayList<Launcher>();
		this.attackValue = new SimpleIntegerProperty();
		this.attackItemImage = new SimpleIntegerProperty();		
		Launcher defaultLauncher = new DefaultAttackLauncher(GameHero.DEFAULTATKITEM);
		
		if(gameStatus != null) {
			defaultLauncher = new DefaultAttackLauncher(gameStatus.getAttackItem());
		}
		
		this.attackList.add(defaultLauncher);

		
		updateAttackImage();
	}
	
	public void addLauncher(Launcher launcher) {
		if(launcher !=  null) {
			this.attackList.add(launcher);
		}
	}

	public void setBasicAtk(AttackItemEnum attack) {
		if(attack != null) {
			this.attackList.set(0, new DefaultAttackLauncher(attack));
			this.updateAttackImage();
		}
		else
			throw new IllegalArgumentException("THE ATTACK CHANGE IS NULL");
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
			Launcher newImageValue = this.attackList.get(this.attackType);
			this.attackItemImage.set(newImageValue.getImage());
			this.attackValue.set(GameHero.DEFAULTATK + newImageValue.getDamage());
		}
		
	}

	public void changeAttack() {
		
		setAttackIndex(this.attackType + 1);
	
	}

	
	
	
	//Raison de ne pas confondre les deux ( Chargement du state se fasse en utilisant les setter dédiés
	private  void initialiseProperties(GameStatus gameStatus) {		
		this.hp = new SimpleIntegerProperty(GameHero.DEFAULTHP);
		this.mp = new SimpleIntegerProperty(GameHero.DEFAULTMP);

		if(gameStatus != null) {
			
			this.maxHP = new SimpleIntegerProperty(gameStatus.getMaxHP());
			this.maxMP = new SimpleIntegerProperty(gameStatus.getMaxMp());
			this.setHP(gameStatus.getHp());
			this.setMP(gameStatus.getMp());
			
		}else {
	
			this.maxHP = new SimpleIntegerProperty(GameHero.DEFAULTHP);
			this.maxMP = new SimpleIntegerProperty(GameHero.DEFAULTMP);

		}
		
		this.def = new SimpleIntegerProperty(GameHero.DEFAULTDEF);
		this.defImage = new SimpleIntegerProperty(824);
	
	}

	


	/*
	 * ITEMS EFFECT ON THESE ONES ............
	 */
	
	public void setMaxHp(HealthEnum healthItem) {
		if(healthItem != null) {
			this.maxHP.set(GameHero.DEFAULTHP + healthItem.getMoreHp());
		}
	}
	
	public void heal(HealEnum healer) {
		if(healer != null)
			this.setHP(this.hp.get() + healer.getHeal());
	}
	
	

	
	public void setBasicDef(DefenseItemEnum defenseItem) {
		if(defenseItem != null) {
			this.defImage.set(defenseItem.getImage());
			this.def.set(defenseItem.getMoreDef() + GameHero.DEFAULTDEF);
		}
	}
	
	public void increaseMP(MPItemEnum MPItem ) {
		if(MPItem != null) {
			this.maxMP.set(GameHero.DEFAULTMP + MPItem.getAdditionalMP());
		}
	}
	
	public void healMP(MPPotionItemEnum MPPotion) {
		this.setMP(this.mp.get() + MPPotion.getMPHeal());
	}

	public void getDmg(Attack attack) {
		this.hp.set(this.hp.get() - GameCharacter.calculateDamage(attack.getDamage(), this.def.get()));
	}
	
	
	private void setHP(int newHP) {
		correctlySetProperty(this.hp,newHP,this.maxHP.get());
	}
	
	private boolean setMP(int newMP) {
		correctlySetProperty(this.mp, newMP, this.maxMP.get());
		return newMP >= 0;
	}
	
	
	/*
	 * Getters
	 */
	
	public int getHP() {
		return this.hp.get();
	}

	public Launcher getCurrentAttack() {
		Launcher currentAttack = null;
		if(this.attackType >= 0) {
			currentAttack = this.attackList.get(this.attackType);
		}
		return currentAttack;
	}

	public CopyOfHeroStats getHeroStats() {
		return this.safeStats;
	}
	
	public int getAtk() {
		return this.attackValue.get();
	}


	private final static void correctlySetProperty(IntegerProperty property,int newValue,int maxValue) {
		
		if(newValue < 0) 
			newValue = 0;
		else if(newValue > maxValue)
			newValue = maxValue;
		property.set(newValue);

	}


}
