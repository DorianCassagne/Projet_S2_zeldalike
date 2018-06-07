package model.character.enemy;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.attack.statics.AttackTest;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public abstract class  Enemy extends GameCharacter{
	
	private final static double DEFAULTCOEFFICIENT = 2;

	
	private int def;
	private int hp; 
	private int damage;
	
	public Enemy(GameMap map, int startRow, int startColumn, int cycle, int defaultImage,int HP,int def,int damage) {
		super(map, startRow, startColumn, cycle, DEFAULTCOEFFICIENT, defaultImage);
		if(HP <= 0 || def < 0 || damage <= 0 )
			throw new IllegalArgumentException("SOMETHING WENT WRONG IN ENEMY");
		this.hp = HP;
		this.def = def;
		this.damage = damage;
	}

	@Override
	public void getDmg(Attack attack) {
		this.setHP(this.hp - GameCharacter.calculateDamage(attack.getDamage(), def));
	}
	
	public int getHp() {
		return this.hp;
	}
	
	private void setHP(int newHP) {
		if(newHP < 0 )
			newHP = 0;
		this.hp = newHP;
	}

	@Override
	public boolean isAlive() {
		return this.hp > 0 ;
	}

	@Override
	public void launchAttack(Movement direction) {
		new AttackTest(getMyMap(), getRow(), getColumn(), direction, damage);
	}


}
