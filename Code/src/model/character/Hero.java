package model.character;


import model.character.attack.AttackTest;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Hero extends GameCharacter{
	
	private final static int DEFAULTHP = 300;
	private final static int DEFAULTDEF = 200;
	private final static int DEFAULTCYCLE = 40;
	private final static double DEFAULTCOEFFICIENT = 2;
	public final static int DEFAULTIMAGE = 8;
	public static final char MOVEUP = 'u';
	public static final char MOVEDOWN = 'd';
	public static final char MOVELEFT = 'l';
	public static final char MOVERIGHT = 'r';
	public static final char STAY = ' ';
	public static final char ATTACK = 'a';
	
	private char nextMove;
	private Movement direction;
	
	public Hero(GameMap map,int startRow,int startColumn) {
		super(map, GameCharacter.HEROTYPE, DEFAULTHP, DEFAULTDEF,startRow,startColumn,DEFAULTCYCLE,DEFAULTCOEFFICIENT,DEFAULTIMAGE);
		this.nextMove = STAY;
		this.direction = Movement.TOP;
	}
	
	public void setNextMove(char nextMove) {
		 this.nextMove = nextMove;
	}
	
	/*
	 * Movements
	 */
	
	private Move interpreteMove() {
		Move myMove;
		
		int reachRow = this.getRow();
		int reachColumn = this.getColumn(); 
		boolean changedCell = true;
		switch(this.nextMove) {
		case MOVEUP:
			reachRow--;
			this.direction = Movement.TOP;
			break;
		case MOVEDOWN:
			reachRow++;
			this.direction = Movement.BOTTOM;
			break;
		case MOVELEFT:
			reachColumn--;
			this.direction = Movement.LEFT;
			break;
		case MOVERIGHT:
			reachColumn++;
			this.direction = Movement.RIGHT;			
			break;
		case ATTACK :
			new AttackTest(getMyMap(), reachRow, reachColumn,this.direction, 80);
			break;
		default : 
			break;
		}
		
		this.nextMove = STAY;
		this.setImage(this.direction);
		 changedCell = changedCell && this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),reachRow,reachColumn);
		if(changedCell) {
			myMove = new Move(Statics.convertToCellId(reachRow, reachColumn),this.getMoveCycle());
		}
		else
			myMove = null;
		return myMove;

	}
	
	@Override
	public void launchAttack(Movement move) {
		throw new IllegalArgumentException("He throws Attack automatically");
	}
	
	@Override
	public Move act() {
		return interpreteMove();
	}

	

}
