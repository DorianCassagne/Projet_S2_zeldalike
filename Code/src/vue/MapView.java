package vue;

import java.util.function.Function;

import controler.Controleur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import model.gameMap.additional.MapReader;

public class MapView {
	private StackPane[] cellsItemAndBackground;
	private Function<Integer,Integer> backgroundSource;
	private TilePane mapContainer;
	
	public MapView(Function<Integer,Integer> backSource,TilePane mapContainer) {
		if(backSource != null && mapContainer != null ) {
			this.mapContainer = mapContainer;
			this.backgroundSource = backSource;
			this.cellsItemAndBackground = new StackPane[MapReader.MAPLENGTH * MapReader.MAPLENGTH];
		}
		else {
			throw new IllegalArgumentException("BACKGROUNDSOURCE OR MAPCONTAINER IS NOT DEFINED");
		}
	}
	
	public void initialise() {			
		for(int cellId = 0 ; cellId < this.cellsItemAndBackground.length ;cellId++) {
			
			this.cellsItemAndBackground[cellId] = new StackPane();
			StackPane current = this.cellsItemAndBackground[cellId];
			int backgroundId = this.backgroundSource.apply(cellId);
			Image image = Controleur.TEXTURE.getImg(backgroundId);
			ImageView background = new ImageView(image);
			current.getChildren().add(background);
			this.mapContainer.getChildren().add(current);
		
		}
		
	}

}
