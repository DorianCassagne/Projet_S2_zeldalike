package resources.additionalClass;

import java.io.FileWriter;
import java.io.IOException;
/*
 * Classe qui ecrit / creer un fichier 
 */
public class SeparatorFileWriter {
	public static boolean writeToFile(String path,String textToWrite,boolean append) {
		boolean writeSuccess = false;
				
		try{
			FileWriter writer = new FileWriter(path,append);
			writer.write(textToWrite);
			writer.close();
			writeSuccess = true;

		}catch(IOException e) {
		}
		
		return writeSuccess;
	}
	
	
}
