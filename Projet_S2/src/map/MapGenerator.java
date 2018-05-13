package map;

import java.util.ArrayList;

import character.GameCharacter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class MapGenerator {

	public static int[][] getBackMap(String path){
		return readAllIntLines(path+"1.csv", ",");
	}
	public static int[][] getItemMap(String path){
		return readAllIntLines(path+"2.csv", ",");
		//TODO:
	}
	public static GameCharacter[][] getCharacMap(String path, ArrayList<GameCharacter> ar){
		return null;
		//TODO:doit ajouter dans la liste de GameCharacter a l'indice 0 un Game character qui doneras lemplacement du jouer en entrée de map
	}
	
	
	
	public static BufferedReader openTextFile(String path) throws IOException, URISyntaxException{
		File file = new File(MapGenerator.class.getResource(path).toURI().toURL().getPath());
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		return reader;
	}
		
	public static int[][] readAllIntLines(String path,String separator){
		String line;
		BufferedReader reader=null;
		try {
			reader = openTextFile(path);
		} catch (URISyntaxException | IOException e) {
			
			e.printStackTrace();
			throw new Error("failed opening map file");
		}
		ArrayList<int[]> lignes = new ArrayList<int[]>();
		try {
			while((line = reader.readLine()) != null) {
				lignes.add(StringArrayToInt(line.split(separator)));
			}
		} catch (IOException e) {

			e.printStackTrace();
			throw new Error("failed opening map file");
		}
		int[][] tab=new int[lignes.size()][lignes.get(0).length];
		tab=lignes.toArray(tab);
		return tab;
		//return lignes;
	}
	
	public static int[] StringArrayToInt(String[] arrayOfString) {
		int[] arrayOfInt = new int[arrayOfString.length];
		for(int i = 0 ; i < arrayOfString.length;i++) {
			arrayOfInt[i] = Integer.parseInt(arrayOfString[i]);
		}
		return arrayOfInt;
	}
	
}
