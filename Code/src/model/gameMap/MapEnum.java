package model.gameMap;

public enum MapEnum {
	
	MAPDEBUT(12,13,"testMap.csv","testMap.csv"),
	MAPFORET(12,13,"testMap.csv"),
	MAPBOSS1(12,13,"testMap.csv");
	
	private String[] layers;
	private int heroPosX;
	private int heroPosY;
	
	MapEnum(int posX,int posY,String ... layers) {
		this.layers = layers;
		this.heroPosX=posX;
		this.heroPosY=posY;
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
		
}
