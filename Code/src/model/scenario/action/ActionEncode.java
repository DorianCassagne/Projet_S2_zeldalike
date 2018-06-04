package model.scenario.action;

import resources.additionalClass.Conversion;

public class ActionEncode {
	
	private final static int NBPARAMS = 5;

	private char action ;
	private char generalType;
	private String specificType;
	private String info;
	private int idCase;
	
	public ActionEncode(String[] params) {
		if(params.length == NBPARAMS) {
			this.action = Conversion.toChar(params[0]);
			this.generalType = Conversion.toChar(params[1]);
			this.specificType = params[2];
			this.info = params[3];
			this.idCase = Conversion.toInt(params[4]);
		}
		else
			throw new IllegalArgumentException();
	}
	
	public char getAction() {
		return this.action;
	}
	
	public char getGeneralType() {
		return this.generalType;
	}
	
	public String getSpecificType() {
		return this.specificType;
	}
	
	public String getInfo() {
		return this.info;
	}
	
	public int idCase() {
		return this.idCase;
	}

	
	
}
