package model;

import model.character.Hero;
import model.gameMap.GameMap;
import model.gameMap.additional.NewCharacter;
import model.gameMap.move.Move;

public class Game {
	private final static String mapName = "testMap.csv";
	
	private GameMap myMap;
	private Hero hero;
	
	
	//Initialise le jeu avec une map
	public Game() {
		myMap = new GameMap(mapName);
		this.hero = new Hero(myMap, 10, 10);
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
	
	public void communiquerMovement(char moveChar) {
		hero.setNextMove(moveChar);
	}
	
	/*
	public int getCharCell(Integer charKey) {
		return this.myMap.getCharacterPosition(charKey);
	}
	*/
	
}
