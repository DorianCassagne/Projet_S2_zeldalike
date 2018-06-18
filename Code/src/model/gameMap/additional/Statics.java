package model.gameMap.additional;
/*
 * Classe Statics contenant les constantes relatives aux index des differents "objets" present dans le tileset
 * contient des methodes utiles pour les conversions d'id d'objet, de position relative au la map 
 */
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
	public final static int MAPFULLSIZE = MapReader.MAPLENGTH * MapReader.MAPLENGTH; // taille de la map complete

	
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
	 * Verifie si les parametres en entrees designe une case libre sur la map
	 */
	public static boolean isInMap(int row,int column) {
		return row >= 0 && row < MapReader.MAPLENGTH && column >= 0 && row < MapReader.MAPLENGTH;
	}
	
	public static Integer[] getItemValue(Integer[][] values) {
		return values[ITEMVALUEINDEX - 1];
	}
	
	public static Integer[][] getBackgroundValues(Integer[][] values) {
		Integer[][] backValues = new Integer[Statics.MAPFULLSIZE][values.length - 1];
		
		for(int j = 0 ; j < Statics.MAPFULLSIZE ; j++) {
			for(int i = ITEMVALUEINDEX ; i < values.length ;i++) {
				backValues[j][i - ITEMVALUEINDEX] = values[i][j];
			}
		}
		
		return backValues;
	}

	public static boolean isInMap(int cellId) {
		return cellId >= 0 && cellId < MAPFULLSIZE;
	}

}
