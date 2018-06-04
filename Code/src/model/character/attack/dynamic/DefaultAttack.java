package model.character.attack.dynamic;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.item.attack.AttackItem;
import model.gameMap.GameMap;
import model.gameMap.cell.Cell;
import model.gameMap.move.Movement;

public class DefaultAttack extends Attack{

	private final static int DEFAULTCYCLE = 20;
	private final static double DEFAULTCOEFFICIENT = 1.6;
	private final static int CELLPERTURN = 1;
	
	public DefaultAttack(GameMap map, int row, int column, Movement direction,AttackItem attackType) {
		super(map, DEFAULTCYCLE, row, column, direction, attackType.getDamage(), CELLPERTURN, DEFAULTCOEFFICIENT, attackType.getAttackImage(), attackType.getMaxDistance());
	}

	@Override
	public boolean handlePlay(byte attackResult) {
		return attackResult == Cell.NOEFFECT || attackResult == Cell.ITEMISPRESENT;
	}

	@Override
	public void attack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
	}
	
	
	

	
}
