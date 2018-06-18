package model.character.attack.statics;

import model.character.attack.Launcher;

import model.character.attack.statics.hero.arrow.SimpleArrowLauncher;
import model.character.attack.statics.hero.dynamite.DynamiteLauncher;
import model.character.attack.statics.hero.enemyMover.EnemyMoverLauncher;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class DynamicLauncher implements Launcher {
	
	
	private final static int ENEMYMOVER = 0;
	private final static int SIMPLEARROW = 1;
	private final static int BOMBLAUNCHER = 2;
	private Launcher launcher;
	
	public DynamicLauncher(int attackId) {
		
		switch(attackId) {
			case ENEMYMOVER :
				this.launcher = new EnemyMoverLauncher();	
				break;
			case BOMBLAUNCHER :
				this.launcher = new DynamiteLauncher();
				break;
			case SIMPLEARROW :
				this.launcher = new SimpleArrowLauncher();
				break;
			default : 
				throw new IllegalArgumentException("INVALID ID FOR DYNAMIC LAUNCHER");
		}
		
	}
	
	@Override
	public int launch(GameMap map, Movement direction, int row, int column,int attackPT) {
		return this.launcher.launch(map, direction, row, column,attackPT);
	}

	@Override
	public int getDamage() {
		return this.launcher.getDamage();
	}
	
	@Override
	public int getImage() {
		return this.launcher.getImage();
	}

	@Override
	public int getManaConsume() {
		return this.launcher.getManaConsume();
	}
}
