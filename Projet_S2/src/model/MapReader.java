package model;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MapReader {

	private final static String MAPPATH = "/map/";
	public final static String SEPARATOR = ",";
	public static final int HORIZONTALNBTILES = 192;
	public static final int TILESDIMENSION = 16;
	private ArrayList<int[]> map ;
	public MapReader(String mapName) {
		try {
			
			BufferedReader reader = SeparatorFileReader.openTextFile(MAPPATH + mapName);
			this.map = SeparatorFileReader.readAllIntLines(reader, SEPARATOR);
		}catch(Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}
	
	public int getNbTilesHeight() {
		return this.map.size();
	}
	
	public int getNbTilesWidth() {
		return this.map.get(0).length;
	}
	
	public int getRepresentation(int row,int column) {
		return this.map.get(row)[column];
	}
	
	
	
	
	
	
}
