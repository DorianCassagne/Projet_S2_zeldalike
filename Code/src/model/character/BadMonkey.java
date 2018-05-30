package model.character;

import model.PathFinder.BFS1;
import model.character.attack.Attack;
import model.character.attack.AttackTest;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class BadMonkey extends GameCharacter{
	
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 10;
	private final static int DEFAULTCYCLE = 20;
	private final static int DEFAULTIMAGE = 20;
	private final static double DEFAULTCOEFFICIENT = 2;
	
	
	public BadMonkey(GameMap map, int startRow, int startColumn) {
		super(map, GameCharacter.ENEMYTYPE, DEFAULTHP, DEFAULTDEF, startRow, startColumn,DEFAULTCYCLE,DEFAULTCOEFFICIENT);
	}

	@Override
	public Move act() {
		
		
		int actualCell=GameMap.convertToCellId(this.getRow(), this.getColumn());
		//exmple pour attackmove attention a l'ordre des cases
		int[]tab= {
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow()+1,GameCharacter.getGameCharacter().getColumn()),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow()-1,GameCharacter.getGameCharacter().getColumn()),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn()+1),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn()-1),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow()+2,GameCharacter.getGameCharacter().getColumn()),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow()-2,GameCharacter.getGameCharacter().getColumn()),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn()+2),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn()-2)
		};
		
		int inPlace= inPlace(tab, actualCell);
		if (inPlace != -1) {
			switch (inPlace %4) {
			case 0:
				new AttackTest(getMyMap(), this.getRow()-1, this.getColumn(), Movement.TOP, 10);
				super.setWait(DEFAULTCYCLE*2);
				break;
			case 1:
				new AttackTest(getMyMap(), this.getRow()+1, this.getColumn(), Movement.BOTTOM, 10);
				super.setWait(DEFAULTCYCLE*2);
				break;
			case 2:
				new AttackTest(getMyMap(), this.getRow(), this.getColumn()-1, Movement.LEFT, 10);
				super.setWait(DEFAULTCYCLE*2);
				break;
			case 3:
				new AttackTest(getMyMap(), this.getRow(), this.getColumn()+1, Movement.RIGHT, 10);
				super.setWait(DEFAULTCYCLE*2);
				break;
			}
			return null;
		}
		else {
			
			int nextCell= BFS1.simpleMove(this.getMyMap(), 
					actualCell,
					tab, false, 0);
			
			if (actualCell==nextCell) {
				return null;
			}
			if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),GameMap.convertToRow(nextCell),GameMap.convertToColomn(nextCell))){
				return new Move(nextCell,this.getMoveCycle(), DEFAULTIMAGE);
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
	
	
	@Override
	public int getDefaultImage() {
		return DEFAULTIMAGE;
	}


}
