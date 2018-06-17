package model.character.enemy.normal;

import model.character.GameCharacter;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
/*
 * Ennemi qui étend tower
 * attaque la position du joueur et non sa direction
 */
public class IntelligentTower extends Tower{

	public IntelligentTower(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn);
	}
	
	@Override
	protected Move act() {
		int destination = GameCharacter.getHero().getCellId();
		Movement perfectMovement = Movement.getDirectionInto(this.getCellId(), destination);

		this.launchAttack(perfectMovement);
		this.setImage(perfectMovement);
		
		return null;
	}

}
