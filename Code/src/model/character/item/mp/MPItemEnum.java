package model.character.item.mp;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum MPItemEnum implements ItemEnum{
	LVL1_MP(10),
	LVL2_MP(20),
	LVL3_MP(30),
	LVL4_MP(50),
	LVL5_MP(80),
	LVL6_MP(120),
	LVL7_MP(150),
	LVL8_MP(210);
	
	private int additionalMP;
	MPItemEnum(int mp){
		this.additionalMP = mp;
	}
	
	public int getAdditionalMP() {
		return this.additionalMP;
	}
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.MPITEMSTARTINDEX;
	}
	
	
}
