package model.event.action.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.condition.elements.ElementNames;

public enum AttackEnum implements ElementNames,TypeAction {
	NOTYETIMPLEMENTED;
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
