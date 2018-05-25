package model.character;

import model.character.attack.AttackTest;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Dolphin extends GameCharacter{
	
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 10;
	private final static int DEFAULTCYCLE = 30;
	private final static int DEFAULTIMAGE = 10;
	private final static int DEFAULTCOEFFICIENT = 2;
	
	private Movement lastHeroPosition;
	
	public Dolphin(GameMap map, int startRow, int startColumn) {
		super(map, GameCharacter.ENEMYTYPE, DEFAULTHP, DEFAULTDEF, startRow, startColumn,DEFAULTCYCLE,DEFAULTCOEFFICIENT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move act() {
		Move nextMove = null;
		if(this.lastHeroPosition != null)
			new AttackTest(getMyMap(),this.getRow(),this.getColumn(),this.lastHeroPosition,80);
		return nextMove;
	}

	
	@Override
	public void launchAttack(Movement movement) {
		this.lastHeroPosition = movement;
	}
	
	
	
	
	@Override
	public int getDefaultImage() {
		return DEFAULTIMAGE;
	}


}
