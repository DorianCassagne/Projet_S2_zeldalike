package model.character;

import model.gameMap.GameMap;

public class Hero extends GameCharacter{
	private final static int DEFAULTSPEED = 10;
	private final static int DEFAULTHP = 300;
	private final static int DEFAULTDEF = 200;
	
	private final static int[] IMAGES = {8,10,9,11};
	
	public Hero(GameMap map,int startRow,int startColumn) {
		super(map, DEFAULTSPEED, Movable.HEROTYPE, DEFAULTHP, DEFAULTDEF,startRow,startColumn);
		
	}
	

	@Override
	public int getDefaultImage() {
		return IMAGES[Movable.DEFAULTIMAGEINDEX];
	}
	

}
