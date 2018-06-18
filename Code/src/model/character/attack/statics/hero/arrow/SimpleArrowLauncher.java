package model.character.attack.statics.hero.arrow;
/*
 * classe launcher de SimpleArrow, retire des points de mana a cahque utilisation
 */

import model.character.attack.Launcher;
import model.character.item.attack.AttackItemEnum;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class SimpleArrowLauncher implements Launcher {

	private final static int MANACONSUME = 10;
	private SimpleArrow lastAttack;
	
	
	@Override
	public int launch(GameMap map, Movement direction, int row, int column,int atkPT) {
		int manaPts = 0;
		if(this.lastAttack == null || this.lastAttack.isAlive()) {
			this.lastAttack = new SimpleArrow(map, row, column, direction, atkPT);
			manaPts = MANACONSUME;
		}
		return manaPts;
	}

	
	@Override
	public int getDamage() {
		return this.lastAttack.getDamage();
	}


	@Override
	public int getImage() {
		return 0;
	}

	
	
}
