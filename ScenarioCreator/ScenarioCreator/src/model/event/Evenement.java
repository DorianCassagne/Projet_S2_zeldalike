package model.event;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.event.action.generator.ActionGenerator;
import model.event.condition.generator.ConditionGenerator;

public class Evenement {
	
	
	private ActionGenerator actionGenerator;
	private ConditionGenerator conditionGenerator;
	private static Integer id =  0 ;
	private StringProperty myId;
	private StringProperty comment;
	
	public Evenement(ActionGenerator actionGenerator,ConditionGenerator conditionGenerator,String comment) {
		if(actionGenerator != null && conditionGenerator != null && actionGenerator.isComplete() && conditionGenerator.isComplete()) {
			this.actionGenerator = actionGenerator;
			this.conditionGenerator = conditionGenerator;
			this.myId = new SimpleStringProperty(id.toString());
			this.initComment(comment);
			id++;
		}
		else {
			throw new IllegalArgumentException("YOU MAY FORGOT THE END LOGICAL LINK IN CONDITION");
		}
	}
	private void initComment(String comment) {
		if(comment == null)
			comment = "";
		this.comment = new SimpleStringProperty(comment);
	}
	
	public ActionGenerator getActionGenerator() {
		return this.actionGenerator;
	}
	
	public ConditionGenerator getConditionGenerator() {
		return this.conditionGenerator;
	}
	
	public StringProperty getId() {
		return this.myId;
	}
	
	public StringProperty commentProperty() {
		return this.comment;
	}
	
	public String getComment() {
		return this.comment.get();
	}
	
	@Override
	public String toString() {
		return conditionGenerator.toString() + actionGenerator.toString();
	}
	
	
	
}
