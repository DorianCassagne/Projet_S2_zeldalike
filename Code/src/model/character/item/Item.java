package model.character.item;

import model.character.Hero;

/*
 * Item : abstract

	public Item (int HP, int MP, int ATK) : crée un objet de type Item. Renvoie une erreure si l’une des valeurs est négatives
	
	public void applyTo(Hero hero) : modifie les caractéristique du personnage à partir des propriétés de l’item,
	
	public abstract int getImageName(): retourne le nom de l’imag
 */

public abstract class Item {
	
	private int imgVal;
	private int hp;
	private int mp;
	private int atk;
		
	//crée un objet de type Item. Renvoie une erreure si l'une des valeurs est négatives
	public Item(int hp, int mp, int atk) throws IllegalArgumentException{ 
		if(hp < 0 || mp < 0 || atk < 0) {
			throw new IllegalArgumentException("FALSE ARGUMENT ON ITEM");
		}
		else {
			this.hp = hp;
			this.mp = mp;
			this.atk = atk;
		}
	}

	//public abstract int getImageName(): retourne le nom de l�imag
	public abstract int getImageName() ;
	
	//modifie les caract�ristique du personnage � partir des propri�t�s de l�item,
	public abstract void applyTo(Hero hero); 
	
}
