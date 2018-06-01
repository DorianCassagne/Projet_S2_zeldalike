package model.gameMap.cell;

import model.gameMap.GameMap;
import model.gameMap.additional.Statics;

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
			if(value >= Statics.STARTNONWALKABLEINDEX && value < Statics.STARTNONWALKABLEINDEX + Statics.CATEGORYLENGTH ) 
				this.isWalkable = this.isWalkable && false ;
			else if(value >= Statics.STARTWALKABLEINDEX && value < Statics.STARTWALKABLEINDEX + Statics.CATEGORYLENGTH)
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