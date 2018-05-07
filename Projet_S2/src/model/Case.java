package model;

public class Case {
	private Background background;
	private Personnage currentChar;
	private Item item;
	private Decoration decoration;
	
	public Case(int backgroundCode,int decorationCode,int itemCode) {
		this.background = new Background(backgroundCode);
		this.decoration = new Decoration(decorationCode);
	}
	
	public Case(String backgroundValue,int decorationCode,int itemCode) {
		this.background = new Background(backgroundValue);
		this.decoration = new Decoration(decorationCode);
	}
	
	public Case(String backgroundValue,String decorationValue,int itemCode) {
		this.background = new Background(backgroundValue);
		this.decoration = new Decoration(decorationValue);
	}
	
	public Case(int backgroundValue,String decorationValue,String itemValue) {
		this.background = new Background(backgroundValue);
		this.decoration = new Decoration(decorationValue);
	}
	
	
	public boolean estVide() {
		return this.currentChar == null;
	}
	
	public void ajouterPersonnage(Personnage character) {
		if(character == null || !background.estTraversable())
			throw new IllegalArgumentException("Votre personnage n'est pas valide (il est null)");
		else
			this.currentChar = character;
	}
	
	public void viderCase() {
		this.currentChar = null;
	}

}
