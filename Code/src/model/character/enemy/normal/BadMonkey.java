package model.character.enemy.normal;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.character.attack.statics.hero.bomb.Bomb;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class BadMonkey extends BlueFairy{
	
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 10;
	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTIMAGE = 16;
	private final static int DEFAULTDMG = 90;
	private final static int DEFAULTSCORE = 100;
	
	
	public BadMonkey(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn,DEFAULTCYCLE,DEFAULTIMAGE,DEFAULTHP, DEFAULTDEF,DEFAULTDMG,DEFAULTSCORE);
	}

	@Override
	public Move act() {
		
		int row = GameCharacter.getHero().getRow() ;
		int column = GameCharacter.getHero().getColumn();
		int actualCell= Statics.convertToCellId(this.getRow(), this.getColumn());
		//exmple pour attackmove attention a l'ordre des cases
		int[]tab= {
				Statics.convertToCellId(row+1,column),
				Statics.convertToCellId(row,column-1),
				Statics.convertToCellId(row-1,column),
				Statics.convertToCellId(row,column+1),
//				Statics.convertToCellId(row+2,column),
//				Statics.convertToCellId(row,column-2),
//				Statics.convertToCellId(row-2,column),
//				Statics.convertToCellId(row,column+2),
		};
		
		int inPlace= inPlace(tab, actualCell);
		if (inPlace != -1) {
			Movement currentMovement = Movement.values()[inPlace];
			this.setImage(currentMovement);
			//new DoomAttack(getMyMap(),this.getRow(),this.getColumn(),currentMovement, 10);
			new Bomb(getMyMap(),this.getRow(),this.getColumn(),currentMovement,10);
			//new NyanAttHori(getMyMap(), this.getRow(), this.getColumn());
			setWait(200);
			return null;
		}
		else {
			int nextCell= BFS1.simpleMove(this.getMyMap(), 
					actualCell,
					tab, true, 3);
			
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
	
	@Override
	public String getName() {
		return "BADMONKEY";
	} 

	
	
	private int inPlace(int[] tab, int actualCell) {
		int ret=-1;
		for (int i = 0; i < tab.length; i++) {
			if(tab[i]==actualCell)
				ret=i;
		}

		return ret%4;
	}

	
	


}