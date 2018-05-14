package model;

public class Tile {
	
	public static int[] getTileInImage(int tileCode) {
		int x = tileCode % MapReader.HORIZONTALNBTILES;
		int y = tileCode / MapReader.HORIZONTALNBTILES;
		int[] dimension = {x,y};
		return dimension;
	}

}
