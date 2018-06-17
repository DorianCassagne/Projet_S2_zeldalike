package model.event.action.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.condition.elements.ElementNames;

public enum GeneralType implements ElementNames{
	SHOW("Afficher","S",SpecificType.MESSAGE),
	CREATE("Créer","C",SpecificType.ITEM,SpecificType.MONSTER,SpecificType.NPC),
	DROP("Retirer","D",SpecificType.WALKABLE,SpecificType.MONSTERDROP,SpecificType.ITEMDROP,SpecificType.HERO),
	ADD("Ajouter","A",SpecificType.ATTACK);
	
	private String nom;
	private String representation;
	private StringProperty representationProperty;
	private SpecificType[] allowedGlobalTypes;
	
	GeneralType(String nom,String representation,SpecificType ... allowedGlobalTypes){
		this.nom = nom;
		this.representation = representation;
		this.representationProperty = new SimpleStringProperty(nom);
		this.allowedGlobalTypes = allowedGlobalTypes;
	}
	
	
	public SpecificType[] getAllowedGlobalTypes() {
		return this.allowedGlobalTypes;
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
