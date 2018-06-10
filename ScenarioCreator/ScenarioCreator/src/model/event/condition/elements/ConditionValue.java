package model.event.condition.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum ConditionValue implements ElementNames {

	ITEM("item","I"),
	WALKABLE("Deplaçable","W");
	
	private String nom;
	private String representation;
	private StringProperty representationProperty;

	ConditionValue(String nom,String representation){
		this.nom = nom;
		this.representation = representation;
		this.representationProperty = new SimpleStringProperty(nom);

	}
	
	
	@Override
	public String toString() {
		return this.nom;
	}

	@Override
	public String getValue() {
		return this.representation;
	}
	
	public StringProperty representationProperty() {
		return this.representationProperty;
	}

}
