package mapZelda;

public  enum Move {
	

	TOP(-Map.STEP,0),
	BOTTOM(Map.STEP,0),
	LEFT(0,-Map.STEP),
	RIGHT(0,Map.STEP);
	
	private int verticaly;
	private int horizontaly;
	Move(int verticaly,int horizontaly){
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
