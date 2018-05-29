package model.character;

import model.PathFinder.BFS1;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class BadMonkey extends GameCharacter{
	
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 10;
	private final static int DEFAULTCYCLE = 4;
	private final static int DEFAULTIMAGE = 20;
	private final static double DEFAULTCOEFFICIENT = 2;
	
	
	public BadMonkey(GameMap map, int startRow, int startColumn) {
		super(map, GameCharacter.ENEMYTYPE, DEFAULTHP, DEFAULTDEF, startRow, startColumn,DEFAULTCYCLE,DEFAULTCOEFFICIENT);
	}

	@Override
	public Move act() {
		//Move nextMove = null;
		int actualCell=GameMap.convertToCellId(this.getRow(), this.getColumn());
		
		//exmple pour attackmove
		int[]tab= {
				//GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn())
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow()-2,GameCharacter.getGameCharacter().getColumn()),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow()+2,GameCharacter.getGameCharacter().getColumn()),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn()-2),
				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn()+2)
		};
		int nextCell= BFS1.simpleMove(this.getMyMap(), 
				actualCell,
				tab, false);
	
		
//		int nextCell= BFS1.simpleMove(this.getMyMap(), 
//				actualCell,
//				GameMap.convertToCellId(GameCharacter.getGameCharacter().getRow(),GameCharacter.getGameCharacter().getColumn()));
		if (actualCell==nextCell) {
			return null;
		}
		
		if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),GameMap.convertToRow(nextCell),GameMap.convertToColomn(nextCell))){
			return new Move(nextCell,this.getMoveCycle(), DEFAULTIMAGE);
		}
		
		return null;
		
		//return nextMove;
	}

	
	@Override
	public void launchAttack(Movement movement) {
		//this.lastHeroPosition = movement;
	}
	
	
	
	
	@Override
	public int getDefaultImage() {
		return DEFAULTIMAGE;
	}


}
