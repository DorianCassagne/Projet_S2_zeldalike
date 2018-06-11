package model.event.condition.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum LogicalLink implements ElementNames{
	OR("OU","OR"),
	AND("ET","AND"),
	END("FIN","");
	
	private String nom;
	private String representation;
	private StringProperty representationProperty;

	LogicalLink(String nom,String representation){
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
