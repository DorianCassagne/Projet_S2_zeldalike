package model.character.attack.statics.hero.arrow;

import model.character.attack.dynamic.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class SimpleArrowLauncher implements Launcher {

	private SimpleArrow lastAttack;
	
	
	@Override
	public void launch(GameMap map, Movement direction, int row, int column) {
		if(this.lastAttack == null || this.lastAttack.isAlive()) {
			this.lastAttack = new SimpleArrow(map, row, column, direction, 1);
		}
	}

	@Override
	public int getImage() {
		return lastAttack.getImageValueProperty().get();
	}

	
	
}
