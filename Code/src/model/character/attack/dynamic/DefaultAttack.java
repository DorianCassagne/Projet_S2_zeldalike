package model.character.attack.dynamic;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.item.attack.AttackItemEnum;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

/*
 * Cette classe represente l'attaque par defaut 
 * L'attaque par defaut depends des points d'attaque du personnage utilisant l'attaque
 * Cette attaque depends toujours d'un item qui sera responsable de son lancement
 * 
 * Ses responsabilites : 
 * - Infliger des degats au caractere l'ayant recu 
 * 
 */

public class DefaultAttack extends Attack{

	private final static int DEFAULTCYCLE = 20;
	private final static double DEFAULTCOEFFICIENT = 1.6; 	// coefficient du temps d'animation
	private final static int CELLPERTURN = 1;				// vitesse par tour
	
	/*
	 * Crée un objet de type defaultAttack
	 * 
	 * @param GameMap map : la map sur laquelle l'attaque va prendre action
	 * @param int row,column : Position de depart de l'attaque sur la map
	 * @param AttackItemEnum : L'item utilisee lors de lancement de l'attaque
	 * @param attackPT : Les points d'attaque du personnage l'ayant lancée
	 * 
	 * @throws IllegalArgumentException : En cas de parametres invalides
	 */
	
	public DefaultAttack(GameMap map, int row, int column, Movement direction,AttackItemEnum attackType,int attackPT) {
		super(map, DEFAULTCYCLE, row, column, direction, Attack.getAttaqueValue(attackPT,attackType.getDmg()), CELLPERTURN, DEFAULTCOEFFICIENT, attackType.getAttackImage(), attackType.getMaxDistance());
	}

	
	/*
	 * @see model.character.attack.Attack#establishAttack(model.character.GameCharacter)
	 */
	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
	}
	
	

	
}
