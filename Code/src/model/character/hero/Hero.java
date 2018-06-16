package model.character.hero;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.GameStatus;
import model.character.GameCharacter;
import model.character.item.mapChange.MapChangerEnum;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Hero extends GameHero{

	private char nextMove;
	private Movement direction;
	private IntegerProperty mapProperty;
	
	public static final char MOVEUP = 'u';
	public static final char MOVEDOWN = 'd';
	public static final char MOVELEFT = 'l';
	public static final char MOVERIGHT = 'r';
	public static final char STAY = ' ';
	public static final char ATTACKUP = '8';
	public static final char ATTACKRIGHT = '6' ;
	public static final char ATTACKDOWN = '2' ;
	public static final char ATTACKLEFT = '4' ;
	public static final char CHANGEATTACK = 'c';
	public static final char TALK = 'x';
	
	
	

	
	public Hero(GameMap map,int startRow,int startColumn,GameStatus gameStatus) {
		super(map,startRow,startColumn,gameStatus);
		GameCharacter.setHero(this);
		this.nextMove = STAY;
		this.direction = Movement.TOP;
		this.mapProperty = new SimpleIntegerProperty();
	}
	
	public void setNextMove(char nextMove) {
		this.nextMove = nextMove;
	}

	/*
	 * Movements
	 */

	private Move interpreteMove() {
		Move myMove = null;

		int reachRow = this.getRow();
		int reachColumn = this.getColumn(); 
		boolean attackLaunch = false;
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
		case ATTACKUP :
			attackLaunch = true;
			this.direction = Movement.TOP;
			break;
		case ATTACKDOWN : 
			attackLaunch = true;
			this.direction = Movement.BOTTOM;
			break;
		case ATTACKLEFT :
			attackLaunch = true;
			this.direction = Movement.LEFT;
			break;
		case ATTACKRIGHT :
			attackLaunch = true;
			this.direction = Movement.RIGHT;
			break;
		case CHANGEATTACK : 
			this.setWait(1);
			//this.changeAttack();
			break;
		case TALK : 
			this.talk();
		default : 
			break;
		}
		updateReference();
		
		if(attackLaunch) {
			launchAttack(this.direction);
		}
		else {
			myMove = movePerso(reachRow,reachColumn);
		}
		
		return myMove;

	}
	
	private void talk() {
		int row = this.getRow() + this.direction.getVerticalIncrement();
		int column = this.getColumn() + this.direction.getHorizontalIncrement();
		this.getMyMap().talkTo(row,column);
	}
	
	private void updateReference() {
		this.nextMove = STAY;
		this.setImage(this.direction);
	}
	
		
	
	//TODO à vérifier
	private Move movePerso(int reachRow,int reachColumn) {
		boolean changedCell;
		Move myMove = null;
		
		changedCell = this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),reachRow,reachColumn);
		if(changedCell) {
			myMove = new Move(Statics.convertToCellId(reachRow, reachColumn),this.getMoveCycle());
		}
		
		return myMove;
		
	}
	
	public void changeMap(GameMap newMap,int newRow,int newColumn) {
		this.setMap(newMap,newRow,newColumn);
	}

	public final IntegerProperty mapChangerProperty() {
		return this.mapProperty;
	}
	
	
	
	@Override
	public Move act() {
		return interpreteMove();
	}
	
	public void setMapChange(MapChangerEnum mapChanger) {
		if(mapChanger == null)
			throw new IllegalArgumentException("MAPCHANGER MUST NOT BE NULL");
		this.mapProperty.set(mapChanger.getMapIndex());
	}

	@Override
	protected void removeCharacter() {
		// TODO Auto-generated method stub
		
	}

	


	


}
