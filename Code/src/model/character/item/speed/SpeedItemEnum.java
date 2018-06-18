package model.character.item.speed;
/*
 * enumration des differents objets qui modifieront la vitesse
 */
import java.util.TimerTask;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum SpeedItemEnum implements ItemEnum{
	LVL1_SPEED(5,30),
	LVL8_SPEED(60,80),
	LVL5_SPEED(30,55),
	LVL2_SPEED(10,35),
	LVL3_SPEED(15,40),
	LVL4_SPEED(25,45),
	LVL6_SPEED(40,65),
	LVL7_SPEED(50,70);

	private int speed;//Pourcentage d'augmentation
	private int timer;
	
	SpeedItemEnum(int speed,int timer){
		this.speed = speed;
		this.timer = timer;
	}
	
	public int getItemSpeed() {
		this.timer--;
		return this.speed;
		
	}
	
	public int getTimer() {
		return this.timer;
	}
		
	
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.SPEEDITEMSTARTINDEX;
	}

	
}

