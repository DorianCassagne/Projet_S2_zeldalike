package model.character.attack.statics.enemyMover;

import model.character.attack.dynamic.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class EnemyMoverLauncher implements Launcher{
	

	@Override
	public void launch(GameMap map, Movement direction, int row, int column) {
			new EnemyMove(map, row, column,direction);
	}

}
