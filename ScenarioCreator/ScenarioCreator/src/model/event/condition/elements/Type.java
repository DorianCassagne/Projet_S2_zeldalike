package model.event.condition.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public enum Type implements ElementNames{
	CELL("Case","C",ConditionValue.ITEM,ConditionValue.WALKABLE),
	MONSTER("Monstre","M"),
	HERO("Hero","H"),
	OLD("Ancienne condition","O",ConditionValue.ABSOLUTE),
	NONE("Aucune","N");

	private String nom;
	private String representation;
	private StringProperty representationProperty;
	private ConditionValue[] validValues;
	
	Type(String nom,String representation,ConditionValue... values){
		this.nom = nom;
		this.representation = representation;
		this.representationProperty = new SimpleStringProperty(nom);
		this.validValues = values;
	}
	
	public ConditionValue[] getValidValues() {
		return this.validValues;
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
