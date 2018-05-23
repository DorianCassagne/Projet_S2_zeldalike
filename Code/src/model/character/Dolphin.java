package model.character;

import model.gameMap.GameMap;
import model.gameMap.move.Move;

public class Dolphin extends GameCharacter{
	
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 150;
	
	
	public Dolphin(GameMap map, int startRow, int startColumn) {
		super(map, GameCharacter.ENEMYTYPE, DEFAULTHP, DEFAULTDEF, startRow, startColumn,50);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Move turn() {
		this.oneTurn();
		if(canAct()) {
				int row = this.getRow() + 1;
				int column = this.getColumn()+1 ;
			if (this.getMyMap().changeCell(this, this.getRow(), this.getColumn(), row, column)) {
				return new Move(GameMap.convertToCellId(row, column),this.getCycle()/getCoefficient(), 10);
			}
		}
		return null;
	}

	
	
	public int getCoefficient() {
		return 2;
	}
	
	
	
	@Override
	public int getDefaultImage() {
		return 10;
	}

}
