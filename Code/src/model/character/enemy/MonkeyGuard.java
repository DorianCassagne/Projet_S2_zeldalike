package model.character.enemy;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.character.attack.statics.Bomb;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
/*
 * ennemi qui lorsqu'il spawn marche tout droit devant lui jusqu'a atteindre un obstacle 
 * et retourne vers sa position initiale pour repeter le meme processus
 * tant que le joueur n'est pas a sa portee (5 par ex)
 * 
 * ou alors 
 * ennemi qui lorsqu'il spawn se dirige vers une position précise puis ensuite retourne a se position initiale
 * tant que le joueur n'est pas a sa portee 
 * 
 * lorsque le joueur est a sa portee, vitesse doublee et attaque au corps a corps
 */
public class MonkeyGuard extends Enemy{
	
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 10;
	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTIMAGE = 16;

	
	public MonkeyGuard(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn,DEFAULTCYCLE,DEFAULTIMAGE,DEFAULTHP, DEFAULTDEF,90);
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
				Statics.convertToCellId(row,column+1)
		};
		
		int inPlace= inPlace(tab, actualCell);
		if (inPlace != -1) {
			Movement currentMovement = Movement.values()[inPlace];
			this.setImage(currentMovement);
			
			new Bomb(getMyMap(),this.getRow(),this.getColumn(),currentMovement);
			
			setWait(200);
			return null;
		}
		else {
	
			int nextCell= BFS1.simpleMove(this.getMyMap(),actualCell,tab, true, 0);
			
			if (actualCell==nextCell) {
				return null;
			}
			if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),Statics.convertToRow(nextCell),Statics.convertToColomn(nextCell))){
				return new Move(nextCell,this.getMoveCycle());
			}
			
			return null;
		}
	}
	
	private int inPlace(int[] tab, int actualCell) {
		int ret=-1;
		for (int i = 0; i < tab.length; i++) {
			if(tab[i]==actualCell)
				ret=i;
		}

		return ret%4;
	}
	
	@Override
	public void launchAttack(Movement movement) {
	}


}
