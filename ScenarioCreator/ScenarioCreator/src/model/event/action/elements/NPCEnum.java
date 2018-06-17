package model.event.action.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.condition.elements.ElementNames;

public enum NPCEnum implements TypeAction,ElementNames{
	MARIO,
	FAIRY1TOP,
	FAIRY1RIGHT,
	FAIRY1BACK,
	FAIRY1LEFT,
	FAIRY2TOP,
	FAIRY2RIGHT,
	FAIRY2BACK,
	FAIRY2LEFT;
	private StringProperty representationProperty;
	
	NPCEnum(){
		this.representationProperty = new SimpleStringProperty(this.name());
	}
	

	public StringProperty representationProperty() {
		return this.representationProperty;
	}


	@Override
	public String getValue() {
		return this.name();
	}

}
