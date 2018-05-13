package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Case {
	public final static char CHARCHANGE = 'c';
	public final static char ITEMCHANGE = 'i';
	public final static char DECORATIONCHANGE = 'd';
	
	private Background background;
	private Personnage currentChar;
	private Item item;
	private Decoration decoration;
	private BooleanProperty changeProperty;
	private final BooleanProperty safeProperty;
	private char lastChange;
	/*
	 * Une case ne peut contenir qu'un item ou une decoration, pas les deux en même temps
	 */
	
	public Case(int backgroundCode) {
		this.safeProperty = new SimpleBooleanProperty();
		this.background = new Background(backgroundCode);
		this.changeProperty = new SimpleBooleanProperty();
		
	}
	
	public Case(Integer backgroundCode,Integer decorationCode,Integer itemCode) {
		this.safeProperty = new SimpleBooleanProperty();
	}	
	
	
	public boolean estVide() {
		return this.currentChar == null && this.item == null && background.estTraversable();
	}
	
	public void ajouterPersonnage(Personnage character) throws IllegalArgumentException{
		if(!this.estVide() )
			throw new IllegalArgumentException("Cette colonne n'est pas traversable");
		else {
			this.currentChar = character;
			this.triggerChange(CHARCHANGE);
		}
	}
	
	private void triggerChange(char lastChange) {
		this.lastChange = lastChange;
		this.changeProperty.set(!this.changeProperty.get());
	}
	
	
	public BooleanProperty changeProperty() {
		this.safeProperty.unbind();
		this.safeProperty.bind(this.changeProperty);
		return this.changeProperty;
	}
	
	
	public void viderCase() {
		this.currentChar = null;
		this.triggerChange(CHARCHANGE);
	}
	
	public Background getBackground() {
		return this.background;
	}
	
	public char getChangeCategory() {
		return this.lastChange;
	}
	
	public boolean contientPersonnage(Personnage personnage) {
		return this.currentChar == personnage;
	}
}
