package model;


public class Map {
	private Case[][] cases ;
	
	public Map(String mapName)  {
		try {
			MapReader map = new MapReader(mapName);
			initialiseCases(map);
		}catch(Exception e) {
			throw new IllegalArgumentException("Invalid Map");
		}
	}
	
	private void initialiseCases(MapReader map) {
		int mapHeight = map.getNbTilesHeight();
		int mapWidth = map.getNbTilesWidth();
		this.cases = new Case[mapHeight][mapWidth];
		for(int i = 0 ; i < mapHeight;i++) {
			for(int j = 0 ; j < mapWidth;j++) {
				this.cases[i][j] = new Case(map.getRepresentation(i, j));	
			} 
		}
	}
	
	public int getMapWidth() {
		return this.cases[0].length;
	}
	
	public int getMapHeight() {
		return this.cases.length;
	}
	
	
	public Case getCase(int row,int column) {
		return this.cases[row][column];
	}
}
