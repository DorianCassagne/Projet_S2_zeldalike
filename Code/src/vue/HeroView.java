package vue;


import controler.ConvertionAndStatics;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import model.Game;
import model.gameMap.additional.MapReader;

public class HeroView extends MovableView{
	
	private double shownRow;
	private double shownColumn;
	private AnchorPane myAnchorPane;
	
	public HeroView(int cellId,IntegerProperty imageValue,Game game,AnchorPane characterAnchorPane,Label HPLabel,ProgressBar HPProgress) {
		super(cellId,imageValue);
		this.shownRow = characterAnchorPane.getScene().getHeight() ;
		this.shownColumn = characterAnchorPane.getScene().getWidth() ;
		this.myAnchorPane = characterAnchorPane;
		
		this.scrollX(this.getLayoutX());
		this.scrollY(this.getLayoutY());

		relateEveryThing(game,HPLabel,HPProgress);
		
	}
	
	private void relateEveryThing(Game game,Label HPLabel,ProgressBar HPProgress) {
		this.layoutXProperty().addListener((obs,oldValue,newValue)->this.scrollX(newValue.doubleValue()));
		this.layoutYProperty().addListener((obs,oldValue,newValue)->this.scrollY(newValue.doubleValue()));	
		linkProperty(game.heroHPProperty(),HPLabel,HPProgress);
		
	}
	
	private static void linkProperty(IntegerProperty property,Label label,ProgressBar progress) {
		double maxValue = property.get();
		label.setText(property.getValue().toString());
		
		property.addListener(
				(obs,oldValue,newValue)->{
					label.setText(oldValue.toString());
					progress.setProgress(property.get()/maxValue);
				}
		);
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
