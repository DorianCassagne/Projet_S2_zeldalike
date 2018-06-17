package model.character.item.attack;
/*
 * enumeration de tous les items qui seront des armes
 */
import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum AttackItemEnum implements ItemEnum{
	AXE(50,1,1616),
	LANCERAVANCE(40,4,1620),
	LANCER(30,4,1640),
	FAUX(60,1,1620),
	EPEEAVANCE(70,2,1672),
	EPEEAVANCE2(80,2,1676),
	HACHEAVANCE(120,1,1680),
	ARCAVANCE(50,3,1624),
	EPEEMAXLEVEL(200,2,1660),
	EPEELEVELMOYEN(100,2,1672),
	EPEELORDE(60,1,1656),
	EPEELONGUE(80,2,1684),
	EPEEROUGE(150,1,1680);

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
