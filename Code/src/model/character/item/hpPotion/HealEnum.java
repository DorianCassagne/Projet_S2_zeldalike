package model.character.item.hpPotion;

/*
 * enumeration de tous les items qui pourront soigner le heros
 */
import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum HealEnum implements ItemEnum{

	LVL1_HEAL(20),
	LVL2_HEAL(50),
	LVL3_HEAL(70),
	LVL4_HEAL(90),
	LVL5_HEAL(150),
	LVL6_HEAL(240),
	LVL7_HEAL(350),
	LVL8_HEAL(500);
	
	
	private int hpHeal;
	
	HealEnum(int hpHeal){
		this.hpHeal = hpHeal;
	}
	
	public int getHeal() {
		return this.hpHeal;
	}
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.HPPOTIONSTARTINDEX;
	}
	
}
