package model.character.item.mpPotion;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum MPPotionItemEnum implements ItemEnum{
	LVL1_MPPOTION(20),
	LVL2_MPPOTION(50),
	LVL5_MPPOTION(350),
	LVL3_MPPOTION(90),
	LVL6_MPPOTION(150),
	LVL7_MPPOTION(240),
	LVL8_MPPOTION(500),
	LVL4_MPPOTION(120);

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
