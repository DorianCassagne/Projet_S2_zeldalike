package personnage;

import mapZelda.Map;

public class Hero extends GameCharacter{
	private final static String[] ZELDAIMAGE = {"Piskel1.png","fairyprofil.png","fairyface.png","fairyprofil.png"};

	private int walkSpeed ;
	
	public Hero(Map map,int startRow,int startColumn) {
		super("Fairy",ZELDAIMAGE[3],map,startRow,startColumn);
		this.walkSpeed = 10;
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


	@Override
	protected int getWalkSpeed() {
		return this.walkSpeed;
	}




}
