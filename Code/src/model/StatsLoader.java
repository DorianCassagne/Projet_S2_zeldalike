package model;
/*
 * Classe qui permet de charger les statistiques
 * permet de recuperer les sauvegardes de partie
 */
import java.io.BufferedReader;
import java.io.FileWriter;
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
		ArrayList<String[]> stringList = null;
		try {
			BufferedReader reader = SeparatorFileReader.openTextFile(FILEPATH,false);
			
			stringList = SeparatorFileReader.readFileWithOneSeparator(reader, GameStatus.SEPARATOR);
		}catch(IllegalArgumentException e) {
			FileWriter writer = new FileWriter(FILEPATH,true);
			writer.close();
		}
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
