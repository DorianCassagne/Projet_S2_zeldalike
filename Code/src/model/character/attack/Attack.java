package model.character.attack;

import model.character.GameCharacter;
import model.character.Movable;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public abstract class Attack extends Movable {
	
	private Movement direction;
	private int damage;
	private int cellPerTurn;
	
	public Attack(GameMap map, int cycle, int row, int column,Movement direction,int damage,int cellPerTurn) {
		
		super(map, cycle, row, column);
		this.direction = direction;
		this.damage = damage;
		this.cellPerTurn = cellPerTurn;
		this.getMyMap().addAttack(this, row, column);

	}
	

	public final int establishMove() {
		int row = this.getRow() + this.cellPerTurn * this.direction.getVerticalIncrement();
		int column = this.getColumn() + this.cellPerTurn * this.direction.getHorizontalIncrement() ;
		this.setCellId(row, column);
		return GameMap.convertToCellId(row, column);
	}
	
	public  void target(GameCharacter gameCharacter) {
		gameCharacter.attaquer(this);
	}
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	
}
