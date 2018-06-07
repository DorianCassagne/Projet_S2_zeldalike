package model.character.item.pvItem;

import model.character.hero.Hero;
import model.character.item.Item;
import model.character.item.def.DefenseItemEnum;
import model.character.item.hp.HealEnum;

public class HealthItem extends Item{
	
	private HealthEnum healthItem;
	
	public HealthItem(int imageValue) {
		super(imageValue);
		int diff = imageValue - Item.HPITEMSTARTINDEX;
		if(diff >= 0 && diff <  HealthEnum.values().length ) {
			this.healthItem = HealthEnum.values()[diff];
		}
		else
			throw new IllegalArgumentException("ITEM NOT A POTION");
	}
	
	public HealthItem(String name) {
		this(DefenseItemEnum.valueOf(name).getImage());
	}

	@Override
	protected void applyTo(Hero hero) {
		hero.setMaxHP(this.healthItem);
	}
	
	public static int isInRange(int imageValue) {
		int diff = imageValue - Item.HPITEMSTARTINDEX;
		if(diff < 0 || diff > HealthEnum.values().length) {
			diff = -1;
		}
		return diff;
	}
	
	public static int isInRange(String name) {
		return isInRange(HealthEnum.valueOf(name).getImage());
	}


}
