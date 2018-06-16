package model.character.attack;
import model.character.GameCharacter;
import model.character.Movable;
import model.gameMap.GameMap;
import model.gameMap.cell.Cell;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public abstract class Attack extends Movable {
	public final static int NOTPLAYED = 0 ;
	private final static int DYINGTURNAFTERREALDEATH = 22; 
	
	private Movement direction;
	private int damage;
	private int cellPerTurn;
	private int maxDistance;
	private boolean isAlive;
	private int lastTurn;
	
	
	public Attack(GameMap map, int cycle, int row, int column,Movement direction,int damage,int cellPerTurn,double coefficient,int defaultImage,int maxDistance) {
		
		super(map,cycle, row, column,coefficient,defaultImage);
		if(direction == null || damage < 0 || cellPerTurn <= 0 || maxDistance < 0) {
			throw new IllegalArgumentException("PROBLEM IN ATTACK");
		}
		else {
			this.lastTurn = 0;
			this.direction = direction;
			this.setImage(direction);
			this.damage = damage;
			this.cellPerTurn = cellPerTurn;
			this.maxDistance = maxDistance;
			this.isAlive = true;
			initAttack();
		}

	}	

	
	private void initAttack() {
		this.moveAttack();
		this.getMyMap().addAttack(this, this.getRow(), this.getColumn());
		this.launchAttack();

	}
	
	@Override
	public Move act() {
		int endCellId = this.establishMove();
		return new Move(endCellId, this.getMoveCycle());
	}

	
	private void clearLastAttack() {
		int row = this.getRow();
		int column = this.getColumn();
		
		for(int i = 0 ; i < this.cellPerTurn;i++) {
			row -= this.direction.getVerticalIncrement();
			column -= this.direction.getHorizontalIncrement();
			this.getMyMap().clearAttack(row,column,this);
		}
		
	}
	
	private void launchAttack() {
		byte playAttack = this.getMyMap().playAttack(this, this.getRow(), this.getColumn());
		this.maxDistance--;
	
		if(!handleMove(playAttack)) {
			int row = this.getRow() - this.direction.getVerticalIncrement();
			int column = this.getColumn() - this.direction.getHorizontalIncrement();
			this.setCellId(row, column);
			this.isAlive = false;
		}
		
	}
	
	private int establishMove() {

		this.clearLastAttack();
		int index = 0;

		while(index < this.cellPerTurn && this.isAlive()) {
			this.moveAttack();
			this.launchAttack();
			index++;
		}
		
		return this.getCellId();

	}
	
	private void moveAttack() {
		int row = this.getRow() + this.direction.getVerticalIncrement();
		int column = this.getColumn() +  this.direction.getHorizontalIncrement() ;
		this.setCellId(row, column);

	}
		
	
	public int getDamage() {
		return this.damage;
	}

	@Override
	public boolean isAlive() {
		return maxDistance >= 0 && this.isAlive ;
	}
	
	@Override
	protected void removeCharacter() {
		
		if(this.lastTurn > DYINGTURNAFTERREALDEATH)
			this.getMyMap().delAttack(this);
		else
			this.lastTurn++;
	}

	public Movement getDirection() {
		return this.direction;
	}

	
	protected boolean handleMove(byte attackResult) {
		return attackResult % Cell.NOTWALKABLE != 0;
	}
	
	public final void attack(GameCharacter gameCharacter) {
		if(gameCharacter != null && this.isAlive()) {
			if(this.handlePlay(gameCharacter)){
				this.isAlive = false;
			}
		}
	}
	
	protected boolean handlePlay(GameCharacter gameCharacter) {
		this.establishAttack(gameCharacter);
		return true;
	}
	
	
	protected abstract void establishAttack(GameCharacter gameCharacter);
	
	protected final static int getAttaqueValue(int playerAtk,int attackNormalDmg) {
		return playerAtk + attackNormalDmg;
	}
	
}
