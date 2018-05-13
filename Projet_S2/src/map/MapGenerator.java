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
		System.out.println(path);
		return readAllIntLines(path+"1.csv", ",");

//		int[][] tab = {{1115,1123,1131},{1116,1124,1132},{1117,1125,1133}};
//		return tab;
	}
	public static int[][] getItemMap(String path){
//		int[][] tab = {{0,0,0},{0,0,0},{0,0,0}};
//		return tab;
		return readAllIntLines(path+"2.csv", ",");
		//TODO:
	}
	public static GameCharacter[][] getCharacMap(String path, ArrayList<GameCharacter> ar){
		GameCharacter[][] tab= {{null,null,null},{null,null,null},{null,null,null}};
		return null;
		//TODO:doit ajouter dans la liste de GameCharacter a l'indice 0 un Game character qui doneras lemplacement du jouer en entr�e de map
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
