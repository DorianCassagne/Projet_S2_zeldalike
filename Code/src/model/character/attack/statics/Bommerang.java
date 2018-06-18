package model.character.attack.statics;
/*
 * Classe d'attaque boomerang
 * attaque rectiligne qui lorsque rencontre un obstacle ou atteint sa portee maximale retourne a sa case de depart
 */

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Bommerang extends Attack {

	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTDAMAGE = 20;
	private final static int CELLPERTURN = 1;
	private final static int DEFAULTCOEFFICIENT = 2;
	private final static int DEFAULTIMAGE = 1620;
	private final static int MAXDISTANCE = 10;
	
	
	private int count;
	private boolean start;
	private Movement backward;
	
	

	
	public Bommerang(GameMap map, int row, int column, Movement direction) {
		super(map, DEFAULTCYCLE, row, column, direction, DEFAULTDAMAGE, CELLPERTURN, DEFAULTCOEFFICIENT, DEFAULTIMAGE, MAXDISTANCE);
		count = 5;
		
		this.backward = Movement.getOppositeOf(direction);
	}

	@Override
	public boolean handleMove(byte attackResult) {
		if(count !=0 || !start) {
			count--;
			start=true;
		}
		
		else {
			count = 5;
		}
		
		if (count == 0) {
			this.setDirection(backward);
		}

		return super.handleMove(attackResult);
	}
	
	public void establishAttack(GameCharacter gameCharacter) {

		gameCharacter.getDmg(this);
	}
	
	

	

	
}
