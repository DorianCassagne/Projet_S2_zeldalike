package model.character.attack;

import model.character.GameCharacter;
import model.character.Movable;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public abstract class Attack extends Movable {
	public final static int NOTPLAYED = 0 ;
	
	private Movement direction;
	private int damage;
	private int cellPerTurn;
	
	
	public Attack(GameMap map, int cycle, int row, int column,Movement direction,int damage,int cellPerTurn,int coefficient) {
		
		super(map, cycle, row, column,coefficient);
		if(direction == null || damage <= 0 && cellPerTurn <= 0) {
			throw new IllegalArgumentException("PROBLEM IN ATTACK");
		}
		else {
			this.direction = direction;
			this.damage = damage;
			this.cellPerTurn = cellPerTurn;
			this.getMyMap().addAttack(this, row, column);	
		}

	}
	

	public final int establishMove() {
		int row = this.getRow() + this.cellPerTurn * this.direction.getVerticalIncrement();
		int column = this.getColumn() + this.cellPerTurn * this.direction.getHorizontalIncrement() ;
		int cellId = GameMap.convertToCellId(row, column);
		int endCellId;
		byte playAttack = this.getMyMap().playAttack(this, row, column);
		
		if(this.getMyMap().playAttack(this,row,column) != NOTPLAYED && (playAttack % 3 == 0 || playAttack % 7 == 0) ) {
			endCellId = cellId;
			this.setCellId(row, column);
			this.removeCharacter();
		}else {
			endCellId = GameMap.convertToCellId(row, column);
			this.setCellId(row, column);
		}
		return endCellId;
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
	
	@Override
	public void removeCharacter() {
		this.getMyMap().delAttack(this);
	}
	
}
