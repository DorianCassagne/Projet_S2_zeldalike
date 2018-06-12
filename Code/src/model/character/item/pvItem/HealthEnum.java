package model.character.item.pvItem;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum HealthEnum implements ItemEnum{
	LVL1_HP(50),
	LVL7_HP(700),
	LVL2_HP(100),
	LVL3_HP(230),
	LVL4_HP(350),
	LVL5_HP(470),
	LVL6_HP(580),
	LVL8_HP(100);
	
	private int additionalHP;
	
	HealthEnum(int additionalHP){
		this.additionalHP = additionalHP;
	}
	
	public int getMoreHp() {
		return this.additionalHP;
	}
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.HPITEMSTARTINDEX;
	}
	

	
	
}
