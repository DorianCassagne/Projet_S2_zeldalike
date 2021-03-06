package model.character.attack;

import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public interface Launcher {
	/*
	 * Lance une attaque En la creant sur la map 
	 * 
	 * @param GameMap map : la map sur laquelle va se lancer l'attaque
	 * @param Movement direction : la direction initiale de l'attaque
	 * @param int row,column : La position de départ de l'attaque
	 * @param int attackPT : les points d'attaque de lanceur
	 * 
	 * @param retourne le nombre de MP consommée par l'attaque
	 */
	
	public int launch(GameMap map,Movement direction,int row,int column,int attackPT);
	
	public int getImage();
	
	/*
	 * Retourne le nombre de degats infligee par l'attaque
	 * 
	 * @return int : degats inflige
	 * 
	 */
	public int getDamage();
	
	public int getManaConsume();
	
}
