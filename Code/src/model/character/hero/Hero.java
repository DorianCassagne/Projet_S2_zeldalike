package model.character.hero;


import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
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
	
	public Hero(GameMap map,int startRow,int startColumn) {
		super(map,startRow,startColumn);
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

	


}
