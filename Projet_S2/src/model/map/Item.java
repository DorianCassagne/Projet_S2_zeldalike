package model.map;

import model.movable.Hero;

public abstract class Item {
	private int imgVal;
	
	public Item(int imgVal) {
		this.setImgVal(imgVal);
	}

	public int getImgVal() {
		return imgVal;
	}

	private void setImgVal(int imgVal) {
		this.imgVal = imgVal;
	}
	
	public void applyToHero(Hero hero) {
		
	}
	
}
