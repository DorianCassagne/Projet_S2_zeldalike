package model.character.item.mpPotion;

import model.character.hero.Hero;
import model.character.item.Item;

public class MPItem extends Item{
	private MPPotionItemEnum mpPotion;
	
	public MPItem(int imageValue) {
		super(imageValue);
		int diff = imageValue - Item.MPPOTIONSTARTINDEX;
		if(diff >= 0 && diff < MPPotionItemEnum.values().length ) {
			this.mpPotion = MPPotionItemEnum.values()[diff];
		}
		else
			throw new IllegalArgumentException("ITEM NOT A POTION " + diff);
	}
	
	public MPItem(String name) {
		this(MPPotionItemEnum.valueOf(name).getImage());
	}

	@Override
	protected void applyTo(Hero hero) {
		hero.healMP(this.mpPotion);
	}

}
