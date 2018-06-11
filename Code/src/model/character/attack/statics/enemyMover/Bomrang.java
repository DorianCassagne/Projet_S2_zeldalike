package model.character.attack.statics.enemyMover;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Bomrang extends Attack {

	private final static int CYCLE= 10;
	private final static int DMG=20;
	private final static int CELLPERTURN=1;
	private final static double COEF=20;
	private final static int IMG=20;
	private final static int MAXDIST=20;
	
	
	public Bomrang(GameMap map, int row, int column, Movement direction) {
		super(map, CYCLE, row, column, direction, DMG, CELLPERTURN, COEF, IMG, MAXDIST);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		// TODO Auto-generated method stub
		
	}



}
