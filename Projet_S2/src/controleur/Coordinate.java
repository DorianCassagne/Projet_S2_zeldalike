package controleur;

public class Coordinate {
	private double posX;
	private double posY;

	public Coordinate(double posX, double posY) {
		this.posX=posX;
		this.posY=posY;
	}

	public Coordinate(Coordinate coord) {
		this.posX=coord.getPosX();
		this.posY=coord.getPosY();
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		if(posX < 0) {
			throw new Error ("X location from Coordinate is non existent");
		}
		else{
			this.posX = posX;
		}
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		if(posY < 0) {
			throw new Error ("Y location from Coordinate is non existent");
		}
		else{
			this.posY = posY;	
		}
	}

	public boolean egalX(Coordinate coord) {
		if(this.posX == coord.getPosX()) {
			return true;
		}
		return false;
	}

	public boolean superieurX(Coordinate coord){
		if(this.posX < coord.getPosX()) {

		}
		return false;
	}

	public boolean egalY(Coordinate coord) {
		if(this.posY == coord.getPosY()) {
			return true;
		}
		return false;
	}

	public boolean superieurY(Coordinate coord) {
		if(this.posY < coord.getPosY()) {
			return true;
		}
		return false;
	}

}
