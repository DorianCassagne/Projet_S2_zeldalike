package model.scenario;

import java.util.function.Supplier;


public class Evenement {
	private Supplier<Boolean> condition;
	private Supplier<Boolean>[] actions;
	private int id;
	
	public Evenement(int id,Supplier<Boolean> condition,Supplier<Boolean>[] action) {
		if(action == null || condition == null || id < 0)
			throw new IllegalArgumentException("ERROR ON EVENEMENT");
		this.actions = action;
		this.condition = condition;
		this.id = id + 1;
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
	
	public int getId() {
		return this.id;
	}
	
	@Override
	public String toString() {
		return ""+this.id;
	}
}
