package model.character.attack.dynamic;

import model.character.attack.Attack;
import model.character.item.attack.AttackItemEnum;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class DefaultAttackLauncher implements Launcher{
	private final static int MANACONSUME = 0;
	
	private Attack lastAttack;
	private AttackItemEnum type;

	public DefaultAttackLauncher(AttackItemEnum attackType) {
		if(attackType != null) {
			this.lastAttack = null;
			this.type = attackType;
		}
		else {
			throw new IllegalArgumentException("ATTACKTYPE IS NULL");
		}
	}
	
	
	@Override
	public int launch(GameMap map,Movement direction,int row,int column,int attackPt) {
		if(this.lastAttack == null || !this.lastAttack.isAlive()) {
			this.lastAttack = new DefaultAttack(map,row,column,direction,this.type,attackPt);
		}
		return MANACONSUME;
	}
	
	@Override
	public int getImage() {
		return this.type.getAttackImage();
	}


	@Override
	public int getDamage() {
		return this.type.getDmg();
	}
	

}
