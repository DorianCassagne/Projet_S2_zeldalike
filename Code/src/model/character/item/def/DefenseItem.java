package model.character.item.def;

import model.character.hero.Hero;
import model.character.item.Item;

public class DefenseItem extends Item{

	private DefenseItemEnum defenseItem;
	
	public DefenseItem(int imageValue) {
		super(imageValue);
		int diff = imageValue - Item.DEFENSEINTEMSTARTINDEX;
		if(diff >= 0 && diff < DefenseItemEnum.values().length ) {
			this.defenseItem = DefenseItemEnum.values()[diff];
		}
		else
			throw new IllegalArgumentException("ITEM NOT A POTION");
	}
	
	public DefenseItem(String name) {
		this(DefenseItemEnum.valueOf(name).getImage());
	}

	@Override
	protected void applyTo(Hero hero) {
		hero.setBasicDef(this.defenseItem);
	}

}
