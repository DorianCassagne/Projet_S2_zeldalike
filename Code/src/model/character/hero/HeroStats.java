package model.character.hero;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.attack.dynamic.DefaultAttackLauncher;
import model.character.attack.dynamic.Launcher;
import model.character.item.attack.AttackItemEnum;
import model.character.item.def.DefenseItemEnum;
import model.character.item.hpPotion.HealEnum;
import model.character.item.mp.MPItemEnum;
import model.character.item.mpPotion.MPPotionItemEnum;
import model.character.item.pvItem.HealthEnum;

public class HeroStats {
	private IntegerProperty def;
	private IntegerProperty hp;
	private IntegerProperty mp;
	private CopyOfHeroStats safeStats;
	private IntegerProperty attackItemImage;
	private IntegerProperty attackValue;
	private ArrayList<Launcher> attackList;
	
	private int attackType;
	private int maxHP;
	private int maxMP;

	
	public HeroStats() {

		this.initialiseProperties();
		this.initAttack();

	}

	
	private void initAttack() {
		this.attackList = new ArrayList<Launcher>();
		this.setAttackIndex(0);
		this.attackList.add(new DefaultAttackLauncher(GameHero.DEFAULTATKITEM));
	}

	private  void initialiseProperties() {

		this.hp = new SimpleIntegerProperty(GameHero.DEFAULTHP);
		this.def = new SimpleIntegerProperty(GameHero.DEFAULTDEF);
		this.attackItemImage = new SimpleIntegerProperty(0);
		this.mp = new SimpleIntegerProperty(GameHero.DEFAULTMP);
		this.attackValue = new SimpleIntegerProperty(GameHero.DEFAULTATK);
		this.safeStats = new CopyOfHeroStats(this.hp,this.def,this.attackItemImage,this.attackValue,this.mp);
		this.maxHP = GameHero.DEFAULTHP;
		this.maxMP = GameHero.DEFAULTMP;
	}

	
	public void addLauncher(Launcher launcher) {
		if(launcher !=  null) {
			this.attackList.add(launcher);
		}
	}


	/*
	 * ITEMS EFFECT ON THESE ONES ............
	 */
	
	public void setMaxHp(HealthEnum healthItem) {
		if(healthItem != null) {
			this.maxHP = GameHero.DEFAULTHP + healthItem.getMoreHp();
			this.setHP(this.hp.get());
		}
	}
	
	public void heal(HealEnum healer) {
		if(healer != null)
			this.setHP(this.hp.get() + healer.getHeal());
	}
	
	public void setBasicAtk(AttackItemEnum attack) {
		if(attack != null) {
			this.attackList.set(0, new DefaultAttackLauncher(attack));
			this.updateAttackImage();
		}
		else
			throw new IllegalArgumentException("THE ATTACK CHANGE IS NULL");
	}
	
	public void setBasicDef(DefenseItemEnum defenseItem) {
		if(defenseItem != null) {
			this.def.set(defenseItem.getMoreDef() + GameHero.DEFAULTDEF);
		}
	}
	
	public void increaseMP(MPItemEnum MPItem ) {
		if(MPItem != null) {
			this.maxMP = GameHero.DEFAULTMP + MPItem.getAdditionalMP();
			this.setMP(this.mp.get());
		}
	}
	
	public void healMP(MPPotionItemEnum MPPotion) {
		this.setMP(this.mp.get() + MPPotion.getMPHeal());
	}

	public void getDmg(Attack attack) {
		this.hp.set(this.hp.get() - GameCharacter.calculateDamage(attack.getDamage(), this.def.get()));
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

	private void setHP(int newHP) {
		correctlySetProperty(this.hp,newHP,this.maxHP);
	}
	
	private boolean setMP(int newMP) {
		correctlySetProperty(this.mp, newMP, this.maxMP);
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
		if(this.attackType > 0) {
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
