package model.character.item.attack;

import model.character.hero.Hero;

import model.character.item.Item;

public class AttackItem extends Item{

	private AttackItemEnum attackItem;
	
	public AttackItem(int imageValue) {
		super(imageValue);
		int diff = imageValue - Item.ATTACKITEMSTARTINDEX;
		if(diff >= 0 && diff < AttackItemEnum.values().length ) {
			this.attackItem = AttackItemEnum.values()[diff];
		}
		else
			throw new IllegalArgumentException("ITEM NOT AN ATTACK EQUIPEMENT WITH ID " + diff);
	}
	
	public AttackItem(String name) {
		this(AttackItemEnum.valueOf(name).getImage());
	}

	public int getDamage() {
		return this.attackItem.getDmg();
	}
	
	public int getMaxDistance() {
		return this.attackItem.getMaxDistance();
	}
	
	public int getAttackImage() {
		return this.attackItem.getAttackImage();
	}
	
	@Override
	protected void applyTo(Hero hero) {
		hero.setBasicAtk(this);
	}

}
