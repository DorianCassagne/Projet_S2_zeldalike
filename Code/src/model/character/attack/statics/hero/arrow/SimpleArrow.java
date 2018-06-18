
package model.character.attack.statics.hero.arrow;
/*
 * Classe d'attaque simple
 * fleche invoque devant le joueur et se deplacement de facon rectiligne jusqu'a atteindre un obstacle ou le maximum de sa portee
 */
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class SimpleArrow extends Attack {
 

	private final static int DEFAULTCYCLE = 20;
	private final static int DEFAULTCELLPERTURN = 4;
	public final static int DEFAULTIMAGE = 1632;
	private final static double DEFAULTCOEFFICIENT = 2;
	private final static int DEFAULTDAMAGE = 1000;
	private final static int MAXDISTANCE = 10;
	
	public SimpleArrow(GameMap map, int row, int column, Movement direction, int level) {
		super(map, DEFAULTCYCLE, row, column, direction, DEFAULTDAMAGE  , DEFAULTCELLPERTURN, DEFAULTCOEFFICIENT,DEFAULTIMAGE,MAXDISTANCE );
	}

	public void establishAttack(GameCharacter gameCharac) {
		gameCharac.getDmg(this);
	}

}