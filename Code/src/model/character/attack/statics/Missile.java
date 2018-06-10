package model.character.attack.statics;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Missile extends Attack{

	private final static int CYCLE= 10;
	private final static int DMG=20;
	private final static int CELLPERTURN=1;
	private final static double COEF=20;
	private final static int IMG=1640;
	private final static int MAXDIST=8;
	private int count;
	public Missile(GameMap map, int row, int column, Movement direction) {
		super(map, CYCLE, row, column, direction, DMG, CELLPERTURN, COEF, IMG, MAXDIST);
		count = MAXDIST;
	}
	private Missile(GameMap map, int row, int column, Movement direction, boolean useless) {
		super(map, CYCLE, row, column, direction, DMG, CELLPERTURN, COEF, IMG+8, MAXDIST);
		count=-1;
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		System.out.println("bim dans ta gueule");
		gameCharacter.getDmg(this);
	}
	
	@Override
	public Move act() {
		if ( count == 0 ) {
			new Missile(getMyMap(), this.getRow(), this.getColumn(), this.getDirection(), false);
			return null;
		}
		int nextCell =BFS1.simpleMove(getMyMap(), this.getCellId(),GameCharacter.getHero().getCellId(), false);
		count--;
		System.out.println("miss"+ count);
		return new Move(nextCell,this.getMoveCycle());
		
	}

}
