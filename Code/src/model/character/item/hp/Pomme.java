package model.character.item.hp;

import model.character.Hero;
import model.character.item.Item;

public class Pomme extends Item{

	private final static int DEFAULTIMAGE = 201;
	private final static int DEFAULTHEAL = 20; 
	
	public Pomme(){
		super(DEFAULTIMAGE);
		
	}

	@Override
	public void applyTo(Hero hero) {
		hero.heal(DEFAULTHEAL);
	}

}
