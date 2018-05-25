package controler;

import javafx.scene.image.ImageView;

public class MovableView extends ImageView{
	
	private double avancementX;
	private double avancementY;
	private int progress;
	
	public MovableView(int cellId,int imageId) {
		this.setImage(Controleur.TEXTURE.getImg(imageId));
		this.setPosition(cellId);
		this.progress = 0;
	}
	
	
	public void moveTo(int cellId,int imageId,int speed) {
		int[] position = Controleur.convertToViewSize(cellId);
		double row = position[Controleur.ROWINDEX] - this.getLayoutY();
		double column = position[Controleur.COLUMNINDEX] - this.getLayoutX();
		this.setImage(Controleur.TEXTURE.getImg(imageId));
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
		int[] position = Controleur.convertToViewSize(cellId);
		this.setLayoutX(position[Controleur.COLUMNINDEX]);
		this.setLayoutY(position[Controleur.ROWINDEX]);
	}
}
