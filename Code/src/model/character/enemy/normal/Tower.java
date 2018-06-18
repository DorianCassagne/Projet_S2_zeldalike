package model.character.enemy.normal;

import model.character.attack.statics.enemy.TowerAttack;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
/*
 * Classe ennemi qui attaque a l'aide de missile vers la direction du joueur mais pas forcement sa position
 */
public class Tower extends EnemyNormal{

	private static int DEFAULTIMAGE = 80;
	private static int HP = 300;
	private static int DEF = 50;
	private static int SCORE = 200;
	private static double DEFAULTCOEFFICIENT = 1.3;
	private static int CYCLE = 200;

	
	private int turnCounter;
	
	
	public Tower(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn, CYCLE, DEFAULTCOEFFICIENT, DEFAULTIMAGE, HP, DEF, SCORE);
		this.turnCounter = 0;
	}

	@Override
	public String getName() {
		return "TOWER";
	}

	@Override
	public void launchAttack(Movement move) {
		new TowerAttack(this.getMyMap(), this.getRow(), this.getColumn(), move);
	}

	@Override
	protected Move act() {

		Movement nextMovement = Movement.getMovementInOrder(this.turnCounter);

		this.setImage(nextMovement);
		this.launchAttack(nextMovement);
		
		turnCounter++;
		turnCounter = turnCounter % 8;

		return null;
	}
	
}
