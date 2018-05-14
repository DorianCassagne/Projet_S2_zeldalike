package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SeparatorFileReader {
	public static BufferedReader openTextFile(String path) throws IOException, URISyntaxException{
		File file = new File(SeparatorFileReader.class.getResource(path).toURI().toURL().getPath());
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		return reader;
	}
		
	public static ArrayList<int[]> readAllIntLines(BufferedReader reader, String separator) throws IOException{
		String line;
		ArrayList<int[]> lignes = new ArrayList<int[]>();
		while((line = reader.readLine()) != null) {
			lignes.add(StringArrayToInt(line.split(separator)));
		}
		return lignes;
	}
	
	public static int[] StringArrayToInt(String[] arrayOfString) {
		int[] arrayOfInt = new int[arrayOfString.length];
		for(int i = 0 ; i < arrayOfString.length;i++) {
			arrayOfInt[i] = Integer.parseInt(arrayOfString[i]);
		}
		return arrayOfInt;
	}
	
	
	
}
