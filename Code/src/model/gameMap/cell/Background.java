package model.gameMap.cell;

import model.gameMap.GameMap;

public class Background {

	private int image;
	private boolean isWalkable;
	

	//Renvoie une IllegalArgumentException, si un des parametres est faux
	public Background(int value) throws IllegalArgumentException { 
		if(value >= GameMap.STARTNONWALKABLEINDEX && value < GameMap.STARTNONWALKABLEINDEX + GameMap.CATEGORYLENGTH ) 
			this.isWalkable = false;
		else if(value >= GameMap.STARTWALKABLEINDEX && value < GameMap.STARTWALKABLEINDEX + GameMap.CATEGORYLENGTH)
			this.isWalkable = true;
		else
			throw new IllegalArgumentException("UNKNOWN BACKGROUND ID" + value);
		this.image = value;
	}	
	
	public int getImage() {
		return image;
	}

	//retourne vrai si le fond est traversable, sinon renvoie faux.
	public boolean isWalkable () {
		return isWalkable;
	}

}