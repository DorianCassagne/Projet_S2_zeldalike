package model;

public class Case {
	private Background background;
	private Personnage currentChar;
	private Item item;
	private Decoration decoration;
	
	/*
	 * Une case ne peut contenir qu'un item ou une decoration, pas les deux en même temps
	 */
	
	public Case(int backgroundCode) {
		this.background = new Background(backgroundCode);
	}
	
	public Case(Integer backgroundCode,Integer decorationCode,Integer itemCode) {
		
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
	
	public Background getBackground() {
		return this.background;
	}
	
}
