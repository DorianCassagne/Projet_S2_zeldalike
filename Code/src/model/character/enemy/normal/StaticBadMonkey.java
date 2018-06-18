package model.character.enemy.normal;

import model.gameMap.GameMap;
import model.gameMap.move.Move;

public class StaticBadMonkey extends BadMonkey{

	public StaticBadMonkey(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn);
	}
	
	@Override
	public Move act() {
		return null;
	}

}
