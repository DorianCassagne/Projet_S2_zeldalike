package model.character.attack.dynamic;

import model.character.attack.Attack;
import model.character.attack.Launcher;
import model.character.item.Item;
import model.character.item.attack.AttackItemEnum;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

/*
 * Cette classe represente un lanceur d'attaque par defaut : 
 * Ce type est destine au hero : Les attaques qui pourra toujours lancer sans se preoccuper de ses points de magie
 * 
 * Responsabilitees : 
 * 	-Genere un type d'attaque par defaut
 *  -Lance l'attaque par defaut
 *
 * Attributs : 
 * lastAttack : la derniere attaque lancee, une deuxieme attaque ne pourra se lancer que si la premiere est morte
 * type : le type d'attaque a lancer
 */

public class DefaultAttackLauncher implements Launcher{
	private final static int MANACONSUME = 0;
	
	private Attack lastAttack;
	private AttackItemEnum type;

	
	/*
	 * Cree un objet DefaultAttackLauncher a partir d'un item
	 * 
	 * @param AttackItemEnum attackType : l'item d'attaque utilisé
	 * 
	 * @throws IllegalArgumentException : Si l'item de l'attaque est null
	 * 
	 */
	
	public DefaultAttackLauncher(AttackItemEnum attackType) {
		if(attackType != null) {
			this.lastAttack = null;
			this.type = attackType;
		}
		else {
			throw new IllegalArgumentException("ATTACKTYPE IS NULL");
		}
	}
	
	
	/*
	 * @see DefaultAttackLauncher(AttackItemEnum attackType)
	 * 
	 * @throws IllegalArgumentException : Si l'identifiant de l'attaque est erronée (n'est pas valide)
	 */
	public DefaultAttackLauncher(int attackItemId) {
		try {
			
			int diff = attackItemId - Item.ATTACKITEMSTARTINDEX;
			this.type = AttackItemEnum.values()[diff];
		
		}catch(IndexOutOfBoundsException e) {
			throw new IllegalArgumentException("ERROR ON ATTACKITEMID");
		}
	}
	
	
	/*
	 * 
	 * @see model.character.attack.Launcher#launch(model.gameMap.GameMap, model.gameMap.move.Movement, int, int, int)
	 */
	@Override
	public int launch(GameMap map,Movement direction,int row,int column,int attackPt) {
		
		if(this.lastAttack == null || !this.lastAttack.isAlive()) {
			this.lastAttack = new DefaultAttack(map,row,column,direction,this.type,attackPt);
		}
		
		return MANACONSUME;
	}
	

	/*
	 * (non-Javadoc)
	 * @see model.character.attack.Launcher#getDamage()
	 */
	@Override
	public int getDamage() {
		return this.type.getDmg();
	}


	@Override
	public int getImage() {
		return this.type.getImage();
	}
	

}
