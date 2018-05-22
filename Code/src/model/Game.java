package model;

import model.gameMap.GameMap;
import model.gameMap.additional.NewCharacter;
import model.gameMap.move.Move;

public class Game {
	private final static String mapName = "smallMap.csv";
	
	private GameMap myMap;
	
	
	//Initialise le jeu avec une map
	public Game() {
		myMap = new GameMap(mapName);
	}
	
	//renvoie l'identifiant du fond pour une cellude donnée.
	public int getBackgroundId(int cellId) {
		return this.myMap.getBackgroundImage(cellId);
	}
	
	//renvoie la liste des caractères crées pendant un tour 
	public NewCharacter[] getNewPlayers() {
		return this.myMap.getNewCharList();
	}
	
	//renvoie la liste des movements effectués pendant un tour
	public Move[] turn() {
		return this.myMap.turn();
	}
	
	//renvoie vrai si le jeu est arrivé à sa fin
	public boolean end() {
		return false;
	}
	
	public int getCharCell(Integer charKey) {
		return this.myMap.getCharacterPosition(charKey);
	}
	
}
