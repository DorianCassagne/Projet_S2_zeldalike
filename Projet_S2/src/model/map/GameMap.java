package model.map;

import model.ModelConst;

public class GameMap {
	private Cell[] cellList;
	
	public GameMap() {
		cellList=new Cell[ModelConst.SIZEMAP*ModelConst.SIZEMAP];
		for (int i = 0; i < cellList.length; i++ ) {
			cellList[i]=new Cell();
		}
	}
	
}
