package model.character.attack.statics.hero.arrow;

import model.character.attack.dynamic.Launcher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class SimpleArrowLauncher implements Launcher {

	private SimpleArrow lastAttack;
	
	
	@Override
	public void launch(GameMap map, Movement direction, int row, int column,int atkPT) {
		if(this.lastAttack == null || this.lastAttack.isAlive()) {
			this.lastAttack = new SimpleArrow(map, row, column, direction, atkPT);
		}
	}

	@Override
	public int getImage() {
		return lastAttack.getImageValueProperty().get();
	}

	@Override
	public int getDamage() {
		return this.lastAttack.getDamage();
	}

	
	
}
