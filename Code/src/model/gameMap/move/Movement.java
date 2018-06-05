package model.gameMap.move;

import model.gameMap.additional.Statics;

public enum Movement {
	

	TOP(-Statics.STEP,0),
	RIGHT(0,Statics.STEP),
	BOTTOM(Statics.STEP,0),
	LEFT(0,-Statics.STEP),
	
	STAY(0,0);
	private int verticaly;
	private int horizontaly;
	
	Movement(int verticaly,int horizontaly){
		this.verticaly = verticaly;
		this.horizontaly = horizontaly;
	}
	
	public int getVerticalIncrement() {
		return this.verticaly;
	}
	
	public int getHorizontalIncrement() {
		return this.horizontaly;
	}
	
	public int getIndex() {
		return Math.abs(1 + this.verticaly - 2 * this.horizontaly );
	}
	
	

}
