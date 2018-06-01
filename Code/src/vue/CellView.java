package vue;

import controler.Controleur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CellView extends StackPane{
	
	public CellView(Integer[] layers) {
		this.updateCell(layers);
	}
	
	public void updateCell(Integer[] layers) {
		
		if(layers != null) {
			this.getChildren().clear();
			
			for(int layer : layers) {
				System.out.println("Layer : "+ layer);
				Image layerImage = Controleur.TEXTURE.getImg(layer);
				ImageView viewLayer = new ImageView(layerImage);
				this.getChildren().add(viewLayer);
			}
			
		}
	}
	
	
	
}
