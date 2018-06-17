package model.character.item.def;
/*
 * enumeration de tous les items qui seront des armures
 */
import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum DefenseItemEnum implements ItemEnum{
	LVL1_DEF(20),
	LVL2_DEF(30),
	LVL4_DEF(80),
	LVL3_DEF(70),
	LVL16_DEF(1500),
	LVL15_DEF(1000),
	LVL14_DEF(850),
	LVL13_DEF(700),
	LVL6_DEF(80),
	LVL5_DEF(120),
	LVL7_DEF(140),
	LVL8_DEF(180),
	LVL9_DEF(230),
	LVL10_DEF(300),
	LVL11_DEF(400),
	LVL12_DEF(600);
	
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
