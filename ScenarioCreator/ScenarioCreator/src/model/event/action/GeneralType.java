package model.event.action;

import model.event.ElementNames;

public enum GeneralType implements ElementNames{
	SHOW("Afficher","S"),
	CREATE("Créer","C"),
	DROP("Retirer","D"),
	ADD("Ajouter","A");
	
	private String nom;
	private String representation;
	
	GeneralType(String nom,String representation){
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
