package model.gameMap.additional;

import java.io.BufferedReader;

import resources.additionalClass.SeparatorFileReader;




public class MapReader {
	public final static String MAPFILEPATH = "/resources/map/";
	public final static String SEPARATOR = ",";
	public final static int MAPLENGTH = 64;
	
	//lit un fichier csv et le convertit en tableau d'entier
	public static int[]  readAndConvertMapFile(String path) {

		BufferedReader reader = SeparatorFileReader.openTextFile(MAPFILEPATH + path);
		int[] result = SeparatorFileReader.readAllIntLines(reader, SEPARATOR, MAPLENGTH);

		return result;
	}
	
}
