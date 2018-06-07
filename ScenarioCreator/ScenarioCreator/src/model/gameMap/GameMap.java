package model.gameMap;


import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import model.gameMap.cell.Cell;

public class GameMap {
	
	private Cell[] cells ;

	
	public GameMap(String ... mapPath) {
			
			Integer[][] values = MapReader.readAndConvertMapFile(mapPath);
			initialiseCells(values);
		
		}
	
	
	//Initialise l'ensemble des celluls de la map
	private void initialiseCells(Integer[][] values) {
		this.cells = new Cell[Statics.MAPFULLSIZE];
		Integer[][] backValues = Statics.getBackgroundValues(values);
		Integer[] itemValue = Statics.getItemValue(values);
		
		for(int i = 0 ; i < Statics.MAPFULLSIZE ;i++) {
			createCell(i,backValues[i],itemValue[i]);
		}
		
	}
	
	private void createCell(Integer cellId,Integer[] backValue,int itemValue) {
		cells[cellId] = new Cell(backValue,itemValue);			
	}
	public Integer[] getLayerForCell(int cellId) {
		return this.cells[cellId].getCellBackgroundLayer();
	}	




}
