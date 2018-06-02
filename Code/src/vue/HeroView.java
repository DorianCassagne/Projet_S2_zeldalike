package vue;


import controler.ConvertionAndStatics;
import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.AnchorPane;
import model.gameMap.additional.MapReader;

public class HeroView extends MovableView{
	
	private double shownRow;
	private double shownColumn;
	private AnchorPane myAnchorPane;
	
	public HeroView(int cellId,IntegerProperty imageId,AnchorPane characterAnchorPane) {
		super(cellId,imageId);
		this.shownRow = characterAnchorPane.getScene().getHeight() ;
		this.shownColumn = characterAnchorPane.getScene().getWidth() ;
		this.myAnchorPane = characterAnchorPane;

		this.scrollX(this.getLayoutX());
		this.scrollY(this.getLayoutY());

		this.layoutXProperty().addListener((obs,oldValue,newValue)->this.scrollX(newValue.doubleValue()));
		this.layoutYProperty().addListener((obs,oldValue,newValue)->this.scrollY(newValue.doubleValue()));	
	}
	
	private void scrollX(double newValue) {
		double diff = newValue- this.shownColumn/2;
		boolean isNotAtLimit = (newValue - (ConvertionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH) < -this.shownColumn/2);
		
		if(diff > 0 && isNotAtLimit) {
			this.myAnchorPane.setTranslateX(-diff);
		}

	}
	
	private void scrollY(double newValue) {
		double diff = newValue - this.shownRow/2;
		boolean isNotAtLimit = (newValue - (ConvertionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH) < -this.shownRow/2);
		
		if(diff > 0 && isNotAtLimit) {
			this.myAnchorPane.setTranslateY(-diff);
		}

	}
}
