package model.character.attack.statics.boss;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Movement;

public class NyanHorRainbow extends Attack{
	
	
	
	protected NyanHorRainbow(GameMap map,int cycle, int row, int column, Movement dir, int dmg,double coef, int img) {
		super(map, cycle, row, column, dir, dmg, 1, coef, img, 1 );
		int i = Statics.convertToCellId(row+dir.getVerticalIncrement(), column+dir.getHorizontalIncrement());
		if (getMyMap().backWalkableAt(i))
			new NyanHorRainbow(map, cycle, row+dir.getVerticalIncrement(),
			column+dir.getHorizontalIncrement(), dir, dmg, coef, 1625);
		//super(map, DEFAULTCYCLE, row, column, Movement.RIGHT, DEFAULDAMAGE, 1, DEFAULTCOEF, 1624, 0);
	}
	@Override
	protected boolean handleMove(byte attackResult) {
		return false;
	}
	
	@Override
	protected void setImage(Movement mov) {
		return ;
	}
	
	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
	}

}


