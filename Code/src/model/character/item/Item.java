package model.character.item;

import model.character.GameCharacter;
import model.character.hero.Hero;

/*
 * 	Item�: classe abstract
	Contient les index des differents types d'item 
	Applique au hero ses effets propre, provoque une modification des caract�ristiques 
	
	
 */

public abstract class Item {
	public static final int	ATTACKITEMSTARTINDEX = 808;
	public final static int DEFENSEINTEMSTARTINDEX = 824;
	public final static int SPEEDITEMSTARTINDEX = 840;
	public final static int MPITEMSTARTINDEX = 848;
	public final static int HPITEMSTARTINDEX = 856;
	public final static int BOXITEMSTARTINDEX = 864 ;
	public final static int HPPOTIONSTARTINDEX = 872;
	public final static int MPPOTIONSTARTINDEX = 880;
	public final static int MPPOTIONENDINDEX = 888;


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
