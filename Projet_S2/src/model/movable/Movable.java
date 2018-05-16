package model.movable;

import controleur.Movement;
import model.map.GameMap;

public abstract class Movable {

	private int key;
	private int xValue;
	private int yValue;
	protected GameMap map;
	
	
	
	public Movable(GameMap map, int key) {
		this.map=map;
		this.key=key;
	}
	
	public Movement play() {
		return null;
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

	public int getKey() {
		return key;
	}

}
