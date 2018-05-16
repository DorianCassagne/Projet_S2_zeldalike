package model.map;

public class Background {
	private int textureVal;
	private boolean walkable;
	
	public Background() {
		this.walkable=false;
	}
	
	public boolean isWalkable() {
		return walkable;
	}

	public int getTextureVal() {
		return textureVal;
	}
	
	public void setBackground(int imgVal) {
		this.textureVal=imgVal;
		this.walkable=BackgroundFactory.getWalkableImgVal(imgVal);
	}
	

	
	
	
}
