package model.gameMap.move;

public class Move {
	private int movableId;
	private int endCellId;
	private int speed;
	private int imageValue;
	
	/*
	 * permet d'indiquer un mouvement de movable
	 * @param movableId 
	 * @param startCellId
	 * @param endCellId
	 * @param speed
	 * @param imageValue
	 */
	
	
	public Move(int endCellId,int speed,int imageValue) {
		this.endCellId = endCellId;
		this.imageValue=imageValue;
		this.speed=speed;
	}
	
	public void setMovableId(int movableId) {
		this.movableId = movableId;
	}
	
	
	
	public int getMovableId() {
		return this.movableId;
	}
	
	
	public int getEndCellId() {
		return this.endCellId;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public int getImageValue() {
		return this.imageValue;
	}


}
