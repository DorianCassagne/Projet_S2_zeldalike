package model.character.attack;

import model.character.GameCharacter;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class AttackTest extends Attack{

	private final static int DEFAULTCYCLE = 20;
	private final static int DEFAULTCELLPERTURN = 1;
	private final static int DEFAULTCOEFFICIENT = 2;
	private final static int DEFAULTIMAGE = 1613;
	
	public AttackTest(GameMap map,  int row, int column, Movement direction, int damage) {
		super(map, DEFAULTCYCLE, row, column, direction, damage, DEFAULTCELLPERTURN,DEFAULTCOEFFICIENT);
		
	}	
	
	@Override
	public int getDefaultImage() {
		return DEFAULTIMAGE;
	}

	public void attaquePersonnage(GameCharacter character) {
		
	}
	
	@Override
	public int getDamage() {
		return 10;
	}

	@Override
	public Move act() {
		int endCellId = this.establishMove();
		return new Move(endCellId, this.getMoveCycle(), getDefaultImage());
	}

	
	public void attack(GameCharacter gameCharac) {
		gameCharac.getDmgAttacked(this);
		
	}
}
