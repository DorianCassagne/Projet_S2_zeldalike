package model.gameMap;

import model.gameMap.additional.MapReader;

public class Conversion {
	public static int convertToCellId(int row ,int column) {
		int cellId = row * MapReader.MAPLENGTH + column;
		return cellId;
	}
	
}
