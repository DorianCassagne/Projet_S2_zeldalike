package model.event.condition.generator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.condition.elements.*;

public class Condition {
	
	private Type type;
	private StringProperty  id;
	private ConditionValidity validity;
	private ConditionValue value;
	private LogicalLink link;
	private final static String SEPARATOR = ":";
	
	
	
	
	public Condition(Type type,String id,ConditionValidity validity,ConditionValue value,LogicalLink link) {
		if(link != null) {
			this.setType(type);
			this.setId(id);
			this.setValidity(validity);
			this.setValue(value);
			this.link = link;
		}
		else {
			throw new IllegalArgumentException("INVALID LINK");
		}
	}
	
	private void setType(Type type) {
		if(type != null)
			this.type = type;
		else
			throw new IllegalArgumentException("INVALID CONDITION TYPE");
	}
	
	private void setId(String id) {
		if(id != null && id.length() > 0)
			this.id = new SimpleStringProperty(id);
		else
			throw new IllegalArgumentException("YOU MUST PROVIDE A VALID CONDITION ID");
	}
	
	private void setValidity(ConditionValidity validity) {
		if(validity != null)
			this.validity = validity;
		else
			throw new IllegalArgumentException("ERROR IN VALIDITY");
	}
	
	private void setValue(ConditionValue value ) {
		if(value == null || listIncludeAtLeast(value,this.type.getValidValues())) {
			this.value = value;
		}
		else
			throw new IllegalArgumentException("PLEASE VERIFY THE CONDITION VALUE");
	}
	
	//Redondance à retirer
	private final static boolean listIncludeAtLeast(Object element,Object[] possibleValues) {
		
		boolean doInclude = possibleValues == null || possibleValues.length == 0;
		if(!doInclude){
			int index = 0;
			while(index < possibleValues.length && !doInclude) {
				doInclude = element.equals(possibleValues[index]);
				index++;
			}
		}
				
		return doInclude;
	}


	public LogicalLink getLink() {
		return this.link;
	}	
	
	public Type getType() {
		return type;
	}
	
	public StringProperty getId() {
		return this.id;
	}
	
	public ConditionValidity getValidity() {
		return this.validity;
	}
	
	public ConditionValue getValue() {
		return this.value;
	}
	
	public String getTypeText() {
		return type.getValue();
	}
	
	public String getIdText() {
		return this.id.get();
	}
	
	public String getValidityText() {
		return this.validity.getValue();
	}
	
	public String getValueText() {
		String representation = "";
		if(value != null)
			representation =  this.value.getValue();
		return representation;
	}

	
	@Override
	public String toString() {
		
		String text = getTypeText();
		text += SEPARATOR + getIdText();
		text += SEPARATOR + getValidityText();
		text += SEPARATOR + getValueText();
		
		return text;
	}
	
}
