package model.map;

import javafx.beans.property.IntegerProperty;
import model.ModelConst;
import model.map.cell.Cell;
import model.map.cell.item.ItemFactory;

public class GameMap {
	private Cell[] cellList;
	
	public GameMap() {
		cellList=new Cell[ModelConst.SIZEMAP*ModelConst.SIZEMAP];
		for (int i = 0; i < cellList.length; i++ ) {
			cellList[i]=new Cell();
		}
	}
	
	public void setMapBackground(int[] backgroundValues) {
		for (int i =0; i< cellList.length; i++) {
			cellList[i].setBackground(backgroundValues[i]);
		}
	}
	public void setMapItem(int[] backgroundValues) {
		for (int i =0; i< cellList.length; i++) {
			if(backgroundValues[i]!=-1)
				cellList[i].setItem(ItemFactory.createItem(backgroundValues[i]));
		}
	}
	
	public IntegerProperty[] getMapChange() {
	IntegerProperty[] changedList = new IntegerProperty[cellList.length];
		for(int i = 0; i<cellList.length;i++) {
			changedList[i]=cellList[i].getChangeValue();
		}
		return changedList;
	}
	
}
