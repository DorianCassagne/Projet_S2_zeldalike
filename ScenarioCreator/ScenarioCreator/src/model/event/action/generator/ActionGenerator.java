package model.event.action.generator;

import javafx.beans.property.SimpleStringProperty;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ActionGenerator {
	
	private final static String ACTIONSEPARATOR = "->";
	private ObservableList<Action> myActions;
	private StringProperty representation;
	
	public ActionGenerator() {
		this.myActions = FXCollections.observableArrayList();
		this.representation = new SimpleStringProperty();
	}
	
	
	public StringProperty representationProperty() {
		return this.representation;
	}
	
	public boolean isComplete() {
		return this.myActions.size() > 0;
	}
	
	public void addAction(Action condition) {
		if(condition != null ) {
			this.myActions.add(condition);
			this.setRepresentation();
		}
	}
	
	private void setRepresentation() {
		if(this.isComplete()) {
			this.representation.set(this.myActions.get(0).toString());
		}
	}
	
	public void removeAction(int conditionId) {
		if(conditionId >= 0 && conditionId < this.myActions.size()) {
			this.myActions.remove(conditionId);
			this.setRepresentation();
		}
	}
	
	
	public void modifyAction(Action action,int actionId) {
		if(action != null && actionId >= 0 && actionId < this.myActions.size()) {
			this.myActions.set(actionId, action);
			this.setRepresentation();
		}
	}	
	
	
	public final ObservableList<Action> getActions(){
		return this.myActions;
	}
	
	@Override
	public String toString() {
		String text = "";
		for(Action action : myActions) {
			text += ACTIONSEPARATOR + action.toString();
		}
		return text;
	}
	
	
}
