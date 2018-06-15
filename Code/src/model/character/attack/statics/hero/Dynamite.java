package model.character.attack.statics.hero;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Dynamite extends Attack {
	
	private final static int CELLPERTURN = 1;
	private final static int DEFAULTDAMAGE = 100;
	private final static int DEFAULTCOEFFICIENT = 1;
	private final static int DEFAULTMAXDISTANCE = 1;
	private final static int DEFAULTCYCLE = 1;
	private final static int DEFAULTIMAGE = 1688; 

	public Dynamite(GameMap map, int row, int column , int direction) {
		
		super(map,DEFAULTCYCLE, row, column, Movement.STAY, DEFAULTDAMAGE , CELLPERTURN, DEFAULTCOEFFICIENT,DEFAULTIMAGE + direction, DEFAULTMAXDISTANCE);

	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
	}
	


}
