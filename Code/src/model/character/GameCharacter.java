/*
 * A discuté, un Gamecharacte peut etre un ennemi, doit on implant� de la defense a un ennemi?
 *  Lui donner de la def en plus c'est comme lui donner de la vie en plus, cela va juste rallonger les combats
 *  pas sur que la def soit un ajout pertinent 
 */


package model.character;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;
import resources.additionalClass.UsefulMethods;

public abstract class GameCharacter extends Movable{

	private static Hero Hero;
	private IntegerProperty hp; 	
	private IntegerProperty def;	// comportement de la defense avoir avec Dorian atk*def/100 ??
	private IntegerProperty safeHP;
	private IntegerProperty safeDEF;

	//Crée un deplaçable ayant les propriétées voulue. Déclenche une erreur si l'une des propriétés est invalid (négatif ou null)
	
	
	
	public final static Hero getHero() {
		return Hero;
	}
	
	public final static void setHero(Hero hero) {
		if(hero != null) {
			Hero = hero;
		}
	}
	
	
	protected static final GameCharacter getGameCharacter() {
		return Hero;
	}
	
	
	public GameCharacter(GameMap map, int hp, int def,int startRow,int startColumn,int cycle,double coefficient,int defaultImage) {
		super(map,cycle,startRow,startColumn,coefficient,defaultImage);
		if( map == null || hp <= 0 || def < 0) {
			throw new IllegalArgumentException("ARGUMENT INVALID on GAMECHARACTER ");
		}
		else {
			this.setMap(map,startRow,startColumn);

			initialiseProperties(hp,def);
		}
	}
	
	
	private  void initialiseProperties(int hp,int def) {
		this.hp = new SimpleIntegerProperty(hp);
		this.def = new SimpleIntegerProperty(def);
		this.safeHP = UsefulMethods.copyIntegerProperty(this.hp);
		this.safeDEF = UsefulMethods.copyIntegerProperty(this.def);
	}
	
	public final IntegerProperty getHp() {
		return this.safeHP;
	}
	
	
	public final IntegerProperty getDef() {
		return this.safeDEF;
	}

	public void getDmgAttacked(Attack atc) {
		this.getDmg(atc.getDamage());
	}
	
	public boolean isAlive() {
		return this.hp.get() > 0;
	}
	
	protected void setMap(GameMap newMap,int row,int column) {

		boolean put = newMap.addCharacter(this, row, column);

		if(!put) {
			throw new IllegalArgumentException("MAP UNDEFINED");
		}
		else
			this.setMap(newMap);
		
	}

	protected void removeCharacter() {
		this.getMyMap().delCharacter(this, getRow(), getColumn());
	}

	public abstract void launchAttack(Movement move);

	private void getDmg(int dmg) {
		
		if (dmg < 0) {
			throw new IllegalArgumentException("DMG on getDMG is < 0");
		}
		dmg = (dmg*100/(100+this.def.get()));

		if(dmg > this.hp.get()) {
			this.hp.set(0);
		}
		else {
			this.hp.set(this.hp.get() - dmg);;
		}
	}
	

	
}
