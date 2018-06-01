
package model.character.attack;

import model.character.GameCharacter;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class SimpleArrow extends Attack {
 

	private final static int DEFAULTCYCLE = 9;
	private final static int DEFAULTCELLPERTURN = 1;
	private final static int DEFAULTIMAGE = 8;
	
	public SimpleArrow(GameMap map, int row, int column, Movement direction, int damage, int cellPerTurn) {
		super(map, DEFAULTCYCLE, row, column, direction, damage, DEFAULTCELLPERTURN, 2,DEFAULTIMAGE);
	}

	@Override
	public void attack(GameCharacter gameCharac) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Move act() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean handlePlay(byte attackResult) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public Move turn() {
//		this.oneTurn();
//		if(this.canAct()) {
//			if(disappear) {
//				this.getMyMap().delMovable(this, this.getRow(), this.getColumn());
//			}
//			int endCellId = this.establishMove();
//			this.setCellId(GameMap.convertIdToRow(endCellId), GameMap.convertIdToColunm(endCellId));
//			int attackResult=getMyMap().playAttack(this, endCellId);
//			if (attackResult % 3 == 0 || attackResult % 7 == 0) {
//				disappear=true;
//				return new Move(endCellId, this.getCycle()/2, this.getDefaultImage());
//			}
//			return new Move(endCellId, this.getCycle()/2, this.getDefaultImage());
//		}
//		
//		
//		return null;
//	}
//
//	@Override
//	public int getDefaultImage() {
//		switch (this.getDirection().getDir()) {
//		case Movement.UPDIR:
//			return IMG;
//		case Movement.DOWNDIR:
//			return IMG+1;
//		case Movement.LEFTDIR:
//			return IMG+2;
//		case Movement.RIGHTDIR:
//			return IMG+3;
//		default:
//			return IMG;
//		}
//	}
//
//	@Override
//	public void attack(GameCharacter gameCharac) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public Move act() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}