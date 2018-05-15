package model;

import cell.Background;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import mapZelda.Map;
import personnage.Hero;

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
		return this.currentMap.getCellChangeCategory(this.currentMap.changeProperty().get());
	}
	
	public Integer getLastChangedId() {
		return this.currentMap.getLastChangedCharacterId();
	}

	
	
	public StringProperty characterImageNameProperty(Integer key) {
		return this.currentMap.getCharacterImageProperty(key);
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
	
	
	public int[] convertFromCellId(int caseId) {
		return this.currentMap.convertFromCellId(caseId);
	}
	public int convertToCellId(int row,int column) {
		return this.currentMap.convertToCellId(row, column);
	}
	
	
	public boolean isFromVoidCell(int ... values) {
		for(int value : values) {
			if(value == Map.TRASHINDEX) {
				return true;
			}
		}
		return false;
	}

	
	
	public void toTop(Integer key) {
		this.currentMap.getCharacterByKey(key).toTop();
	}
	public void toLeft(Integer key) {
		this.currentMap.getCharacterByKey(key).toLeft();
	}
	public void toBottom(Integer key) {
		this.currentMap.getCharacterByKey(key).toBottom();;
	}
	public void toRight(Integer key) {
		this.currentMap.getCharacterByKey(key).toRight();
	}
	
	public int getWalkTime(Integer key) {
		return this.currentMap.getCharacterByKey(key).getWalkTime();
	}
}
