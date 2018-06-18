package model.gameMap.additional;
/*
 * Classe MapReader
 * Est un outil qui permet de recuperer les valeurs pour chaque Tile de la map
 */
import java.io.BufferedReader;

import resources.additionalClass.SeparatorFileReader;

public class MapReader {
	public final static String MAPFILEPATH = "/resources/map/";
	public final static String SEPARATOR = ",";
	public final static int MAPLENGTH = 64;
	
	/*
	 * Methode qui lit un fichier csv et le convertit en tableau d'entier 
	 */
	public static Integer[][]  readAndConvertMapFile(String[] path) {
		Integer[][] result = new Integer[path.length][];
		BufferedReader reader;
		
		for(int i = 0 ; i < path.length;i++) {
			reader = SeparatorFileReader.openTextFile(MAPFILEPATH + path[i],true);
			Integer[] values = SeparatorFileReader.readAllIntLines(reader, SEPARATOR, MAPLENGTH);
			result[i] = values;
		}
		
		return result;
	}
	
}
