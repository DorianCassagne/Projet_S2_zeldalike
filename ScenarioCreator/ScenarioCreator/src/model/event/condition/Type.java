package model.event.condition;


import model.event.ElementNames;

public enum Type implements ElementNames{
	CELL("Case","C"),
	MONSTER("Monstre","M"),
	HERO("Hero","H"),
	OLD("Ancienne condition","O");

	private String nom;
	private String representation;
	Type(String nom,String representation){
		this.nom = nom;
		this.representation = representation;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}

	@Override
	public String getValue() {
		return this.representation; 
	}
	
	
}
