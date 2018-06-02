package model.character;


import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.attack.AttackTest;
import model.character.item.mapChange.MapChangerEnum;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Hero extends GameCharacter{

	private final static int DEFAULTHP = 300;
	private final static int DEFAULTDEF = 200;
	private final static int DEFAULTCYCLE = 20;
	private final static double DEFAULTCOEFFICIENT = 2;
	public final static int DEFAULTIMAGE = 8;
	public static final char MOVEUP = 'u';
	public static final char MOVEDOWN = 'd';
	public static final char MOVELEFT = 'l';
	public static final char MOVERIGHT = 'r';
	public static final char STAY = ' ';
	public static final char ATTACKUP = '8';
	public static final char ATTACKRIGHT = '6' ;
	public static final char ATTACKDOWN = '2' ;
	public static final char ATTACKLEFT = '4' ;

	private char nextMove;
	private Movement direction;
	private int nextAttack;
	private IntegerProperty mapProperty;
	
	public Hero(GameMap map,int startRow,int startColumn) {
		super(map, DEFAULTHP, DEFAULTDEF,startRow,startColumn,DEFAULTCYCLE,DEFAULTCOEFFICIENT,DEFAULTIMAGE);
		GameCharacter.setHero(this);
		this.nextMove = STAY;
		this.direction = Movement.TOP;
		this.nextAttack = -1;
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
		default : 
			break;
		}
		
		updateReference();
		if(attackLaunch) {
			launchAttack();
		}
		else {
			myMove = movePerso(reachRow,reachColumn);
		}
		
		return myMove;

	}
	
	private void updateReference() {
		this.nextMove = STAY;
		this.setImage(this.direction);
	}
	
	private void launchAttack() {
		new AttackTest(this.getMyMap(), this.getRow(),this.getColumn(), this.direction,60);
	}
	
	private Move movePerso(int reachRow,int reachColumn) {
		boolean changedCell;
		Move myMove = null;
		
		changedCell = this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),reachRow,reachColumn);
		if(changedCell) {
			myMove = new Move(Statics.convertToCellId(reachRow, reachColumn),this.getMoveCycle());
		}
		
		return myMove;
		
	}
	

	@Override
	public void launchAttack(Movement move) {
		throw new IllegalArgumentException("He throws Attack automatically");
	}

	public void changeMap(GameMap newMap,int newRow,int newColumn) {
		this.setMap(newMap,newRow,newColumn);
	}


	public void heal(int heal) {
		System.out.println("j'avais "+this.getHp()+"hp");
		
		if(this.getHp().get()+heal > DEFAULTHP) { 
			this.getHp().set(DEFAULTHP);
			System.out.println("j'ai maintenant "+this.getHp()+"hp");
		}else {
			this.getHp().set(this.getHp().get()+heal);
			System.out.println("j'ai maintenant "+this.getHp()+"hp");
		}
	}

	public void setMapChange(MapChangerEnum mapChanger) {
		if(mapChanger == null)
			throw new IllegalArgumentException("MAPCHANGER MUST NOT BE NULL");
		this.mapProperty.set(mapChanger.getMapIndex());
	}
	
	public final IntegerProperty mapChangerProperty() {
		return this.mapProperty;
	}
	
	@Override
	public Move act() {
		return interpreteMove();
	}
	


}
