package model.character.item;

import model.character.GameCharacter;
import model.character.hero.Hero;

/*
 * Item : abstract

	public Item (int HP, int MP, int ATK) : crée un objet de type Item. Renvoie une erreure si l’une des valeurs est négatives
	
	public void applyTo(Hero hero) : modifie les caractéristique du personnage à partir des propriétés de l’item,
	
	public abstract int getImageName(): retourne le nom de l’imag
 */

public abstract class Item {
	public static final int	ATTACKITEMSTARTINDEX = 808;
	public final static int DEFENSEINTEMSTARTINDEX = 824;
	public final static int SPEEDITEMSTARTINDEX = 840;
	public final static int MPITEMSTARTINDEX = 856;
	public final static int HPITEMSTARTINDEX = 864;
	public final static int BOXITEMSTARTINDEX = 872 ;
	public final static int HPPOTIONSTARTINDEX = 888;
	public final static int MPPOTIONSTARTINDEX = 892;


	private int imageValue;
	
	//crée un objet de type Item. Renvoie une erreure si l'une des valeurs est négatives
	public Item(int imageValue) {
		this.imageValue = imageValue;
	}

	//public abstract int getImageName(): retourne le nom de l�imag
	public int getImageValue() {
		return this.imageValue;
	}
	
	public boolean effectOn(GameCharacter c) {
		boolean isConsumed = c == GameCharacter.getHero();
		if(isConsumed) {
			this.applyTo(GameCharacter.getHero());
		}
		return isConsumed;
	}
	
	
	//modifie les caract�ristique du personnage � partir des propri�t�s de l�item,
	protected abstract void applyTo(Hero hero); 
}
