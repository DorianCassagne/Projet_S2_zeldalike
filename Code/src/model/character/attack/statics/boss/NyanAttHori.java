package model.character.attack.statics.boss;
/*
 * Classe d'attaque du Boss de fin
 * 
 */

import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class NyanAttHori extends NyanHorRainbow{
	private final static double DEFAULTCOEF = 2.0;
	private final static int DEFAULTCYCLE = 1;
	private final static int DEFAULDAMAGE = 3;
	
	
	
	public NyanAttHori(GameMap map, int row, int column) {
		super(map, DEFAULTCYCLE, row, column+1, Movement.RIGHT,DEFAULDAMAGE, DEFAULTCOEF, 1624);
		new NyanHorRainbow(map, DEFAULTCYCLE, row, column, Movement.LEFT,DEFAULDAMAGE, DEFAULTCOEF, 1627);
	}

	public NyanAttHori(GameMap map, int row, int column, boolean left) {
			super(map, DEFAULTCYCLE, row, column, (left)?Movement.LEFT:Movement.RIGHT,DEFAULDAMAGE, DEFAULTCOEF, (left)?1026:1624);
	}
	

}


