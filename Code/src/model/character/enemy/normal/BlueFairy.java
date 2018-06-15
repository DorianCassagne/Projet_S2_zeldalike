package model.character.enemy.normal;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class BlueFairy extends EnemyNormal{
	
	private final static double DEFAULTCOEFFICIENT = 2;
	private final static int ATTACKITEMINDEX = 810;
	
	
	
	public BlueFairy(GameMap map, int startRow, int startColumn, int cycle, int defaultImage,int HP,int def,int damage,int score) {
		super(map, startRow, startColumn, cycle, DEFAULTCOEFFICIENT, defaultImage,HP,def,score);
		
//		if(HP <= 0 || def < 0 || damage <= 0 )
//			throw new IllegalArgumentException("SOMETHING WENT WRONG IN ENEMY");
//		this.hp = HP;
//		this.def = def;
	}

//	@Override
//	public void getDmg(Attack attack) {
//		this.setHP(this.hp - GameCharacter.calculateDamage(attack.getDamage(), def));
//	}
	
//	public int getHp() {
//		return this.hp;
//	}
	
//	private void setHP(int newHP) {
//		if(newHP < 0 )
//			newHP = 0;
//		this.hp = newHP;
//	}
//
//	@Override
//	public boolean isAlive() {
//		return this.hp > 0 ;
//	}

	@Override
	public void launchAttack(Movement direction) {
		int cellId = BFS1.simpleMove(getMyMap(), this.getCellId(), GameCharacter.getHero().getCellId(),true);
		Movement nextDirection = Movement.getDirectionInto(this.getCellId(), cellId);
		//new DefaultAttack(this.getMyMap(), this.getRow(), this.getColumn(),	nextDirection,new AttackItem(ATTACKITEMINDEX));
	}

	@Override
	protected Move act() {
		return null;                                               
	}

	@Override
	public String getName() {
		return "BLUEFAIRY";
	}


}
