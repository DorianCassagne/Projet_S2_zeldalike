package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Game {
	public final static String IMAGESPATH = "/img/";
	public final static Integer HEROKEY = 0;
	private Map currentMap ;
	
	public Game(String mapName) {
		this.currentMap = new Map(mapName);
	}
	
	public void startGame() {
		Hero hero = new Hero(currentMap,7,13);
	}
	
	
	public IntegerProperty changeProperty() {
		final IntegerProperty property = this.currentMap.changeProperty();
		return property;
	}
	
	public char getLastCharChangeCategory() {
		return this.currentMap.getCaseChangeCategory(this.currentMap.changeProperty().get());
	}
	
	
	public StringProperty getPersonnageImage(Integer key) {
		return this.currentMap.getPersonnageStringProperty(key);
	}
	
	public int getMapWidth() {
		return this.currentMap.getMapWidth();
	}
	
	public int getMapHeight() {
		return this.currentMap.getMapHeight();
	}
	
	public Background getBackground(int row,int column) {
		return this.currentMap.getBackground(row, column);
	}
	
	public Integer getLastChangedId() {
		return this.currentMap.getLastChangeId();
	}
	
	public int[] convertFromCaseId(int caseId) {
		return this.currentMap.convertFromCaseId(caseId);
	}
	public int convertToCaseId(int row,int column) {
		return this.currentMap.convertToCaseId(row, column);
	}
	
	
	public boolean isFromTrash(int ... values) {
		for(int value : values) {
			if(value == Map.TRASHINDEX) {
				return true;
			}
		}
		return false;
	}

	public void toTop(Integer key) {
		this.currentMap.getPersonnageByKey(key).toTop();
	}

	public void toLeft(Integer key) {
		this.currentMap.getPersonnageByKey(key).toLeft();
	}
	public void toBottom(Integer key) {
		this.currentMap.getPersonnageByKey(key).toBottom();;
	}
	public void toRight(Integer key) {
		this.currentMap.getPersonnageByKey(key).toRight();
	}
}
