package model.event.action.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.condition.elements.ElementNames;

public enum SpecificType implements ElementNames{
	ITEM("Item","I",ItemEnum.values()),
	MONSTER("Monstre","M",AttackEnum.values()),
	NPC("NPC","N",NPCEnum.values()),
	MESSAGE("Message","m",null),
	ATTACK("Attack","A",null),
	WALKABLE("Déplaçable","W",null),
	HERO("Héro","H",null),
	ITEMDROP("Item","I",null),
	MONSTERDROP("Monstre","M",null);
	;

	private String nom;
	private String representation;
	private StringProperty representationProperty;
	private TypeAction[] allowedActions;
	
	SpecificType(String nom,String representation,TypeAction[] allowedActions) {
		this.nom = nom;
		this.representation = representation;
		this.representationProperty = new SimpleStringProperty(nom);
		this.allowedActions = allowedActions;
	}
	
	public TypeAction[] getAllowedActions() {
		return this.allowedActions;
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
