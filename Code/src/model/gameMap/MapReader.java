package model.gameMap;

import java.io.BufferedReader;

import resources.additionalClass.SeparatorFileReader;




public class MapReader {
	public final static String MAPFILEPATH = "";
	public final static String SEPARATOR = ",";
	public final static int MAPLENGTH = 100;
	
	public static int[]  readAndConvertMapFile(String path) {
		BufferedReader reader = SeparatorFileReader.openTextFile(MAPFILEPATH + path);
		int[] result = SeparatorFileReader.readAllIntLines(reader, SEPARATOR, MAPLENGTH);
		return result;
	}
	
}
