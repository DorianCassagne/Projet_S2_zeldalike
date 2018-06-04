package model.character.item.hp;

import model.character.item.Item;
import model.character.item.factory.ItemEnum;

public enum HealEnum implements ItemEnum{

	SMALLPOTION(20),
	MIDDLEPOTION(50),
	BIGPOTION(80),
	HUGEPOTION(100);
	
	
	private int hpHeal;
	
	HealEnum(int hpHeal){
		this.hpHeal = hpHeal;
	}
	
	public int getHeal() {
		return this.hpHeal;
	}
	
	@Override
	public int getImage() {
		return this.ordinal() + Item.HPPOTIONSTARTINDEX;
	}
	
}
