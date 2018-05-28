package model.gameMap.additional;
/*
A discuter
*/
/*
package model.gameMap;

import java.util.ArrayList;


import model.gameMap.Movement;

public class GameMapGraph {
	
	private int[][] matrice;
	private GameMap myMap ;
	public final static int IMPOSSIBLE = 0;
	
	public GameMapGraph(GameMap map) {
		this.myMap = map;
		this.matrice = new int[MapReader.MAPLENGTH*MapReader.MAPLENGTH][Move.values().length];
		for(int i = 0 ; i < MapReader.MAPLENGTH;i++) {
			for(int j = 0; j < MapReader.MAPLENGTH ;j++) {
				//this.updateGraphe(MapReader.convertToCellId(i,j));
			}
		}
	}
	
	
	public void updateGraphe(int cellId) {
		for(int k = 0 ; k < Movement.values().length;k++){
			this.matrice[cellId] = this.myMap.peutTraverserA(cellId,Movement.values()[k]);
		}
	}
	
	public void listMovementPossible(int cellId) {
		ArrayList<Integer> movementPossible = new ArrayList<Integer>();
		int possibleCellId = cellId;
		for(int j = 0 ; j < this.matrice[cellId].length;j++) {
			if(this.matrice[cellId][j] != IMPOSSIBLE) {
				possibleCellId += Movement.values()[j].getVerticalIncrement() * GameMap.MAPLENGTH;
				possibleCellId += Movement.values()[j].getHorizontalIncrement() ;
				movementPossible.add(possibleCellId);
			}
		}
	}
	
}
*/