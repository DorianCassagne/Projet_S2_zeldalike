package model.character;

import model.PathFinder.BFS1;
import model.character.attack.AttackTest;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class BadMonkey extends GameCharacter{
	
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 10;
	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTIMAGE = 16;
	private final static double DEFAULTCOEFFICIENT = 2;
	
	
	public BadMonkey(GameMap map, int startRow, int startColumn) {
		super(map, DEFAULTHP, DEFAULTDEF, startRow, startColumn,DEFAULTCYCLE,DEFAULTCOEFFICIENT,DEFAULTIMAGE);
	}

	@Override
	public Move act() {
		
		int row = GameCharacter.getGameCharacter().getRow() ;
		int column = GameCharacter.getGameCharacter().getColumn();
		int actualCell= Statics.convertToCellId(this.getRow(), this.getColumn());
		//exmple pour attackmove attention a l'ordre des cases
		int[]tab= {
				Statics.convertToCellId(row+1,column),
				Statics.convertToCellId(row,column-1),
				Statics.convertToCellId(row-1,column),
				Statics.convertToCellId(row,column+1),
		};
		
		int inPlace= inPlace(tab, actualCell);
		if (inPlace != -1) {
			Movement currentMovement = Movement.values()[inPlace];
			this.setImage(currentMovement);
			new AttackTest(getMyMap(),this.getRow(),this.getColumn(),currentMovement,10);
			return null;
		}
		else {
	
			int nextCell= BFS1.simpleMove(this.getMyMap(), 
					actualCell,
					tab, false, 0);
			
			if (actualCell==nextCell) {
				return null;
			}
			if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),Statics.convertToRow(nextCell),Statics.convertToColomn(nextCell))){
				return new Move(nextCell,this.getMoveCycle());
			}
			
			return null;
		}
	}

	
	@Override
	public void launchAttack(Movement movement) {
	}
	
	private int inPlace(int[] tab, int actualCell) {
		int ret=-1;
		for (int i = 0; i < tab.length; i++) {
			if(tab[i]==actualCell)
				ret=i;
		}

		return ret;
	}
	
	


}
