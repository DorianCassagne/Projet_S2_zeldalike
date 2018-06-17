package model.character.enemy.boss;
/*
 * Ennemi qui possede deux etats differents associes a des couleurs
 * avant de lancer une attaque, elle s'immobilise change de couleur et lance une attaque dependante de son etat
 * change d etat aleatoirement  
 */
import model.character.enemy.normal.BlueFairy;
import model.gameMap.GameMap;
import model.gameMap.move.Move;

public class Fairy extends BlueFairy {
	private final static int CYCLE=30;
	private final static int IMG=32;
	private final static int DEF=30;
	private final static int DMG=30;
	private final static int HP=30;
	private final static int SCORE = 600;

	public Fairy(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn, CYCLE, IMG, HP, DEF, DMG,SCORE);
	}

	@Override
	protected Move act() {
		
		return null;
	}
	

}
