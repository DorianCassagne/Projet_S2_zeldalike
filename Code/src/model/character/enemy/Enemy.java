package model.character.enemy;

import model.character.GameCharacter;
import model.gameMap.GameMap;

public abstract class Enemy extends GameCharacter{

	private int score;
	
	public Enemy(GameMap map, int startRow, int startColumn, int cycle, double coefficient, int defaultImage,int score) {
		super(map, startRow, startColumn, cycle, coefficient, defaultImage);
		this.score = score;
	}

	
	@Override
	protected void removeCharacter() {
		this.getMyMap().delEnemy(this);
	}

	public int getScore() {
		return this.score;
	}
	
	
	
	public abstract int getHP() ;
	
	public abstract String getName();
	
}
