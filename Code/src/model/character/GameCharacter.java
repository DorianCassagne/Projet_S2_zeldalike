/*
 * A discuté, un Gamecharacte peut etre un ennemi, doit on implant� de la defense a un ennemi?
 *  Lui donner de la def en plus c'est comme lui donner de la vie en plus, cela va juste rallonger les combats
 *  pas sur que la def soit un ajout pertinent 
 */
/*
 * super Classe GameCharacter (etendu par les ennemi et le heros) etend Movable
 * un gameCharacter a une map une position de spawn, un cycle pour effectuer des actions, un coefficient de vitesse d'animation de deplacement, une image)  
 * un gameCharacter a la possibilite d'attaque, de faire des degats
 * le gamecharacter se rajoute a la map lui meme 
 */

package model.character;

import model.character.attack.Attack;
import model.character.hero.Hero;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public abstract class GameCharacter extends Movable{

	
	public final static double DEFAULTCOEFFICENT = 1.5;
	private static Hero Hero;
	
	
	//Crée un deplaçable ayant les propriétées voulue. Déclenche une erreur si l'une des propriétés est invalid (négatif ou null)
	
	
	
	public final static Hero getHero() {
		return Hero;
	}
	
	public final static void setHero(Hero hero) {
		if(hero != null) {
			Hero = hero;
		}
	}
		
	
	public GameCharacter(GameMap map,int startRow,int startColumn,int cycle,double coefficient,int defaultImage) {
		super(map,cycle,startRow,startColumn,coefficient,defaultImage);
		if( map == null ) {
			throw new IllegalArgumentException("ARGUMENT INVALID on GAMECHARACTER ");
		}
		else {
			this.setMap(map,startRow,startColumn);
		}
	}
	
	/*
	 * methode qui permet de s'ajouter a la map 
	 * exception si le personnage n'a pas pu etre plac� ( place sur un obstacle, en dehors de la map) 
	 */
	protected void setMap(GameMap newMap,int row,int column) {

		boolean put = newMap.addCharacter(this, row, column);

		if(!put) {
			throw new IllegalArgumentException("MAP UNDEFINED BECAUSE A PLAYER COULDN'T BE PLACED");
		}
		else
			this.setMap(newMap);
		
	}
	


	public abstract void launchAttack(Movement move);
	public abstract void getDmg(Attack attack);
	
	
	public final static int calculateDamage(int dmg,int def) {
		return (100*dmg/(100+def));
	}

	
}
