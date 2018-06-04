package model.character.attack.statics;

import model.character.attack.dynamic.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class BoomerangLauncher implements Launcher{
	
	
	
	

	@Override
	public void launch(GameMap map, Movement direction, int row, int column) {
		new Bomrang(map,row,column,direction);
	}

}
