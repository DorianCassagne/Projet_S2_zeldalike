/*
 * Classe attaque : Missile a tete chercheuse (utilise BFS)
 */

package model.character.attack.statics.boss;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Missile extends Attack{

	private final static int CYCLE= 20;
	private final static int DMG=20;
	private final static int CELLPERTURN=1;
	private final static double COEF=20;
	private final static int IMG=1640;		
	private final static int MAXDIST=10;	// distance maximale de temps de vie
	private boolean change;					// change son image
	private int count;
	public Missile(GameMap map, int row, int column, Movement direction) {
		super(map, CYCLE, row, column, direction, DMG, CELLPERTURN, COEF, IMG, MAXDIST);
		count=MAXDIST/2;
		change=false;
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
	}
	
	@Override
	public boolean handleMove(byte i){
		int actualCell = this.getCellId()+this.getDirection().getHorizontalIncrement()+this.getDirection().getVerticalIncrement()*MapReader.MAPLENGTH;
		int nextCell = BFS1.simpleMove(getMyMap(), actualCell,GameCharacter.getHero().getCellId(), false);
		if (actualCell==nextCell)
			nextCell=GameCharacter.getHero().getCellId();
		count--;
		if(!change && this.count==0)
			change=true;
		
		if(actualCell + MapReader.MAPLENGTH == nextCell) {
			this.setDirection(Movement.BOTTOM);
		}
		
		else if(actualCell - MapReader.MAPLENGTH == nextCell) {
			this.setDirection(Movement.TOP);
		}
		
		else if(actualCell + 1 == nextCell) {
			this.setDirection(Movement.RIGHT);
		}
		
		else if(actualCell - 1 == nextCell) {
			this.setDirection(Movement.LEFT);
		}
		
		if(!change)
			this.setImage(this.getDirection());
		else
			this.setImage(this.getDirection().getIndex()-8);
		
		return true;
		
	}
}
