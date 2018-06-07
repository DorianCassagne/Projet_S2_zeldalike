package model.event.condition;

import model.event.ElementNames;

public enum ConditionValue implements ElementNames {

	ITEM("item","I"),
	WALKABLE("Deplaçable","W");
	
	private String nom;
	private String representation;
	
	ConditionValue(String nom,String representation){
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
