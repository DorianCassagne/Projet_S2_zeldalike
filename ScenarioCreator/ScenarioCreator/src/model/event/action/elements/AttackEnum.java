package model.event.action.elements;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.StringProperty;
import model.event.condition.elements.ElementNames;

public enum AttackEnum implements ElementNames,TypeAction {
	BADMONKEY,
	FAIRY,
	MONKEYGUARD,
	BOMBER,
	INTELLIGENTTOWER,
	TOWER,
	NYANYANAY,
	NYABLOCK;
	private StringProperty representationProperty;
	
	AttackEnum(){
		this.representationProperty = new SimpleStringProperty(this.name());
	}
	
	@Override
	public String getValue() {
		return this.name();
	}

	public StringProperty representationProperty() {
		return this.representationProperty;
	}

}
