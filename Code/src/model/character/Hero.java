package model.character;

import model.character.attack.Attack;
import model.character.attack.AttackTest;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Hero extends GameCharacter{
	private final static int DEFAULTHP = 300;
	private final static int DEFAULTDEF = 200;
	private final static int DEFAULTCYCLE = 2;
	private final static double DEFAULTCOEFFICIENT = 1.2;
	public final static int DEFAULTIMAGE = 8;
	public static final char MOVEUP = 'u';
	public static final char MOVEDOWN = 'd';
	public static final char MOVELEFT = 'l';
	public static final char MOVERIGHT = 'r';
	public static final char STAY = ' ';
	public static final char ATTACK = 'a';
	
	private char nextMove;
	
	private int lastImageIndex;
	
	public Hero(GameMap map,int startRow,int startColumn) {
		super(map, GameCharacter.HEROTYPE, DEFAULTHP, DEFAULTDEF,startRow,startColumn,DEFAULTCYCLE,DEFAULTCOEFFICIENT);
		//map.addHero(this);
		this.nextMove = STAY;
	}
	
	public void setNextMove(char nextMove) {
		 this.nextMove = nextMove;
	}
	
	@Override
	public int getDefaultImage() {
		return DEFAULTIMAGE  ;
	}
	
	private Move interpreteMove() {
		Move myMove;
		int reachRow = this.getRow();
		int reachColumn = this.getColumn(); 
		int imageIndex = TOPIMAGEINDEX;
		boolean changedCell = true;
		switch(this.nextMove) {
		case MOVEUP:
			reachRow--;
			break;
		case MOVEDOWN:
			reachRow++;
			imageIndex = BOTTOMIMAGEINDEX;
			break;
		case MOVELEFT:
			reachColumn--;
			imageIndex = LEFTIMAGEINDEX;
			break;
		case MOVERIGHT:
			reachColumn++;
			imageIndex = RIGHTIMAGEINDEX;
			break;
		case ATTACK :
			changedCell = false;
			Movement movement = Movement.values()[lastImageIndex];
			Attack attack = new AttackTest(getMyMap(), reachRow, reachColumn,movement, 80);
			break;
		default : 
			break;
		}
		
		this.nextMove = STAY;
		
		 changedCell = changedCell && this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),reachRow,reachColumn);
		if(changedCell) {
			this.lastImageIndex = imageIndex;
			myMove = new Move(GameMap.convertToCellId(reachRow, reachColumn),this.getMoveCycle(), DEFAULTIMAGE + imageIndex);
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
