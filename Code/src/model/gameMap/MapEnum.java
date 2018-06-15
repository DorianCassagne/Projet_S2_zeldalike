package model.gameMap;

public enum MapEnum {
	
	MAPDEBUT(40,47,"Scenario-Map-1.txt","Dungeon1-1_5item.csv","Dungeon1-1_1fond.csv","Dungeon1-1_2mur.csv","Dungeon1-1_3deco.csv","Dungeon1-1_4porte.csv"),
	MAPFORET(10,22,"Scenario-Map-1.txt","newlevel_item.csv","newlevel_Calque1.csv","newlevel_Calque2.csv","newlevel_Calque3.csv","newlevel_Calque4.csv","newlevel_Calque5.csv");	
	
	private String[] layers;
	private int heroPosX;
	private int heroPosY;
	private String scenario;
	
	MapEnum(int posX,int posY,String scenario,String ... layers) {
		this.layers = layers;
		this.heroPosX = posX;
		this.heroPosY = posY;
		this.scenario = scenario;
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
