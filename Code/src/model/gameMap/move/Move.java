package model.gameMap.move;
/*
 * Classe Move, relatif au mouvement des Movables
 */
public class Move {
	private int movableId;
	private int endCellId;
	private int speed;
	private char animation;
	
	/*
	 * permet d'indiquer un mouvement de movable
	 * @param movableId 
	 * @param startCellId
	 * @param endCellId
	 * @param speed
	 * @param imageValue
	 */
	
	
	public Move(int endCellId,int speed) {
		this.endCellId = endCellId;
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
	
	public int getAnimation() {
		return this.animation;
	}
	
}
