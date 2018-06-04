package model.character.item.def;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum DefenseItemEnum implements ItemEnum{
	ITEM1(40);
	
	
	private int additionalDef;
	
	DefenseItemEnum(int additionalDef){
		this.additionalDef = additionalDef;
	}
	
	public int getMoreDef() {
		return this.additionalDef;
	}
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.DEFENSEINTEMSTARTINDEX;
	}
	
	
	
}
