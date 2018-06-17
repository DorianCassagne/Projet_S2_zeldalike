package model.character.item.mpPotion;
/*
 * enumeration des differentes potions de regeneration de mp
 */
import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum MPPotionItemEnum implements ItemEnum{
	LVL1_MP(20),
	LVL2_MP(50),
	LVL5_MP(350),
	LVL3_MP(90),
	LVL6_MP(150),
	LVL7_MP(240),
	LVL8_MP(500),
	LVL4_MP(120);

	private int mpHeal;
	
	MPPotionItemEnum(int MP){
		this.mpHeal = MP;
	}
	
	public int getMPHeal() {
		return this.mpHeal;
	}
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.MPPOTIONSTARTINDEX;
	}
}
