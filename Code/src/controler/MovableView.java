package controler;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class MovableView extends ImageView{
	
	private static final int MINSPEEDBYCELL = 2000;
	private TranslateTransition translation;
	
	public MovableView(int cellId,int imageId,AnchorPane characterPane) {
		this.translation = new TranslateTransition();
		this.setImage(Controleur.TEXTURE.getImg(imageId));
		this.setPosition(cellId);
		this.translation.setAutoReverse(false);
		this.translation.setCycleCount(0);
		this.translation.setDelay(Duration.ZERO);
		this.translation.setNode(this);
	}
	
	
	public void moveTo(int cellId,int imageId,int speed) {
		int[] position = Controleur.convertToViewSize(cellId);
		double row = position[Controleur.ROWINDEX] - this.getLayoutY();
		double column = position[Controleur.COLUMNINDEX] - this.getLayoutX();
		this.translation.setDuration(Duration.millis(MINSPEEDBYCELL/speed));
		this.setImage(Controleur.TEXTURE.getImg(imageId));
		this.translation.setToX(column);
		this.translation.setToY(row);
		this.translation.play();
		
	}

	public double getNewLayoutY() {
		return this.getLayoutY() + this.translation.getToY();
	}
	
	public double getNewLayoutX() {
		return this.getLayoutX() + this.translation.getToX();
	}
	
	private void setPosition(int cellId) {
		int[] position = Controleur.convertToViewSize(cellId);
		this.setLayoutX(position[Controleur.COLUMNINDEX]);
		this.setLayoutY(position[Controleur.ROWINDEX]);
	}
}
