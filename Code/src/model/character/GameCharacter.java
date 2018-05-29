/*
 * A discuté, un Gamecharacte peut etre un ennemi, doit on implant� de la defense a un ennemi?
 *  Lui donner de la def en plus c'est comme lui donner de la vie en plus, cela va juste rallonger les combats
 *  pas sur que la def soit un ajout pertinent 
 */


package model.character;
import java.util.HashMap;

import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;
import resources.additionalClass.UsefulMethods;

public abstract class GameCharacter extends Movable{

	public final static char HEROTYPE = 'H';
	public final static char ENEMYTYPE = 'E';
	private final static HashMap<Movable,Character> CHARACTERTYPE;
	private static GameCharacter Hero;
	
	private int hp; 	
	private int def;	// comportement de la defense avoir avec Dorian atk*def/100 ??

	//Crée un deplaçable ayant les propriétées voulue. Déclenche une erreur si l'une des propriétés est invalid (négatif ou null)
	
	
	static {
		CHARACTERTYPE = new HashMap<Movable,Character>();
	}
	
	
	public static final char getType(Movable movable) {
		return CHARACTERTYPE.get(movable);
	}
	
	protected static final GameCharacter getGameCharacter() {
		return Hero;
	}
	
	
	public GameCharacter(GameMap map, char type, int hp, int def,int startRow,int startColumn,int cycle,double coefficient) {
		super(map,cycle,startRow,startColumn,coefficient);
		
		if( map == null || hp <= 0 || def < 0) {
			throw new IllegalArgumentException("ARGUMENT INVALID on GAMECHARACTER ");
		}
		else if(!UsefulMethods.isCharInCharList(type, HEROTYPE,ENEMYTYPE))
			throw new IllegalArgumentException("Undefined type");
		else {
			boolean put = this.getMyMap().addCharacter(this, startRow, startColumn);
			if(!put)
				throw new IllegalArgumentException("THE CHARACTER COULD NOT BE CORRECTLY PLACED");
			this.hp = hp;
			this.def = def;
			if(type == HEROTYPE)
				Hero = this;
			CHARACTERTYPE.put(this, type);
		}
	}
	
	
	protected final int getHp() {
		return this.hp;
	}
	
	
	protected final int getDef() {
		return this.def;
	}

	protected final void setDef(int def) {
		if(def < 0 ) {
			throw new IllegalArgumentException("ARGUMENT 'DEF' INVALID on GAMECHARACTER ");
		}
		this.def=def;
	}
	
	public void getDmgAttacked(Attack atc) {
		this.getDmg(atc.getDamage());
	}
	
	//getters setters end
	
	//blesse le personnage exeption si dmg < 0.
	//j'aime pas beaucoup le nom de la methode "getDmg" 
	//fait penser a un getter alors que ce n'est pas un attribut
	
	private void getDmg(int dmg) {
		if (dmg < 0) {
			throw new IllegalArgumentException("DMG on getDMG is < 0");
		}
		dmg = (dmg*100/(100+this.def));
		if(dmg > this.hp) {
			this.hp = 0;
		}
		else {
			this.hp -= dmg;
		}
	}
	
	protected void removeCharacter() {
		this.getMyMap().delCharacter(this, getRow(), getColumn());
	}
	
	
	public boolean isAlive() {
		return this.hp > 0;
	}

	public abstract void launchAttack(Movement move);
}
