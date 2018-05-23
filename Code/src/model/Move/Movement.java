package model.Move;


import model.gameMap.GameMap;

public  enum Movement {

	TOP(-GameMap.STEP,0),
	RIGHT(0,GameMap.STEP),
	BOTTOM(GameMap.STEP,0),
	LEFT(0,-GameMap.STEP);
	
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
	
	

}