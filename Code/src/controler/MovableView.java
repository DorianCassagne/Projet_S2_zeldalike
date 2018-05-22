package controler;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MovableView extends ImageView{
	private static final int MINSPEEDBYCELL = 2000;
	private TranslateTransition translation;
	
	public MovableView(int cellId,int imageId) {
		this.translation = new TranslateTransition();
		Controleur.setViewPort(this, imageId);
		this.setPosition(cellId);
		this.translation.setAutoReverse(false);
		this.translation.setCycleCount(0);
		this.translation.setDelay(Duration.ZERO);
		this.translation.setNode(this);
	}
	
	
	public void moveTo(int cellId,int imageId,int speed) {
		int[] position = Controleur.convertToViewSize(cellId);
		this.translation.setDuration(Duration.millis(MINSPEEDBYCELL/speed));
		Controleur.setViewPort(this, imageId);
		this.translation.setToX(position[Controleur.COLUMNINDEX]);
		this.translation.play();
		this.translation.setOnFinished(e->{
			this.translation.setToY(position[Controleur.ROWINDEX]);
			this.translation.setOnFinished(event->System.out.println("finished"));
			this.translation.play();
		});
		
	}
	
	private void setPosition(int cellId) {
		int[] position = Controleur.convertToViewSize(cellId);
		this.setLayoutX(position[Controleur.COLUMNINDEX]);
		this.setLayoutY(position[Controleur.ROWINDEX]);
	}
}
