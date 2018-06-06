package model.character.attack.statics.enemyMover;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.cell.Cell;
import model.gameMap.move.ExternalMover;
import model.gameMap.move.Movement;

public class EnemyMove extends Attack implements ExternalMover{

	private static final int DEFAULTCYCLE = 10;
	private static final int DAMAGE = 0;
	private static final double COEFFICIENT = 1.3;
	private static final int DEFAULTIMAGE = 1624 ;
	private final static int DEFAULTCELLPERTURN = 1;
	private final static int DEFAULTMAXDISTANCE = 4;
	
	
	public EnemyMove(GameMap map,  int row, int column, Movement direction) {
		super(map, DEFAULTCYCLE, row, column, direction, DAMAGE, DEFAULTCELLPERTURN, COEFFICIENT, DEFAULTIMAGE, DEFAULTMAXDISTANCE);
	}

	public boolean handlePlay(byte attackResult) {
		return attackResult % Cell.NOEFFECT != 0;
	}
	

	@Override
	public int getSpeed() {
		return 20;
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		
		Movement direction = this.getDirection();
		int newRow = gameCharacter.getRow() + direction.getVerticalIncrement() ;
		int newColumn = gameCharacter.getColumn() + direction.getHorizontalIncrement();
		this.getMyMap().changeCell(gameCharacter, gameCharacter.getRow(), gameCharacter.getColumn(), newRow, newColumn,this);
		
	}

}
