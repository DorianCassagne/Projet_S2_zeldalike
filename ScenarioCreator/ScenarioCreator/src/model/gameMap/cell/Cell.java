package model.gameMap.cell;

import ressources.additionalClass.Fusion;

public class Cell {

	
	private Background background;
	private int itemValue;
	
	
	public Cell(Integer[] backgroundValue,int itemValue){
		this.background = new Background(backgroundValue);
		this.itemValue = itemValue;
	}

	

	


	public Integer[] getCellBackgroundLayer() {
		return Fusion.fuseIntegerWithArray(this.background.getBackgroundList(),this.itemValue);
	}
	
			
	
	
}
