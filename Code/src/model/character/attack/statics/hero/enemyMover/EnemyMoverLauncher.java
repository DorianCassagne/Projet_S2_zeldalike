package model.character.attack.statics.hero.enemyMover;

import model.character.attack.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;
/*
 * classe launcher de EnemyMover, retire des points de mana a chaque utilisation
 */

public class EnemyMoverLauncher implements Launcher{
	private final static int MPCONSUME = 50;

	@Override
	public int launch(GameMap map, Movement direction, int row, int column,int attackPT) {
			new EnemyMover(map, row, column,direction);
			return MPCONSUME;
	}


	@Override
	public int getDamage() {
		return EnemyMover.DEFAULTDAMAGE;
	}


	@Override
	public int getImage() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
