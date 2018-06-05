package model.character.attack.statics;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.cell.Cell;
import model.gameMap.move.Movement;

public class Bomrang extends Attack {

	public Bomrang(GameMap map, int row, int column, Movement direction) {
		super(map, 100, row, column, direction,100, 1, 2, 9, 10);
		this.setWait(30);
	}

	@Override
	public void establishAttack(GameCharacter gameCharacter) {
		
		Movement direction1 = Movement.values()[(this.getDirection().getIndex() + 1)%4 ];
		Movement direction2 = Movement.values()[(this.getDirection().getIndex() + 3)%4];
		new AttackTest(this.getMyMap(),this.getRow(),this.getColumn(),direction1,80);
		new AttackTest(this.getMyMap(),this.getRow(),this.getColumn(),direction2,80);

	}
	
	

	

	
}
