package model.character.item.hpPotion;

import model.character.hero.Hero;
import model.character.item.Item;

public class Healer extends Item{

	private HealEnum healType;
	
	public Healer(int imageId){
		super(imageId);
		int diff = imageId - Item.HPPOTIONSTARTINDEX;
		if(diff >= 0 && diff < HealEnum.values().length ) {
			this.healType = HealEnum.values()[diff];
		}
		else
			throw new IllegalArgumentException("ITEM NOT A POTION");
	}
	
	public Healer(String name) {
		this(HealEnum.valueOf(name).getImage());
	}

	@Override
	protected void applyTo(Hero hero) {
		hero.heal(this.healType);
	}

}
