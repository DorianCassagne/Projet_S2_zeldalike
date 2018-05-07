package model;

public class Background implements WithImageDefiner{
	private int value;
	private final static String[] SIGNIFICATIONS = {"Pierre","Herbe","Arbre","Eau"};
	private final static int[] TRAVERSABLE = {1,2};
	private final static int[] DEPLACABLE = {1};
	
	public Background(int value) {
		if(value >= 0 && value < SIGNIFICATIONS.length) {
			this.value = value;
		}
		else 
			throw new IllegalArgumentException(StaticMethods.INVALIDNAMECODE);
	}
	
	public Background(String name) {
		int value = StaticMethods.getIndex(SIGNIFICATIONS, name);
		if(StaticMethods.getIndex(SIGNIFICATIONS, name) != -1) {
			this.value = value;
		}
		else {
			throw new IllegalArgumentException(StaticMethods.NOTFOUNDCODE) ;
		}
	}
	
	
	public String toString() {
		return SIGNIFICATIONS[this.value] ;
	}
	
	
	public boolean estTraversable() {
		return StaticMethods.arrayContains(TRAVERSABLE,this.value);
	}
	
	public boolean estDeplacable() {
		return StaticMethods.arrayContains(DEPLACABLE, this.value);
	}

	@Override
	public String getImagePath() {
		return "Ok";
	}
	
	
	
}
