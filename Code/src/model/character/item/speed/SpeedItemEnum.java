package model.character.item.speed;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum SpeedItemEnum implements ItemEnum{
	LVL1_SPEED(5),
	LVL8_SPEED(60),
	LVL5_SPEED(30),
	LVL2_SPEED(10),
	LVL3_SPEED(15),
	LVL4_SPEED(25),
	LVL6_SPEED(40),
	LVL7_SPEED(50);

	private int speed;//Pourcentage d'augmentation
	
	SpeedItemEnum(int speed){
		this.speed = speed;
	}
	
	public int getItemSpeed() {
		return this.speed;
	}
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.SPEEDITEMSTARTINDEX;
	}

}
