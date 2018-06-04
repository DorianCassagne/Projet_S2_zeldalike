package model.scenario.condition;

import resources.additionalClass.Conversion;
import resources.additionalClass.UsefulMethods;

public class ConditionEncode {

	private static final int  PARAMLENGTH = 4;

	private char category;
	private String id;
	private char conditionValue;
	private char detail;
	private int lifePoint;
	
	public ConditionEncode(String[] params) {
		if(params != null && params.length == PARAMLENGTH) {
			this.category = Conversion.toChar(params[0]);
			this.id = params[1];
			this.conditionValue = Conversion.toChar(params[2]);
			try {
				this.lifePoint = Integer.parseInt(params[3]);
			}catch(NumberFormatException e) {
				this.detail = Conversion.toChar(params[3]);
			}
			
		}
	}
	
	public char getCategory() {
		return this.category;
	}
	
	public String getId() {
		return this.id;
	}
	
	public char getConditionValue() {
		return this.conditionValue;
	}
	
	public char getDetail() {
		return this.detail;
	}
	
	public int getLifePoint() {
		return this.lifePoint;
	}
}
