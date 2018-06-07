package model.gameMap.cell;

public class Background {

	private Integer[] backgroundList;

	//Renvoie une IllegalArgumentException, si un des parametres est faux
	public Background(Integer[] values) throws IllegalArgumentException { 
		this.backgroundList = values;
	}
	
	
			
	
	public Integer[] getBackgroundList() {	
		return this.backgroundList;
	}


}