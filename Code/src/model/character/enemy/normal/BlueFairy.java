package model.character.enemy.normal;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
/*
 * Super Classe BlueFairy (?) qui �tends EnemyNormal
 * Contenant deux constantes
 * 
 */
public class BlueFairy extends EnemyNormal{
	
	private final static double DEFAULTCOEFFICIENT = 2;
	
	
	
	public BlueFairy(GameMap map, int startRow, int startColumn, int cycle, int defaultImage,int HP,int def,int damage,int score) {
		super(map, startRow, startColumn, cycle, DEFAULTCOEFFICIENT, defaultImage,HP,def,score);
	}

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
