package model.character.item.speed;

import model.character.hero.Hero;
import model.character.item.Item;

public class SpeedItem extends Item{

	private SpeedItemEnum speedEnum;
	
	public SpeedItem(int imageValue) {
		super(imageValue);
		int diff = imageValue - Item.SPEEDITEMSTARTINDEX;
		if(diff >= 0 && diff < SpeedItemEnum.values().length ) {
			this.speedEnum = SpeedItemEnum.values()[diff];
		}
		else
			throw new IllegalArgumentException("ITEM NOT A SPEED ITEM");

	}
	
	public SpeedItem(String name) {
		this(SpeedItemEnum.valueOf(name).getImage());
	}


	@Override
	protected void applyTo(Hero hero) {
		//hero.setSpeedItem(speedEnum);
		//TODO
	}

}
