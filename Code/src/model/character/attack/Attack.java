package model.character.attack;
import model.character.GameCharacter;
import model.character.Movable;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Movement;

public abstract class Attack extends Movable {
	public final static int NOTPLAYED = 0 ;
	
	private Movement direction;
	private int damage;
	private int cellPerTurn;
	
	
	public Attack(GameMap map, int cycle, int row, int column,Movement direction,int damage,int cellPerTurn,double coefficient,int defaultImage) {
		
		super(map,cycle, row, column,coefficient,defaultImage);
		if(direction == null || damage <= 0 || cellPerTurn <= 0) {
			throw new IllegalArgumentException("PROBLEM IN ATTACK");
		}
		else {
			this.direction = direction;
			this.setImage(direction);
			this.damage = damage;
			this.cellPerTurn = cellPerTurn;
			row += direction.getVerticalIncrement() ;
			column += direction.getHorizontalIncrement();
			this.getMyMap().addAttack(this, row, column);
			this.establishMove();
		}

	}
	
	protected void setCastTime(int castTime) {
		
	}
	
	protected void setMaxCell(int maxCell) {
		
	}
	

	public final int establishMove() {
		
		int row = this.getRow() + this.cellPerTurn * this.direction.getVerticalIncrement();
		int column = this.getColumn() + this.cellPerTurn * this.direction.getHorizontalIncrement() ;
		byte playAttack = this.getMyMap().playAttack(this, row, column);
		if(!handlePlay(playAttack)) {
			row = this.getRow();
			column = this.getColumn();
			this.removeCharacter();
		}
		this.setCellId(row, column);
		return Statics.convertToCellId(row, column);

	}
		
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public boolean isAlive() {
		return true;
	}
	
	@Override
	protected void removeCharacter() {
		this.getMyMap().delAttack(this);
	}

	public abstract boolean handlePlay(byte attackResult);
	public abstract void attack(GameCharacter gameCharacter) ;
	
	
}
