package model.event.action.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.condition.elements.ElementNames;

public enum ItemEnum implements ElementNames,TypeAction{
	AXE,
	LANCERAVANCE,
	LANCER,
	EPEE,
	EPEEAVANCE,
	EPEEAVANCE2,
	ARC,
	EPEEAVANCE3,
	EPEEBASIQUE,
	EPEEMOYENNE,
	ARCAVANCE,
	ARCBASIQUE,
	MINER,
	EQUIP1,
	EQUIP2,
	EQUIP5;
	private StringProperty representationProperty;
	
	ItemEnum(){
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
