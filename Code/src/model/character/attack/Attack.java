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
		int cellId = GameMap.convertToCellId(row, column);
		int endCellId;
		if(this.getMyMap().playAttack(this,row,column) != NOTPLAYED) {
			endCellId = cellId;
			this.setCellId(row, column);
		}else {
			endCellId = GameMap.convertToCellId(getRow(), getColumn());
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
