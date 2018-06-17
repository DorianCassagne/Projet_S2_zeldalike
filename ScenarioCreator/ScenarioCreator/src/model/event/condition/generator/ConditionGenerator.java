package model.event.condition.generator;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.event.condition.elements.*;
public class ConditionGenerator {
	private final static String CONDITIONSEPARATOR = "-";
	
	private boolean isComplete;
	private ObservableList<Condition> myConditions;
	private StringProperty representation; 
	
	public ConditionGenerator() {
		this.myConditions = FXCollections.observableArrayList();
		this.representation = new SimpleStringProperty();
		this.isComplete = false;
	}
	
	public void addCondition(Condition condition) {
		if(condition != null && !this.isComplete) {
			if(checkLogicalLink(condition)) {
	    		this.myConditions.add(condition);	
				this.setRepresentation();
			}
			else {
				throw new IllegalArgumentException("ERROR IN LOGICAL LINK");
			}
		}
		else {
			throw new IllegalArgumentException("CANNOT ADD AFTER END (LOGICAL LINK)");
		}
	}
	
	private boolean checkLogicalLink(Condition condition) {
		boolean valid = !this.isComplete;
		if(!this.isComplete) {
			this.isComplete = condition.getLink().equals(LogicalLink.END);
		}
		return valid;
	}
	
	
	public void removeCondition(int conditionId) {
		if(conditionId >= 0 && conditionId < this.myConditions.size()) {
			Condition condition = this.myConditions.get(conditionId);
			this.myConditions.remove(conditionId);
			this.setRepresentation();
			if(this.isComplete && condition.getLink().equals(LogicalLink.END))
				this.isComplete = false;
		}
	}
	
	
	public void modifyCondition(Condition condition,int conditionId) {
		if(condition != null &&  conditionId >= 0 && conditionId < this.myConditions.size()) {
			Condition old  = this.myConditions.get(conditionId);
			if(this.isComplete && condition.getLink().equals(LogicalLink.END) && !old.getLink().equals(LogicalLink.END))
				throw new IllegalArgumentException("CANNOT MODIFY TO A LOGICAL LINK OF END");
			else {
				this.myConditions.set(conditionId, condition);
				if(old.getLink().equals(LogicalLink.END))
					this.isComplete = false;
				this.setRepresentation();
			}
		}
	}
	
	
	public StringProperty representationProperty() {
		return this.representation;
	}
	
	private void setRepresentation() {
		if(this.isComplete()) {
			this.representation.set(this.myConditions.get(0).toString());
		}
	}	
	
	public final ObservableList<Condition> getConditions(){
		return this.myConditions;
	}
	
	public boolean isComplete() {
		return this.isComplete;
	}
	
	
	@Override
	public String toString() {
		String text = "";
		for(Condition condition:myConditions) {
			text +=  condition.toString() ;
			if(!condition.getLink().equals(LogicalLink.END)) {
				text += CONDITIONSEPARATOR + condition.getLink().getValue() + CONDITIONSEPARATOR;
			}
		}
		return text;
	}
}
