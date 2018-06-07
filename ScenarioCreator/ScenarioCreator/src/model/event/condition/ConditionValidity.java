package model.event.condition;

import model.event.ElementNames;

public enum ConditionValidity implements ElementNames {
	TRUE("vrai"," "),
	FALSE("faux","!");
	
	private String nom;
	private String representation;
	ConditionValidity(String nom,String representation){
		this.nom = nom;
		this.representation = representation;
	}
	@Override
	public String getValue() {
		return this.representation;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
	
}
