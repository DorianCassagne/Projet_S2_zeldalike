package model.event.action.elements;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.condition.elements.ElementNames;

public enum ItemEnum implements ElementNames,TypeAction{
	AXE,
	LANCERAVANCE,
	LANCER,
	FAUX,
	EPEEAVANCE,
	EPEEAVANCE2,
	HACHEAVANCE,
	ARCAVANCE,
	EPEEMAXLEVEL,
	EPEELEVELMOYEN,
	EPEELORDE,
	EPEELONGUE,
	EPEEROUGE,
	LVL1_DEF,
	LVL2_DEF,
	LVL4_DEF,
	LVL3_DEF,
	LVL16_DEF,
	LVL15_DEF,
	LVL14_DEF,
	LVL13_DEF,
	LVL6_DEF,
	LVL5_DEF,
	LVL7_DEF,
	LVL8_DEF,
	LVL9_DEF,
	LVL10_DEF,
	LVL11_DEF,
	LVL12_DEF,
	LVL1_HEAL,
	LVL2_HEAL,
	LVL3_HEAL,
	LVL4_HEAL,
	LVL5_HEAL,
	LVL6_HEAL,
	LVL7_HEAL,
	LVL8_HEAL,
	MAP1,
	MAP2,
	MAP3,
	LVL1_MP,
	LVL2_MP,
	LVL3_MP,
	LVL4_MP,
	LVL5_MP,
	LVL6_MP,
	LVL7_MP,
	LVL8_MP,
	LVL1_MPPOTION,
	LVL2_MPPOTION,
	LVL5_MPPOTION,
	LVL3_MPPOTION,
	LVL6_MPPOTION,
	LVL7_MPPOTION,
	LVL8_MPPOTION,
	LVL4_MPPOTION,
	LVL1_HP,
	LVL7_HP,
	LVL2_HP,
	LVL3_HP,
	LVL4_HP,
	LVL5_HP,
	LVL6_HP,
	LVL8_HP,
	LVL1_SPEED,
	LVL8_SPEED,
	LVL5_SPEED,
	LVL2_SPEED,
	LVL3_SPEED,
	LVL4_SPEED,
	LVL6_SPEED,
	LVL7_SPEED;
	
	
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
