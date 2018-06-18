package resources.additionalClass;

import java.io.FileWriter;
import java.io.IOException;
/*
 * Classe qui ecrit / creer un fichier 
 */
public class SeparatorFileWriter {
	public static boolean writeToFile(String path,String textToWrite,boolean append) {
		boolean writeSuccess = true;
		
		try{
			FileWriter writer = new FileWriter(path,append);
			writer.write(textToWrite);
			writer.close();
		}catch(IOException e) {
			writeSuccess = false;
		}
		
		return writeSuccess;
	}
}
