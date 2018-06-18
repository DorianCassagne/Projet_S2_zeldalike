package model.character.item.mapChange;

import model.character.hero.Hero;

public enum MapChangerEnum  {
	MAPDEBUT(0),
	MAPFORET(1),
	MAPBOSS(2);
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
