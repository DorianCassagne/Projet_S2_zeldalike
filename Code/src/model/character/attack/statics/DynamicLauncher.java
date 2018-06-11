package model.character.attack.statics;

import model.character.attack.dynamic.Launcher;
import model.character.attack.statics.hero.enemyMover.EnemyMoverLauncher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class DynamicLauncher implements Launcher {
	
	
	private final static int ENEMYMOVER = 0;
	private Launcher launcher;
	
	public DynamicLauncher(int attackId) {
		
		switch(attackId) {
			case ENEMYMOVER :
				this.launcher = new EnemyMoverLauncher();		
		}
		
	}
	@Override
	public void launch(GameMap map, Movement direction, int row, int column) {
		this.launcher.launch(map, direction, row, column);
	}
	@Override
	public int getImage() {
		return this.launcher.getImage();
	}
}
