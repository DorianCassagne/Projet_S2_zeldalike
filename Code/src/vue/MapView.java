package vue;

import java.util.function.Function;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.TilePane;
import model.gameMap.additional.MapReader;

public class MapView {
	
	private CellView[] cellsItemAndBackground;
	private Function<Integer,Integer[]> backgroundSource;
	private TilePane mapContainer;
	
	public MapView(Function<Integer,Integer[]> backSource,TilePane mapContainer,IntegerProperty changeCell) {
		if(backSource != null && mapContainer != null ) {
			
			this.mapContainer = mapContainer;
			this.backgroundSource = backSource;
			this.cellsItemAndBackground = new CellView[MapReader.MAPLENGTH * MapReader.MAPLENGTH];
			changeCell.addListener(
					(obs,oldValue,newValue)->changeCell(newValue.intValue(),oldValue.intValue())
			);
		}
		
		else {
			throw new IllegalArgumentException("BACKGROUNDSOURCE OR MAPCONTAINER IS NOT DEFINED");
		}
	}
	
	
	private void changeCell(int cellId,int oldValue) {
		if (cellId==-1)
			cellId=oldValue;
		Integer[] changeCell = this.backgroundSource.apply(cellId);
		this.cellsItemAndBackground[cellId].updateCell(changeCell);
	}
	
	public void initialise() {		
		this.mapContainer.getChildren().clear();
		for(int cellId = 0 ; cellId < this.cellsItemAndBackground.length ;cellId++) {
			Integer[] layers = this.backgroundSource.apply(cellId);
			CellView current = new CellView(layers);
			this.cellsItemAndBackground[cellId] = current;
			this.mapContainer.getChildren().add(current);
		
		}
		
	}
	
	
	public void updateCell(int cellId,Integer[] newBackground) {
		this.cellsItemAndBackground[cellId].updateCell(newBackground);
	}

}
