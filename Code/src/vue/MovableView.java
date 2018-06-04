package vue;

import controler.Controleur;

import controler.ConvertionAndStatics;
import javafx.beans.property.IntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MovableView extends ImageView{
	
	private double avancementX;
	private double avancementY;
	private int progress;
	
	public MovableView(int cellId,IntegerProperty imageId) {
		this.setPosition(cellId);
		this.progress = 0;
		this.changeImage(imageId.get());
		imageId.addListener((obs,oldValue,newValue)->changeImage(newValue.intValue()));
	}
	
	
	private void changeImage(int newValue) {
		Image image = Controleur.TEXTURE.getImg(newValue);
		this.setImage(image);
	}
	
	public void moveTo(int cellId,int speed) {
		int[] position = ConvertionAndStatics.convertToViewSize(cellId);
		double row = position[ConvertionAndStatics.ROWINDEX] - this.getLayoutY();
		double column = position[ConvertionAndStatics.COLUMNINDEX] - this.getLayoutX();
		this.avancementX = column/speed;
		this.avancementY = row/speed;
		this.progress = speed;
	
	}
	
	
	public void tick() {
		
		if( this.progress > 0) {
			this.setLayoutY(this.avancementY + this.getLayoutY());
			this.setLayoutX(this.avancementX + this.getLayoutX());
			this.progress--;
		}
		
	}
	
		
	private void setPosition(int cellId) {
		
		int[] position = ConvertionAndStatics.convertToViewSize(cellId);
		this.setLayoutX(position[ConvertionAndStatics.COLUMNINDEX]);
		this.setLayoutY(position[ConvertionAndStatics.ROWINDEX]);
	
	}
}
