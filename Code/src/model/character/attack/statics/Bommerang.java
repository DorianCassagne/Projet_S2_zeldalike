package model.character.attack.statics;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Bommerang extends Attack {

	private int count;
	private boolean start;
	private Movement backward;
	public Bommerang(GameMap map, int row, int column, Movement direction) {
		super(map, 40, row, column, direction, 20, 1, 2, 1620, 10);
		count = 5;
		switch (direction.getIndex()) {
		case 0:
			backward = Movement.BOTTOM;
			break;
		case 1:
			backward = Movement.LEFT;
			break;
		case 2:
			backward = Movement.TOP;
			break;
		case 3:
			backward = Movement.RIGHT;
			break;
		default:
			backward = Movement.STAY;
			break;
		}
	}

	@Override
	public boolean handleMove(byte attackResult) {
		if(count!=0||!start) {
			count--;
			start=true;
		}
		else {
			count=5;
		}
		
		if (count == 0) {
			this.setDirection(backward);
		}
		return super.handleMove(attackResult);
	}
	
	public void establishAttack(GameCharacter gameCharacter) {

		gameCharacter.getDmg(this);
	}
	
	

	

	
}
