package resources.additionalClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;

public class SeparatorFileReader {
	
	public static BufferedReader openTextFile(String path)  {
		try {
			File file = new File(SeparatorFileReader.class.getResource(path).toURI().toURL().getPath());
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			return reader;
		}catch(IOException exception) {
			throw new IllegalArgumentException("Error while reading file");
		}catch(URISyntaxException exception) {
			throw new IllegalArgumentException("Error in file path");
		}
	}
	
	
	public static int[] readAllIntLines(BufferedReader reader,String separator,int lineLength) {
		try{
			if(separator == null)
				throw new IllegalArgumentException("The separator is null");
			int[] valueTable = new int[lineLength * lineLength];
			String line ;
			String[] splittedLine;
			for(int i = 0 ; i < lineLength;i++) {
				line = reader.readLine();
				splittedLine = line.split(separator);
				convertAndTransferValToIntTable(valueTable,splittedLine,i*lineLength);
			}
			return valueTable;
		}catch(IOException exception) {
			throw new IllegalArgumentException("Error while reading file");
		}catch(NullPointerException exception) {
			throw new IllegalArgumentException("The reader doesn't respect the map criteria");
		}catch(NumberFormatException exception) {
			throw new IllegalArgumentException("The file doesn't contains integer only");
		}
	}
	
	private static void convertAndTransferValToIntTable(int[] destination,String[] source, int startIndex) {
		for(int i = 0 ; i < source.length; i++) {
			destination[startIndex + i ] = Integer.parseInt(source[i]);
		}
	}
	
	
}
