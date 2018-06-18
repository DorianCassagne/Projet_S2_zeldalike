package model.character.item.attack;
/*
 * enumeration de tous les items qui seront des armes
 */
import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum AttackItemEnum implements ItemEnum{
	AXE(50,1,1608),
	LANCERAVANCE(40,4,1616),
	LANCER(30,4,1616),
	FAUX(60,1,1636),
	EPEEAVANCE(70,2,1652),
	EPEEAVANCE2(80,2,1656),
	HACHEAVANCE(120,1,1660),
	ARCAVANCE(50,3,1632),
	EPEEMAXLEVEL(200,2,1644),
	EPEELEVELMOYEN(100,2,1648),
	EPEELORDE(60,1,1656),
	EPEELONGUE(80,2,1612),
	EPEEROUGE(150,2,1628),
	EPEEROUGE2(150,2,1628),
	EPEEROUGE3(150,2,1628),
	EPEEROUGE4(150,2,1628);

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
