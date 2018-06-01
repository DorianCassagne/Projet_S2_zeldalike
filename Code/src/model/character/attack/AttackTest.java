package model.character.attack;

import model.character.GameCharacter;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class AttackTest extends Attack{

	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTCELLPERTURN = 1;
	private final static double DEFAULTCOEFFICIENT = 2;
	private final static int DEFAULTIMAGE = 1612;
	
	
	public AttackTest(GameMap map,  int row, int column, Movement direction, int damage) {
		super(map, DEFAULTCYCLE, row, column, direction, damage, DEFAULTCELLPERTURN,DEFAULTCOEFFICIENT,DEFAULTIMAGE);
	}	
	

	public void attaquePersonnage(GameCharacter character) {
		
	}

	@Override
	public Move act() {
		int endCellId = this.establishMove();
		return new Move(endCellId, this.getMoveCycle());
	}

	
	public void attack(GameCharacter gameCharac) {
		gameCharac.getDmgAttacked(this);
	}

	@Override
	public boolean handlePlay(byte attackResult) {
		boolean isNotDead = false;

		return isNotDead;
		
	}
}
