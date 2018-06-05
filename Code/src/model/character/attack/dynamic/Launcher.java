package model.character.attack.dynamic;

import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public interface Launcher {
	public void launch(GameMap map,Movement direction,int row,int column);
}
