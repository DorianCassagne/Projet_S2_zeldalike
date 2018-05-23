package model.character;

import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Hero extends GameCharacter{
	private final static int DEFAULTSPEED = 10;
	private final static int DEFAULTHP = 300;
	private final static int DEFAULTDEF = 200;
	private final static int DEFAULTCYCLE = 20;
	
	public static final char MOVEUP = 'u';
	public static final char MOVEDOWN = 'd';
	public static final char MOVELEFT = 'l';
	public static final char MOVERIGHT = 'r';
	public static final char STAY = ' ';
	
	private char nextMove;
	
	private final static int[] IMAGES = {8,11,9,10};
	
	public Hero(GameMap map,int startRow,int startColumn) {
		super(map, DEFAULTSPEED, Movable.HEROTYPE, DEFAULTHP, DEFAULTDEF,startRow,startColumn,DEFAULTCYCLE);
		this.nextMove = STAY;
	}

	public Move turn() {
		this.oneTurn();
		Move move = null;
		if(this.nextMove != STAY && this.canAct()) {
			move = interpreteMove();
		}
		return move;
	}
	
	public void setNextMove(char nextMove) {
		 this.nextMove = nextMove;
	}
	
	@Override
	public int getDefaultImage() {
		return IMAGES[Movable.DEFAULTIMAGEINDEX];
	}
	
	private Move interpreteMove() {
		Move myMove;
		int reachRow = this.getRow();
		int reachColumn = this.getColumn(); 
		int imageIndex = TOPIMAGEINDEX;
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
		default : 
			break;
		}
		
		this.nextMove = STAY;
		boolean changedCell = this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),reachRow,reachColumn);
		if(changedCell) {
			myMove = new Move(GameMap.convertToCellId(reachRow, reachColumn),this.getSpeed(), IMAGES[imageIndex]);
		}
		else
			myMove = null;
		return myMove;

	}
	

}
