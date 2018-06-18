package model.character.attack.statics.hero.dynamite;

/*
 * Classe d'attaque Dynamite (explosion)

 * attaque statique simple a qui l'image sera modifie pour permettre la visualisation d'une image 3x3 
 */

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Dynamite extends Attack {
	
	private final static int CELLPERTURN = 1;
	public final static int DEFAULTDAMAGE = 100;
	private final static int DEFAULTCOEFFICIENT = 1;
	private final static int DEFAULTMAXDISTANCE = 1;
	private final static int DEFAULTCYCLE = 20;
	public final static int DEFAULTIMAGE = 1688; 

	public Dynamite(GameMap map, int row, int column , int direction) {
		
		super(map,DEFAULTCYCLE, row, column, Movement.STAY, DEFAULTDAMAGE , CELLPERTURN, DEFAULTCOEFFICIENT,DEFAULTIMAGE , DEFAULTMAXDISTANCE);
		this.setImage(direction);
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
		this.getMyMap().clearBackgroundConstraint(this.getCellId(), this, 2516);
	}
	
	@Override 
	protected boolean handlePlay(GameCharacter gameCharacter) {
		return false;
	}
	
	


}
