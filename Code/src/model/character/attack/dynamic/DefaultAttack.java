package model.character.attack.dynamic;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.item.attack.AttackItemEnum;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

/*
 * Cette classe représente l'attaque par défaut 
 * L'attaque par défaut dépends des points d'attaque du personnage utilisant l'attaque
 * Cette attaque dépends toujours d'un item qui sera responsable de son lancement
 * 
 * Ses résponsabilités : 
 * - Infliger des dégats au caractère l'ayant reçu 
 * 
 */

public class DefaultAttack extends Attack{

	private final static int DEFAULTCYCLE = 20;
	private final static double DEFAULTCOEFFICIENT = 1.6;
	private final static int CELLPERTURN = 1;
	
	/*
	 * Crée un objet de type défaultAttack
	 * 
	 * @param GameMap map : la map sur laquelle l'attaque va prendre action
	 * @param int row,column : Position de départ de l'attaque sur la map
	 * @param AttackItemEnum : L'item utilisée lors de lancement de l'attaque
	 * @param attackPT : Les points d'attaque du personnage l'ayant lancée
	 * 
	 * @throws IllegalArgumentException : En cas de paramètres invalides
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
