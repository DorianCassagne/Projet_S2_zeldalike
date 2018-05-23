package model.character.attack;

import model.character.GameCharacter;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class AttackTest extends Attack{

	private final static int DEFAULTCYCLE = 20;
	private final static int DEFAULTCELLPERTURN = 1;
	
	public AttackTest(GameMap map,  int row, int column, Movement direction, int damage) {
		super(map, DEFAULTCYCLE, row, column, direction, damage, DEFAULTCELLPERTURN);
		
	}

	@Override
	public Move turn() {
		this.oneTurn();
		if(this.canAct()) {
			int endCellId = this.establishMove();
			return new Move(endCellId, this.getCycle(), 10);
		}
		return null;
	}
	
	@Override
	public int getDefaultImage() {
		return 11;
	}

	public void attaquePersonnage(GameCharacter character) {
		
	}

}
