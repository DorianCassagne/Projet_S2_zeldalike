package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import resources.additionalClass.SeparatorFileReader;

public class StatsLoader {
	final static String FILEPATH ;
	
	
	static {
		FILEPATH = System.getProperty("user.home") + "/omar.txt";
	}
	
	//Pour recharger à chaque fois .
	public static ObservableList<GameStatus> loadState() throws IOException {
		BufferedReader reader = SeparatorFileReader.openTextFile(FILEPATH,false);
		
		ArrayList<String[]> stringList = SeparatorFileReader.readFileWithOneSeparator(reader, GameStatus.SEPARATOR);
		
		return createGameStatus(stringList);
	
	}

	
	private static ObservableList<GameStatus> createGameStatus(ArrayList<String[]> stringList){
		ObservableList<GameStatus> statusList = FXCollections.observableArrayList();
		
		for(String[] list : stringList) {
			statusList.add(new GameStatus(list));
		}
		
		return statusList;
	}
}
