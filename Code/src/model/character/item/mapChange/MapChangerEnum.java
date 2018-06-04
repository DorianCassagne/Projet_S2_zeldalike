package model.character.item.mapChange;

import model.character.hero.Hero;

public enum MapChangerEnum  {
	MAP1(0),
	MAP2(1),
	MAP3(2);
	
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
