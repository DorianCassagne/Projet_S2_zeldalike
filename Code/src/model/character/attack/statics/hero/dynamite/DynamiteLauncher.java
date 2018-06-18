package model.character.attack.statics.hero.dynamite;

import model.character.attack.Launcher;
import model.character.enemy.normal.Bomber;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class DynamiteLauncher implements Launcher{

	private static int MANACONSUME = 50;
	
	@Override
	public int launch(GameMap map, Movement direction, int row, int column, int attackPT) {
		new Bomber(map, row + direction.getVerticalIncrement(), column + direction.getHorizontalIncrement());
		return MANACONSUME;
	}

	@Override
	public int getImage() {
		return Bomber.DEFAULTIMAGE;
	}

	@Override
	public int getDamage() {
		return Dynamite.DEFAULTDAMAGE;
	}

	@Override
	public int getManaConsume() {
		return MANACONSUME;
	}


	
	
}
