package view;


import controller.MapLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CellView extends StackPane{
	public CellView(Integer[] layers) {
		this.updateCell(layers);
	}
	
	public void updateCell(Integer[] layers) {
		if(layers != null ) {
			this.getChildren().clear();
			for(int layer : layers) {
				Image layerImage = MapLoader.TEXTURE.getImg(layer);
				ImageView viewLayer = new ImageView(layerImage);
				viewLayer.setScaleX(MapLoader.TILESCALE);
				viewLayer.setScaleY(MapLoader.TILESCALE);
				this.getChildren().add(viewLayer);
				
			}
		}
		

	}
	
	
	
}
