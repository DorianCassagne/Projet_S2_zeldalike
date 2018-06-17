package model.character.enemy;

import model.character.GameCharacter;
import model.gameMap.GameMap;

/*
 * Classe abstract Enemy qui étend GameCharacter
 * Un ennemy donne un certain nombre de point (score) lors de sa mort
 */
public abstract class Enemy extends GameCharacter{

	private int score;
	
	public Enemy(GameMap map, int startRow, int startColumn, int cycle, double coefficient, int defaultImage,int score) {
		super(map, startRow, startColumn, cycle, coefficient, defaultImage);
		this.score = score;
	}

	
	@Override
	protected void removeCharacter() {
		this.getMyMap().delEnemy(this, this.getRow(), this.getColumn());
	}

	public int getScore() {
		return this.score;
	}

	public abstract int getHP() ;
	
	public abstract String getName();
	
}
