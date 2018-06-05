package model.character.item.attack;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum AttackItemEnum implements ItemEnum{
	AXE(50,1,1608),
	LANCERAVANCE(80,3,1616),
	LANCER(30,2,1612),
	EPEE(30,1,1620),
	EPEEAVANCE(60,1,1616),
	EPEEAVANCE2(80,1,1612),
	ARC(30,3,1612),
	EPEEAVANCE3(100,1,1608),
	EPEEBASIQUE(20,1,1620),
	EPEEMOYENNE(40,1,1616),
	ARCAVANCE(50,3,1612),
	ARCBASIQUE(20,2,1616),
	MINER(40,1,1620),
	EQUIP1(40,1,1616),
	EQUIP2(30,1,1620),
	EQUIP5(30,1,1616),
;

	private int maxDistance;
	private int atkDmg;
	private int attackImage;
	
	AttackItemEnum(int atkDmg, int maxDistance,int attackImage){
		this.maxDistance = maxDistance;
		this.atkDmg = atkDmg;
		this.attackImage = attackImage;
	}
	
	public int getDmg() {
		return this.atkDmg;
	}
	
	public int getMaxDistance() {
		return this.maxDistance;
	}
	
	public int getAttackImage() {
		return this.attackImage;
	}

	@Override
	public int getImage() {
		return this.ordinal() + Item.ATTACKITEMSTARTINDEX;
	}
	
}
