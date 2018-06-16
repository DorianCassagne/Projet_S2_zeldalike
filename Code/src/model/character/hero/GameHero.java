
package model.character.hero;


import model.character.item.attack.*;
import model.character.item.def.*;
import model.character.item.mp.*;
import model.character.item.hpPotion.*;
import model.character.item.mpPotion.*;
import model.character.item.pvItem.*;
import model.character.item.speed.SpeedItemEnum;
import model.character.attack.dynamic.*;
import model.GameStatus;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.attack.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public abstract class GameHero extends GameCharacter {
	
	 final static int DEFAULTCYCLE = 20;
	 final static double DEFAULTCOEFFICIENT = 1.5;
	 final static int DEFAULTHP = 30000;
	 final static int DEFAULTMP = 200;
	 final static int DEFAULTDEF = 40;
	 final static int DEFAULTATK = 50;
	 final static int DEFAULTIMAGE = 8;
	 final static AttackItemEnum DEFAULTATKITEM = AttackItemEnum.LANCER;
	 
	private HeroStats heroStats;
		
	public GameHero(GameMap map, int startRow, int startColumn,GameStatus gameStatus) {
		super(map, startRow, startColumn, DEFAULTCYCLE, DEFAULTCOEFFICIENT, DEFAULTIMAGE);
		this.heroStats = new HeroStats(gameStatus);
		this.loadState(gameStatus);
	}
				
		
	@Override
	public void getDmg(Attack attack) {
		this.heroStats.getDmg(attack);
	}
	
	
	@Override
	public boolean isAlive() {
		return this.heroStats.getHP() > 0;
	}

	public void setBasicDef(DefenseItemEnum defenseItem) {
		this.heroStats.setBasicDef(defenseItem);
	}
	
	public void heal(HealEnum healer) {
		this.heroStats.heal(healer);
	}
	
	public void increaseMP(MPItemEnum MPItem) {
		this.heroStats.increaseMP(MPItem);
	}
	
	public void healMP(MPPotionItemEnum MPPotion) {
		this.heroStats.healMP(MPPotion);
	}
	
	public void setMaxHp(HealthEnum healthItem) {
		this.heroStats.setMaxHp(healthItem);
	}
	
	public void addLauncher(Launcher launcher) {
		this.heroStats.addLauncher(launcher);
	}
	
	public void setBasicAtk(AttackItemEnum attackItem) {
		this.heroStats.setBasicAtk(attackItem);
	}
	
	@Override
	public void launchAttack(Movement direction) {
		Launcher attack = this.heroStats.getCurrentAttack();
		if(attack != null) {
			attack.launch(this.getMyMap(), direction, this.getRow(), this.getColumn(),this.heroStats.getAtk());
		}
	}
	
	public CopyOfHeroStats getHeroStats() {
		return this.heroStats.getHeroStats();
	}
	
	public void setSpeed(SpeedItemEnum speedItem) {
		if(this.getMoveCycle() == DEFAULTCYCLE) {
			int newSpeed = DEFAULTCYCLE * (1 - speedItem.getItemSpeed()/100);
			this.setWait(newSpeed);

		}
	}
	
	public GameStatus getGameStatus() {
		
		CopyOfHeroStats myStats = this.heroStats.getHeroStats();
		GameStatus status = new GameStatus(myStats,this.getRow(),this.getColumn(),0,this.getMyMap().getMapId());
		return status;
	}
	
	
	//@throws IllegalArgumentException
	private void loadState(GameStatus mapStat) {
		
		if(mapStat != null) {
			AttackItem attackItem = new AttackItem(mapStat.getAttackItem());
			attackItem.effectOn(this);
			
			DefenseItem defenseItem = new DefenseItem(mapStat.getDefenseItem());
			defenseItem.effectOn(this);
		}
	
	}

	
	
}
