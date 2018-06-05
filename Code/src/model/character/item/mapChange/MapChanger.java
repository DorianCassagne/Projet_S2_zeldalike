package model.character.item.mapChange;

import model.character.hero.Hero;
import model.character.item.Item;

public class MapChanger extends Item{

	private static final int STARTMAPCHANGEINDEX = 10;
	
	private MapChangerEnum changer;
	
	public MapChanger(int mapIndex) throws IllegalArgumentException {
		super(STARTMAPCHANGEINDEX + mapIndex);
		if(mapIndex >= 0 && mapIndex < MapChangerEnum.values().length)
			this.changer = MapChangerEnum.values()[mapIndex];
		else
			throw new IllegalArgumentException("MAPINDEX IS NOT AVAILABLE");
	}

	@Override
	protected void applyTo(Hero hero) {
		this.changer.applyTo(hero);
	}
	
	
}
