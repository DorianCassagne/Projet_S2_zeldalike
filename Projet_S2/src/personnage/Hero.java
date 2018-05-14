package personnage;

import mapZelda.Map;

public class Hero extends GameCharacter{
	private final static String[] ZELDAIMAGE = {"fairyback.png","fairyprofil.png","fairyface.png","fairyprofil.png"};
	
	public Hero(Map map,int startRow,int startColumn) {
		super("Fairy",ZELDAIMAGE[3],map,startRow,startColumn);
	}


	@Override
	public String getTopImage() {
		return ZELDAIMAGE[0];
	}

	@Override
	public String getBottomImage() {
		return ZELDAIMAGE[2];
	}

	@Override
	public String getLeftImage() {
		return ZELDAIMAGE[3];	
	}

	@Override
	public String getRightImage() {
		return ZELDAIMAGE[1];
	}




}
