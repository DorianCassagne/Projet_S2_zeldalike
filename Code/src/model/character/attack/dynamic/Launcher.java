package model.character.attack.dynamic;

import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public interface Launcher {
	public int launch(GameMap map,Movement direction,int row,int column,int attackPT);
	public int getImage();
	public int getDamage();
}
