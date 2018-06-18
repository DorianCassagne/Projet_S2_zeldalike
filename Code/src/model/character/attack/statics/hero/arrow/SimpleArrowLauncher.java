package model.character.attack.statics.hero.arrow;

import model.character.attack.Attack;

import model.character.attack.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class SimpleArrowLauncher implements Launcher {

	private final static int MANACONSUME = 40;
	
	
	@Override
	public int launch(GameMap map, Movement direction, int row, int column,int atkPT) {
		 new SimpleArrow(map,row,column,direction,atkPT);
		return MANACONSUME;
	}

	
	@Override
	public int getDamage() {

		return SimpleArrow.DEFAULTIMAGE;
	}


	@Override
	public int getImage() {
		return SimpleArrow.DEFAULTIMAGE;
	}


	@Override
	public int getManaConsume() {
		return MANACONSUME;
	}

	
	
}
