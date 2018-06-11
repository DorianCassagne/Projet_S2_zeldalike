package model.character.enemy.boss;

import model.character.enemy.BlueFairy;
import model.gameMap.GameMap;
import model.gameMap.move.Move;

public class Fairy extends BlueFairy {
	private final static int CYCLE=30;
	private final static int IMG=32;
	private final static int DEF=30;
	private final static int DMG=30;
	private final static int HP=30;

	public Fairy(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn, CYCLE, IMG, HP, DEF, DMG);
	}

	@Override
	protected Move act() {
		
		return null;
	}
	
	

}
