package model.event.condition.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum ConditionValidity implements ElementNames {
	TRUE("vrai"," "),
	FALSE("faux","!");
	
	private String nom;
	private String representation;
	private StringProperty representationProperty;
	ConditionValidity(String nom,String representation){
		this.nom = nom;
		this.representation = representation;
		this.representationProperty = new SimpleStringProperty(nom);
	}
	@Override
	public String getValue() {
		return this.representation;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
	@Override
	public StringProperty representationProperty() {
		return this.representationProperty;
	}
	
}
