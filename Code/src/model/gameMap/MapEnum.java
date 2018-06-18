package model.gameMap;

/*
 * Enumeration des maps du jeu 
 * attrributs : - positionX et positionY (point de spawn du hero)
 * 				- nom du fichier de scenario 
 * 				- nom des differents calques de la map
 */
public enum MapEnum {
	
	MAPDEBUT(false,32,33,"Scenario-Map-0.txt","DEFAULT-ITEM.csv","Theatre_1fond.csv","Theatre_2mur.csv","Theatre_3scene.csv","Theatre_4deco.csv"),
    MAPFORET(true,10,22,"Scenario-Map-1.txt","DEFAULT-ITEM.csv","Foret1-2_1Fond.csv","Foret1-2_2chemin.csv","Foret1-2_3arbre.csv","Foret1-2_4arbre.csv","Foret1-2_5arbrerocher.csv","Foret1-2_6porte.csv"),
    MAPBOSS(true,31,50,"Scenario-Map-2.txt","DEFAULT-ITEM.csv","BossRoom1-1_1fond.csv","BossRoom1-1_2mur.csv","BossRoom1-1_3deco.csv");

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
