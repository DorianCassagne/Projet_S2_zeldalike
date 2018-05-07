package model;

public class Item implements WithImageDefiner{
	private static final String[] SIGNIFICATIONS = {"Neige"};
	private int value;
	
	public Item(String name) {
		int value = StaticMethods.getIndex(SIGNIFICATIONS, name);
		if(StaticMethods.getIndex(SIGNIFICATIONS, name) != -1) {
			this.value = value;
		}
		else {
			throw new IllegalArgumentException(StaticMethods.NOTFOUNDCODE) ;
		}
	}
	
	public Item(int value) {
		if(value >= 0 && value <= SIGNIFICATIONS.length) {
			this.value = value;
		}else {
			throw new IllegalArgumentException(StaticMethods.ILLEGALARGUMENTCODE);
		}
	}
	
	
	@Override
	public String getImagePath() {
		return null;
	}

}
