package model.character.item.mapChange;
/*
 * Enumeration des indices de map
 */
import model.character.hero.Hero;

public enum MapChangerEnum  {
	MAPDEBUT(0),
	MAPFORET(1),
	MAPBOSS(2),
	MAPFIN(3);
	private int mapIndex ;
	
	MapChangerEnum(int mapIndex){
		this.mapIndex = mapIndex;
	}
	
	public  void applyTo(Hero hero) {
		hero.setMapChange(this);
	}
	
	public int getMapIndex() {
		return this.mapIndex;
	}
}
