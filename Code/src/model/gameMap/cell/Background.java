package model.gameMap.cell;
/*
 * Classe background
 * designe les tiles entre les lignes 300 et 500
 * un background est marchable tant qu'il ne contient pas de gamecharacter 
 * et que son background de base n'est pas un background issu des lignes 400-500
 */

import model.gameMap.additional.Statics;

public class Background {

	private boolean isWalkable;
	private Integer[] backgroundList;

	//Renvoie une IllegalArgumentException, si un des parametres est faux
	public Background(Integer[] values) throws IllegalArgumentException { 
		setWalkable(values);
		this.backgroundList = values;
	}
	
	
	
	private void setWalkable(Integer[] backValues) {
		this.isWalkable = true;
		for(int value : backValues) {
			if(value >= Statics.STARTNONWALKABLEINDEX && value < Statics.STARTNONWALKABLEINDEX + Statics.CATEGORYLENGTH ) 
				this.isWalkable = this.isWalkable && false ;
			else if(value >= Statics.STARTWALKABLEINDEX && value < Statics.STARTWALKABLEINDEX + Statics.CATEGORYLENGTH)
				this.isWalkable = this.isWalkable && true ;
			else {
				if(value != -1 )
					throw new IllegalArgumentException("INVALID BACKGROUND "+ value);
			}
		}
		
	}
	
	public void setToWalkable(int replace) {
		int i = 0;
		while(i < backgroundList.length){
			if(backgroundList[i] >= Statics.STARTNONWALKABLEINDEX)
				backgroundList[i] = replace;
			i++;
		}
		this.isWalkable = true;
	}
		
	
	public Integer[] getBackgroundList() {	
		return this.backgroundList;
	}

	//retourne vrai si le fond est traversable, sinon renvoie faux.
	public boolean isWalkable () {
		return isWalkable;
	}

}