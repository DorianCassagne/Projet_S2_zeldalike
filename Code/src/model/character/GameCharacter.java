/*
 * A discuté, un Gamecharacte peut etre un ennemi, doit on implant� de la defense a un ennemi?
 *  Lui donner de la def en plus c'est comme lui donner de la vie en plus, cela va juste rallonger les combats
 *  pas sur que la def soit un ajout pertinent 
 */


package model.character;
import model.gameMap.GameMap;

public abstract class GameCharacter extends Movable{

	private int hp; 	
	private int def;	// comportement de la defense avoir avec Dorian atk*def/100 ??

	//Crée un deplaçable ayant les propriétées voulue. Déclenche une erreur si l'une des propriétés est invalid (négatif ou null)
	
	public GameCharacter(GameMap map,int speed, char type, int hp, int def,int startRow,int startColumn,int cycle) {
		super(map,type,speed,cycle,startRow,startColumn);
		
		if( map == null || hp <= 0 || def < 0) {
			throw new IllegalArgumentException("ARGUMENT INVALID on GAMECHARACTER ");
		}
		else {
			this.getMyMap().addCharacter(this, startRow, startColumn);
			this.hp=hp;
			this.def=def;
		}

	}
	
	
	public int getHp() {
		return this.hp;
	}
	
	public void setHp(int hp) {
		if(hp <= 0 ) {
			throw new IllegalArgumentException("ARGUMENT 'HP' INVALID on GAMECHARACTER ");
		}
		this.hp=hp;
	}
	
	public int getDef() {
		return this.def;
	}
	public void setDef(int def) {
		if(def < 0 ) {
			throw new IllegalArgumentException("ARGUMENT 'DEF' INVALID on GAMECHARACTER ");
		}
		this.def=def;
	}
	
	//getters setters end
	
	//blesse le personnage exeption si dmg < 0.
	//j'aime pas beaucoup le nom de la methode "getDmg" 
	//fait penser a un getter alors que ce n'est pas un attribut
	public void getDmg(int dmg) {
		if (dmg < 0) {
			throw new IllegalArgumentException("DMG on getDMG is < 0");
		}
		if(dmg > this.hp) {
			this.hp = 0;
		}
		else {
			this.hp -= dmg;
		}
	}
	
	

	//peu etre negatif ou positif) a faire plus tard, voir si pertinent ou non
	/*
	Public void changeDef(int def) {

	}
	 */
	

}
