package model.gameMap.additional;

import model.gameMap.MapEnum;

public class Statics {
	private final static int ITEMVALUEINDEX = 1;
	public final static int LINELENGTH = 8;
	public final static int CATEGORYLENGTH = 100 * LINELENGTH ;
	public final static int STARTCHARINDEX = 0 * LINELENGTH;
	public final static int STARTITEMINDEX = 100 * LINELENGTH;
	public final static int STARTATTACKINDEX = 200 * LINELENGTH;
	public final static int STARTWALKABLEINDEX = 300 * LINELENGTH;
	public final static int STARTNONWALKABLEINDEX = 400 * LINELENGTH;
	public final static int CHANGEPARTWITHCELLID = 9;
	public final static int STEP = 1;
	public final static int MAPFULLSIZE = MapReader.MAPLENGTH * MapReader.MAPLENGTH;

	
	public final static int convertFromRelativeMapIndex(int newMapIndex) {
		return newMapIndex - Integer.MIN_VALUE;
	}
	
	public final static int convertToRelativeMapIndex(int mapIndex) {
		return Integer.MIN_VALUE + mapIndex;
	}
	
	public final static boolean isAMapChange(int checkedValue) {
		return Math.abs(convertFromRelativeMapIndex(checkedValue)) < MapEnum.values().length ;
	}
	
	public final static int convertToCellId(int row,int column) {
		return row * MapReader.MAPLENGTH + column;
	}
	
	public final static int convertToRow(int id) {
		return id/MapReader.MAPLENGTH;
	}
	
	public final static int convertToColomn(int id) {
		return id%MapReader.MAPLENGTH;
	}
	
	/*
	 * V�rifie si les param�tres en entr�s d�signe une case avaible sur la map
	 */
	public static boolean isInMap(int row,int column) {
		return row >= 0 && row < MapReader.MAPLENGTH && column >= 0 && row < MapReader.MAPLENGTH;
	}
	
	public static int[] getItemValue(int[][] values) {
		return values[ITEMVALUEINDEX - 1];
	}
	
	public static int[][] getBackgroundValues(int[][] values) {
		int[][] backValues = new int[Statics.MAPFULLSIZE][values.length - 1];
		
		for(int j = 0 ; j < Statics.MAPFULLSIZE ; j++) {
			for(int i = ITEMVALUEINDEX ; i < values.length ;i++) {
				backValues[j][i - ITEMVALUEINDEX] = values[i][j];
			}
		}
		
		return backValues;
	}

}
