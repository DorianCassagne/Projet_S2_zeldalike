package model.character.item.pvItem;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum HealthEnum implements ItemEnum{
	BASIQUEHEALTHARMOR(50),
	MEDIUMHEALERARMOR(200),
	ADVANCEDHEALTHARMOR(600);
	
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
