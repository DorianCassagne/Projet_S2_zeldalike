package model.gameMap.cell;

import model.gameMap.GameMap;

public class Background {

	private boolean isWalkable;
	private int[] backgroundList;

	//Renvoie une IllegalArgumentException, si un des parametres est faux
	public Background(int[] values) throws IllegalArgumentException { 
		setWalkable(values);
		this.backgroundList = values;
	}
	
	
	
	private void setWalkable(int[] backValues) {
		this.isWalkable = true;
		for(int value : backValues) {
			if(value >= GameMap.STARTNONWALKABLEINDEX && value < GameMap.STARTNONWALKABLEINDEX + GameMap.CATEGORYLENGTH ) 
				this.isWalkable = this.isWalkable && false ;
			else if(value >= GameMap.STARTWALKABLEINDEX && value < GameMap.STARTWALKABLEINDEX + GameMap.CATEGORYLENGTH)
				this.isWalkable = this.isWalkable && true ;
			else {
				throw new IllegalArgumentException("INVALID BACKGROUND");
			}
		}
		
	}
		
	
	public int[] getBackgroundList() {	
		return this.backgroundList;
	}

	//retourne vrai si le fond est traversable, sinon renvoie faux.
	public boolean isWalkable () {
		return isWalkable;
	}

}