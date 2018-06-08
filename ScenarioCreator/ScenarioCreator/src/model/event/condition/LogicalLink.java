package model.event.condition;

import model.event.ElementNames;

public enum LogicalLink implements ElementNames{
	OR("OU","OR"),
	AND("ET","AND"),
	END("FIN","");
	
	private String nom;
	private String representation;
	
	LogicalLink(String nom,String representation){
		this.nom = nom;
		this.representation = representation;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}

	@Override
	public String getValue() {
		return this.representation;
	}
	
	
	
}
