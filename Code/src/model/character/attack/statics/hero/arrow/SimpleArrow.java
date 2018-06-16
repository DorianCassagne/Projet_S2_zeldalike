
package model.character.attack.statics.hero.arrow;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class SimpleArrow extends Attack {
 

	private final static int DEFAULTCYCLE = 1;
	private final static int DEFAULTCELLPERTURN = 4;
	private final static int DEFAULTIMAGE = 1616;
	private final static double DEFAULTCOEFFICIENT = 2;
	private final static int DEFAULTDAMAGE = 100;
	private final static int DAMAGEPERLEVEL = 5;
	private final static int MAXDISTANCE = 2;
	
	public SimpleArrow(GameMap map, int row, int column, Movement direction, int level) {
		super(map, DEFAULTCYCLE, row, column, direction, DEFAULTDAMAGE + level * DAMAGEPERLEVEL , DEFAULTCELLPERTURN, DEFAULTCOEFFICIENT,DEFAULTIMAGE,MAXDISTANCE + DAMAGEPERLEVEL);
	}

	public void establishAttack(GameCharacter gameCharac) {
		gameCharac.getDmg(this);
	}

}