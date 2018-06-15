package resources.additionalClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class SeparatorFileReader {

	public final static String TOIGNORE = "//";
	
	public static BufferedReader openTextFile(String path,boolean localFile)  {
		try {
			File file;
			System.out.println(path);
			
			if(localFile )
				file = new File(SeparatorFileReader.class.getResource(path).toURI().toURL().getPath());
			else
				file = new File(path);
			
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);

			return reader;
		}catch(IOException exception) {

			throw new IllegalArgumentException("Error while reading file");
		}catch(URISyntaxException exception) {
			throw new IllegalArgumentException("Error in file path");
		}
	}
	
	
	public static Integer[] readAllIntLines(BufferedReader reader,String separator,int lineLength) {
		try{
			if(separator == null)
				throw new IllegalArgumentException("The separator is null");
			Integer[] valueTable = new Integer[lineLength * lineLength];
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
	
	
	
	public static ArrayList<ArrayList<String[]>> readFileWithTwoSeparator(BufferedReader reader,String separator1,String separator2){
		ArrayList<ArrayList<String[]>> list = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> firstList = new ArrayList<String[]>();
		ArrayList<String[]> smallList;
		
		try {
			String line = reader.readLine();
			
			while(line != null) {
				if(!line.startsWith(TOIGNORE) && line.length() != 0)
					firstList.add(line.split(separator1));
				line = reader.readLine();
			}
			
			for(String[] arrayOfString : firstList) {
				smallList = new ArrayList<String[]>();
				for(String element : arrayOfString) {
					smallList.add(element.split(separator2));
				}
				list.add(smallList);
			}
			
		}catch(IOException exception) {
			throw new IllegalArgumentException("Error while reading file");
		}catch(NullPointerException exception) {
			throw new IllegalArgumentException("The reader doesn't respect the map criteria");
		}catch(NumberFormatException exception) {
			throw new IllegalArgumentException("The file doesn't contains integer only");
		}

		
		
		return list;
	}
	
	private static void convertAndTransferValToIntTable(Integer[] destination,String[] source, int startIndex) {
		for(int i = 0 ; i < source.length; i++) {
			destination[startIndex + i ] = Integer.parseInt(source[i]);
		}
	}
	
	
	public static ArrayList<String[]> readFileWithOneSeparator(BufferedReader reader,String separator) throws IOException{
		ArrayList<String[]> list = new ArrayList<String[]>();
		String line = reader.readLine();
		
		while(line != null) {
			list.add(line.split(separator));
			line = reader.readLine();
		}
		
		return list;
		
	}
	
}
