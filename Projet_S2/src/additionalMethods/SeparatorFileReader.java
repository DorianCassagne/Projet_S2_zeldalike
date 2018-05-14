package additionalMethods;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SeparatorFileReader {
	
	//Cette méthode ouvre un fichier text et renvoie une référence vers ce fichier
	//La méthode déclenche une excéption en cas d'érreur
	public static BufferedReader openTextFile(String path) throws IOException, URISyntaxException{
		File file = new File(SeparatorFileReader.class.getResource(path).toURI().toURL().getPath());
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		return reader;
	}
		
	//Cette méthode se charge de lire tous les éléments présents sur une ligne de chaîne de caractères
	//Elle renvoie une liste contenant des tableaux des éléments entiers trouvé dans chaque ligne
	//Cette méthode déclenche une exception en cas d'erreur
	
	public static ArrayList<int[]> readAllIntLines(BufferedReader reader, String separator) throws IOException,NumberFormatException{
		String line;
		ArrayList<int[]> lignes = new ArrayList<int[]>();
		while((line = reader.readLine()) != null) {
			lignes.add(StringArrayToIntArray(line.split(separator)));
		}
		return lignes;
	}
	
	public static int[] StringArrayToIntArray(String[] arrayOfString) {
		int[] arrayOfInt = new int[arrayOfString.length];
		for(int i = 0 ; i < arrayOfString.length;i++) {
			arrayOfInt[i] = Integer.parseInt(arrayOfString[i]);
		}
		return arrayOfInt;
	}
	
	
	
}
