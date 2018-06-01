package model;

import java.util.Map;

import javafx.beans.property.IntegerProperty;
import model.character.Dolphin;
import model.character.GameCharacter;
import model.character.Hero;
import model.character.Movable;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.GameMap.MapEnum;
import model.gameMap.additional.NewMovable;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Game {
	public final static int HEROKEY = 0;
	private final static String mapName = "modifiedMap1.csv";
	private final static String mapName2 = "MapForet1.csv";
	
	private GameMap myMap;
	private Hero hero;
	
	
	//Initialise le jeu avec une map
	public Game() {
		myMap = new GameMap(mapName);
		this.hero = new Hero(myMap, 20, 27);
		new Dolphin(myMap,14,15);

	}
	
	public IntegerProperty getChangeProperty() {
		return myMap.getChangeProperty();
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
	/**
	 * retourne le hero
	 * @return
	 */
//	public GameCharacter delMap() {
//		for (Map.Entry mapentry : myMap.getMovableList().entrySet()) {
//			 
//			
//		}
//		
//	}
//	public void createMap() {
//		
//	}
//	
//	//change la map de la game et la map du joueur 
//	public void changeMap(MapEnum newMap) {
//		 
//		//myMap.delChar	
		
//		GameMap map= new GameMap(newMap.getPath());
//		this.hero.setMap(map);
//		this.myMap = map;
//		this.hero.setCellId(newMap.getPosX(), newMap.getPosY());
//		
//	}
	
	/*
	public int getCharCell(Integer charKey) {
		return this.myMap.getCharacterPosition(charKey);
	}
	*/
	
}
