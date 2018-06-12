package model.character.attack.statics.hero.bomb;

import model.character.attack.dynamic.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class BombLauncher implements Launcher{

	
	@Override
	public void launch(GameMap map, Movement direction, int row, int column,int atkPT) {
		new Bomb(map,row,column,direction,atkPT);
	}

	@Override
	public int getImage() {
		return Bomb.DEFAULTIMAGE;
	}

	@Override
	public int getDamage() {
		return Bomb.DEFAULTDAMAGE;
	}

}
