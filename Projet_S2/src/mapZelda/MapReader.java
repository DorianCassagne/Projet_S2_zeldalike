package mapZelda;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import additionalMethods.ErrorCodes;
import additionalMethods.SeparatorFileReader;

public class MapReader {

	private final static String MAPPATH = "/map/";
	
	public static final int HORIZONTALNBTILES = 192;//Cet attribut représente le nombre de tiles dans le tileset
	public final static String SEPARATOR = ",";
	private ArrayList<int[]> map ;
	
	
	//
	public MapReader(String mapName) {
		try {
			
			BufferedReader reader = SeparatorFileReader.openTextFile(MAPPATH + mapName);
			this.map = SeparatorFileReader.readAllIntLines(reader, SEPARATOR);
		}catch(Exception e) {
			throw new IllegalArgumentException(ErrorCodes.ILLEGALARGUMENTCODE);
		}
	}
	
	public int getNbTilesHeight() {
		return this.map.size();
	}
	
	public int getNbTilesWidth() {
		int width = 0;
		if(!this.map.isEmpty())
			width = this.map.get(0).length;
		return width;
	}
	
	public int getRepresentation(int row,int column) {
		return this.map.get(row)[column];
	}
	
	
	
	
	
	
}
