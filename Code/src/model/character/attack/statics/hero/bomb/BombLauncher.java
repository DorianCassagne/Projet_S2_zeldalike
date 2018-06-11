package model.character.attack.statics.hero.bomb;

import model.character.attack.dynamic.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class BombLauncher implements Launcher{

	
	@Override
	public void launch(GameMap map, Movement direction, int row, int column) {
		new Bomb(map,row,column,direction);
	}

	@Override
	public int getImage() {
		return Bomb.DEFAULTIMAGE;
	}

}
