package model.gameMap;

public enum MapEnum {
	
	MAPDEBUT(14,13,"scenario1.txt","BelleMap_ItemLayer.csv","BelleMap_Background.csv","BelleMap_Foreground.csv"),
	MAPFORET(10,22,"scenario1.txt","newlevel_item.csv","newlevel_Calque1.csv","newlevel_Calque2.csv","newlevel_Calque3.csv","newlevel_Calque4.csv","newlevel_Calque5.csv");	
	
	private String[] layers;
	private int heroPosX;
	private int heroPosY;
	private String scenario;
	
	MapEnum(int posX,int posY,String scenario,String ... layers) {
		this.layers = layers;
		this.heroPosX=posX;
		this.heroPosY=posY;
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
