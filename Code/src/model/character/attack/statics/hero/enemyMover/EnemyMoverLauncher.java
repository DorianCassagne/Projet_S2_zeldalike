package model.character.attack.statics.hero.enemyMover;

import model.character.attack.dynamic.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class EnemyMoverLauncher implements Launcher{
	

	@Override
	public void launch(GameMap map, Movement direction, int row, int column,int attackPT) {
			new EnemyMover(map, row, column,direction);
	}

	@Override
	public int getImage() {
		return EnemyMover.DEFAULTIMAGE;
	}

	@Override
	public int getDamage() {
		return EnemyMover.DEFAULTDAMAGE;
	}

	

}
