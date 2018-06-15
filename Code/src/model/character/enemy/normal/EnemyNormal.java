package model.character.enemy.normal;

import model.character.attack.Attack;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;

public abstract class EnemyNormal extends Enemy{

	
	private int def;
	private int hp; 

	
	public EnemyNormal(GameMap map, int startRow, int startColumn, int cycle, double coefficient, int defaultImage,int hp,int def,int score) {
		super(map, startRow, startColumn, cycle, coefficient, defaultImage,score);
		if(hp > 0 && def > 0 ) {
			this.hp = hp;
			this.def = def;
		}else
			throw new IllegalArgumentException("ENEMY HP AND DEF MUST BE > 0");
	}

	

	@Override
	public void getDmg(Attack attack) {
		this.setHP(this.hp - calculateDamage(attack.getDamage(), this.def));
	}

	
	private void setHP(int newHP) {
		if(newHP < 0)
			newHP = 0;
		this.hp = newHP;
	}
	

	@Override
	public boolean isAlive() {
		return this.hp > 0;
	}
	
	@Override
	public int getHP() {
		return this.hp;
	}

	public String getName() {
		return "ENEMYNORMAL";
	}
}
