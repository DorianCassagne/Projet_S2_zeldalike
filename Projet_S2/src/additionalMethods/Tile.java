package additionalMethods;

import mapZelda.MapReader;

public class Tile {

	public static final int TILESDIMENSION = 16;

	
	//Cette méthode se charge de transformer le numéro d'une Tile à sa zone dans le tileset

	public static int[] getTileInImage(int tileCode) {
		int x = tileCode % MapReader.HORIZONTALNBTILES;
		int y = tileCode / MapReader.HORIZONTALNBTILES;
		int[] dimension = {x,y};
		return dimension;
	}

}
