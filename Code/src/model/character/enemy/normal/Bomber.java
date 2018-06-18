package model.character.enemy.normal;

/*
 * Classe d'ennemi Bomber 
 * Bomber est une bombe qui explose au bout d'un temps (MAXCOUNTER) dans un rayon de 3x3 centr� sur bomber 
 * l'explosion est compos� de 9 attacks de type Dynamite avec la direction associ� pour afficher une image de 3x3
 * ne bouge pas et est intuable
 * 
 * 
 * animation : bomber a 3 images, il clignote entre 2 images lorsque counter = MAXCOUNTER la 3eme image s'affiche pour signaler
 * 				au joueur que la bombe va exploser au tour suivant
 * 
 */

import model.character.attack.statics.hero.dynamite.Dynamite;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Bomber extends EnemyNormal{
	
	private final static int DEFAULTCYCLE = 80;
	private final static int COEFFICIENT = 2;
	public final static int DEFAULTIMAGE = 1680;
	private final static int DEFAULTHP = 800000;
	private final static int DEFAULTDEF = Integer.MAX_VALUE;
	private final static int SCORE = 0;
	private final static int MAXCOUNTER = 7;
	
	private int counter;
	public Bomber(GameMap map, int startRow, int startColumn) {
		
		super(map, startRow, startColumn, DEFAULTCYCLE, COEFFICIENT, DEFAULTIMAGE, DEFAULTHP, DEFAULTDEF, SCORE);
		this.counter = 0;
		
	}

	@Override
	public String getName() {
		return "BOMBER";
	}

	@Override
	public void launchAttack(Movement move) {
		int nextRow ;
		int nextColumn;
		
		for(Movement movement : Movement.values()) {

			nextRow = this.getRow() + movement.getVerticalIncrement();
			nextColumn = this.getColumn() + movement.getHorizontalIncrement();
			
			new Dynamite(getMyMap(), nextRow, nextColumn, movement.ordinal());
		}
	}

	@Override
	protected Move act() {
		if(this.counter == MAXCOUNTER) {
			this.setImage(Movement.BOTTOM);
			this.launchAttack(null);
			this.removeCharacter();
		}
		
		else if(this.counter % 2 == 0) {
			this.setImage(Movement.RIGHT);
		}
		else {
			this.setImage(Movement.TOP);
		}
		
		this.counter++;
		return null;
	}

}
