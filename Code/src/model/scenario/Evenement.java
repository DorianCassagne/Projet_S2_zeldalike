package model.scenario;

import java.util.function.Supplier;


public class Evenement {
	private Supplier<Boolean> condition;
	private Supplier<Boolean>[] actions;
	
	public Evenement(Supplier<Boolean> condition,Supplier<Boolean> ... action) {
		if(action == null || condition == null)
			throw new IllegalArgumentException("ERROR ON EVENEMENT");
		this.actions = action;
		this.condition = condition;
	}
	
	public boolean evaluate() {
		boolean done = false;
		if(this.condition.get()) {
			int counter = 0;
			done = true;
			while(counter < actions.length) {
				if(actions[counter] != null && actions[counter].get()) {
					actions[counter] = null;
					done = false;
				}
				counter++;
			}

		}
		return done;
	}
	
}
