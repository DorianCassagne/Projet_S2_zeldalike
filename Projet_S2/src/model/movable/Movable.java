package model.movable;

import controleur.Movement;
import model.map.GameMap;

public abstract class Movable {

	private int xValue;
	private int yValue;
	protected GameMap map;
	
	public Movement play() {
		return null;
	}
	
	public Movable(GameMap map) {
		this.map=map;
	}

	protected int getxValue() {
		return xValue;
	}

	protected void setxValue(int xValue) {
		this.xValue = xValue;
	}

	protected int getyValue() {
		return yValue;
	}

	protected void setyValue(int yValue) {
		this.yValue = yValue;
	}
}
