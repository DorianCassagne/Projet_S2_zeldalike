package model.character.item.factory;

import model.character.attack.statics.BoomerangLauncher;
import model.character.hero.Hero;
import model.character.item.Item;

public class SpecialAttackItem extends Item{

	public SpecialAttackItem() {
		super(11);
	}

	@Override
	protected void applyTo(Hero hero) {
		hero.addLauncher(new BoomerangLauncher());
	}

}
