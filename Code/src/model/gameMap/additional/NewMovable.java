package model.gameMap.additional;

public class NewMovable {
	private int cellId;
	private int key;
	private int imageValue;
	
	public NewMovable(int key,int cellId,int imageValue) {
		this.key = key;
		this.cellId = cellId;
		this.imageValue = imageValue;
	}
	
	public int getCellId() {
		return this.cellId;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public int getImageValue() {
		return this.imageValue;
	}
}
