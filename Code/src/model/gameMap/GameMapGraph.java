/*
package model.gameMap;

import java.util.ArrayList;

import model.Move.Move;

public class GameMapGraph {
	
	private int[][] matrice;
	private GameMap myMap ;
	public final static int IMPOSSIBLE = 0;
	
	public GameMapGraph(GameMap map) {
		this.myMap = map;
		this.matrice = new int[GameMap.MAPLENGTH*GameMap.MAPLENGTH][Move.values().length];
		for(int i = 0 ; i < GameMap.MAPLENGTH;i++) {
			for(int j = 0; j < GameMap.MAPLENGTH ;j++) {
				this.updateGraphe(GameMap.convertToCellId(i,j));
			}
		}
	}
	
	
	public void updateGraphe(int cellId) {
		for(int k = 0 ; k < Move.values().length;k++){
			this.matrice[cellId] = this.myMap.peutTraverserA(cellId,Move.values()[k]);
		}
	}
	
	public void listMovementPossible(int cellId) {
		ArrayList<Integer> movementPossible = new ArrayList<Integer>();
		int possibleCellId = cellId;
		for(int j = 0 ; j < this.matrice[cellId].length;j++) {
			if(this.matrice[cellId][j] != IMPOSSIBLE) {
				possibleCellId += Move.values()[j].getVerticalIncrement() * GameMap.MAPLENGTH;
				possibleCellId += Move.values()[j].getHorizontalIncrement() ;
				movementPossible.add(possibleCellId);
			}
		}
	}
	
}
*/