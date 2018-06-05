package model.character.attack;
import model.character.GameCharacter;
import model.character.Movable;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.cell.Cell;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public abstract class Attack extends Movable {
	public final static int NOTPLAYED = 0 ;
	
	private Movement direction;
	private int damage;
	private int cellPerTurn;
	private int maxDistance;
	private boolean isAlive;
	private boolean lastTurn;
	
	
	public Attack(GameMap map, int cycle, int row, int column,Movement direction,int damage,int cellPerTurn,double coefficient,int defaultImage,int maxDistance) {
		
		super(map,cycle, row, column,coefficient,defaultImage);
		if(direction == null || damage < 0 || cellPerTurn <= 0 || maxDistance < 0) {
			throw new IllegalArgumentException("PROBLEM IN ATTACK");
		}
		else {
			this.lastTurn = false;
			this.direction = direction;
			this.setImage(direction);
			this.damage = damage;
			this.cellPerTurn = cellPerTurn;
			this.maxDistance = maxDistance;
			this.isAlive = true;
			row += direction.getVerticalIncrement() ;
			column += direction.getHorizontalIncrement();
			this.getMyMap().addAttack(this, row, column);
			this.establishMove();
		}

	}	

	
	@Override
	public final Move act() {
		int endCellId = this.establishMove();
		return new Move(endCellId, this.getMoveCycle());
	}

	
	
	private final int establishMove() {
		int row = this.getRow();
		int column = this.getColumn();
		int index = 0;
		while(index < this.cellPerTurn && this.isAlive()) {
			
			row +=  this.direction.getVerticalIncrement();
			column +=  this.direction.getHorizontalIncrement() ;
			byte playAttack = this.getMyMap().playAttack(this, row, column);
			this.maxDistance--;
			
			if(!handleMove(playAttack)) {
				row = this.getRow();
				column = this.getColumn();
				this.isAlive = false;
			}
			else if(this.lastTurn) {
				this.lastTurn = false;
				this.isAlive = false;
			}
			this.setCellId(row, column);
			index++;
		}
		
		return Statics.convertToCellId(row, column);

	}
		
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public boolean isAlive() {
		System.out.println("The last turn or alive is : " + ((this.isAlive && maxDistance >= 0) || this.lastTurn));
		return (this.isAlive && maxDistance >= 0) || this.lastTurn;
	}
	
	@Override
	protected void removeCharacter() {
		this.getMyMap().delAttack(this);
		this.isAlive = false;
		this.lastTurn = false;
	}

	public Movement getDirection() {
		return this.direction;
	}
	
	protected boolean handleMove(byte attackResult) {
		return attackResult % Cell.NOTWALKABLE != 0;
	}
	
	public final void attack(GameCharacter gameCharacter) {
		if(gameCharacter != null && this.isAlive()) {
			this.lastTurn = this.handlePlay(gameCharacter);
		}
	}
	
	protected boolean handlePlay(GameCharacter gameCharacter) {
		this.establishAttack(gameCharacter);
		return true;
	}
	
	
	protected abstract void establishAttack(GameCharacter gameCharacter);
	
	
}
