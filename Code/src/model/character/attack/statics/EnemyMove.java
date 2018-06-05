package model.character.attack.statics;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.cell.Cell;
import model.gameMap.move.Movement;

public class EnemyMove extends Attack{

	private static final int DEFAULTCYCLE = 10;
	private static final int DAMAGE = 0;
	private static final double COEFFICIENT = 1.3;
	private static final int DEFAULTIMAGE = 12 ;
	
	public EnemyMove(GameMap map,  int row, int column, Movement direction, int cellPerTurn,
			  int maxDistance) {
		super(map, DEFAULTCYCLE, row, column, direction, DAMAGE, cellPerTurn, COEFFICIENT, DEFAULTIMAGE, maxDistance);
	}

	public boolean handlePlay(byte attackResult) {
		return attackResult == Cell.NOEFFECT;
	}

	@Override
	public void attack(GameCharacter gameCharacter) {
		Movement direction = this.getDirection();
		int newRow = gameCharacter.getRow() + direction.getVerticalIncrement() ;
		int newColumn = gameCharacter.getColumn() + direction.getHorizontalIncrement();
		this.getMyMap().changeCell(gameCharacter, gameCharacter.getRow(), gameCharacter.getColumn(), newRow, newColumn);
	}

}
