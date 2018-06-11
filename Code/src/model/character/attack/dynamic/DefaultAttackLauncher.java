package model.character.attack.dynamic;

import model.character.attack.Attack;
import model.character.item.attack.AttackItem;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class DefaultAttackLauncher implements Launcher{
	private Attack lastAttack;
	private AttackItem type;
	public DefaultAttackLauncher(AttackItem attackType) {
		if(attackType != null) {
			this.lastAttack = null;
			this.type = attackType;
		}
		else {
			throw new IllegalArgumentException("ATTACKTYPE IS NULL");
		}
	}
	
	
	@Override
	public void launch(GameMap map,Movement direction,int row,int column) {
		if(this.lastAttack == null || !this.lastAttack.isAlive()) {
			this.lastAttack = new DefaultAttack(map,row,column,direction,this.type);
		}
	}
	
	@Override
	public int getImage() {
		return this.type.getAttackImage();
	}
	

}
