package model.event.action;

import model.event.ElementNames;

public enum SpecificType implements ElementNames{
	ITEM("Item","I"),
	MONSTER("Monstre","M"),
	MESSAGE("Message","m"),
	ATTACK("Attack","A"),
	WALKABLE("Déplaçable","W");

	private String nom;
	private String representation;
	
	SpecificType(String nom,String representation) {
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
