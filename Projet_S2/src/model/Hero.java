package model;

public class Hero extends Personnage{
	private final static String[] IMAGEZELDA = {"fairyback.png","fairyprofil.png","fairyface.png","fairyprofil.png"};
	
	public Hero(Map map,int caseDebutX,int caseDebutY) {
		super("Fairy",IMAGEZELDA[3],map,caseDebutX,caseDebutY);
	}


	@Override
	public String getTopImage() {
		return IMAGEZELDA[0];
	}

	@Override
	public String getBottomImage() {
		return IMAGEZELDA[2];
	}

	@Override
	public String getLeftImage() {
		return IMAGEZELDA[3];	
	}

	@Override
	public String getRightImage() {
		return IMAGEZELDA[1];
	}




}
