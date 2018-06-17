package model.gameMap;


public enum MapEnum {
	
	MAPDEBUT(false,39,33,"Scenario-Map-0.txt","DEFAULT-ITEM.csv","Theatre_1fond.csv","Theatre_2mur.csv","Theatre_3scene.csv","Theatre_4deco.csv"),
    MAPFORET(true,10,22,"Scenario-Map-1.txt","newlevel_item.csv","newlevel_Calque1.csv","newlevel_Calque2.csv","newlevel_Calque3.csv","newlevel_Calque4.csv","newlevel_Calque5.csv");

	private String[] layers;
	private int heroPosX;
	private int heroPosY;
	private String scenario;
	private boolean isBig;
	
	MapEnum(boolean isBig,int posX,int posY, String scenario,String ... layers) {
		this.isBig = isBig ;
		this.layers = layers;
		this.heroPosX = posX;
		this.heroPosY = posY;
		this.scenario = scenario;
	}
	
	public boolean getIsBig() {
		return this.isBig;
	}
	
	public String[] getLayers() {
		return this.layers;
	}
	public int getPosX() {
		return this.heroPosX;
	}
	public int getPosY() {
		return this.heroPosY;
	}
	
	public String getScenario() {
		return this.scenario;
	}
	
		
}
