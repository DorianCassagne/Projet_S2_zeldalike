package model;

import model.character.BadMonkey;
import model.character.Dolphin;

import model.character.Hero;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.additional.NewMovable;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Game {
	public final static int HEROKEY = 0;
	private final static String mapName = "MapForet1.csv";
	
	private GameMap myMap;
	private Hero hero;
	
	
	//Initialise le jeu avec une map
	public Game() {
		myMap = new GameMap(mapName);
		this.hero = new Hero(myMap, 16, 17);
		new Dolphin(myMap,14,15);
		new BadMonkey(myMap,12,21);
		new BadMonkey(myMap,16,20);
		new BadMonkey(myMap,12,20);
		new BadMonkey(myMap,13,20);
		new BadMonkey(myMap,13,19);
	}
	
	//renvoie l'identifiant du fond pour une cellude donnée.
	public int getBackgroundId(int cellId) {
		return this.myMap.getBackgroundImage(cellId);
	}
	
	//renvoie la liste des caractères crées pendant un tour 
	public NewMovable[] getNewPlayers() {
		return this.myMap.getNewCharList();
	}
	
	//renvoie la liste des movements effectués pendant un tour
	public Move[] turn() {
		return this.myMap.turn();
	}
	
	//renvoie vrai si le jeu est arrivé à sa fin
	public boolean end() {
		return !this.hero.isAlive();
	}
	
	public void communiquerMovement(char moveChar) {
		hero.setNextMove(moveChar);
	}
	
	public int[] getRemovedMovable() {
		return this.myMap.getRemovedCharacter();
	}
	
	/*
	public int getCharCell(Integer charKey) {
		return this.myMap.getCharacterPosition(charKey);
	}
	*/
	
}
