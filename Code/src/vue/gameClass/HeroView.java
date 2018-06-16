package vue.gameClass;


import controler.conversion.ConversionAndStatics;

import controler.gameLoop.ControlerEncoder;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import model.character.hero.CopyOfHeroStats;
import model.gameMap.additional.MapReader;

public class HeroView extends MovableView{
	public final static int ATTACKINDEX = 0;
	public final static int DEFENSEINDEX = 1;
	
	
	
	private double shownRow;
	private double shownColumn;
	private AnchorPane myAnchorPane;
	private Tooltip attackTooltip;
	private Tooltip defenseTooltip;
	
	public HeroView(int cellId,IntegerProperty imageValue,ControlerEncoder data) {
		super(cellId,imageValue);
		this.initShownDimension(data.getCharacterAnchorPane());
		
		this.scrollX(this.getLayoutX());
		this.scrollY(this.getLayoutY());
		
		
		this.attackTooltip = new Tooltip();
		this.defenseTooltip = new Tooltip();
		
		relateEveryThing(data);
		
	}
	
	
	private void initShownDimension(AnchorPane characterAnchorPane) {
		this.shownRow = characterAnchorPane.getScene().getHeight() ;
		this.shownColumn = characterAnchorPane.getScene().getWidth() ;
		characterAnchorPane.getScene().heightProperty().addListener((obs,oldValue,newValue)->{this.shownRow = newValue.intValue();this.scrollY(this.getY());});
		characterAnchorPane.getScene().widthProperty().addListener((obs,oldValue,newValue)->{this.shownColumn = newValue.intValue();this.scrollX(this.getX());});
		this.myAnchorPane = characterAnchorPane;

	}
	
	
	private void relateEveryThing(ControlerEncoder encoder) {
		
		this.layoutXProperty().addListener((obs,oldValue,newValue)->{
			this.scrollX(newValue.doubleValue());
		});
		this.layoutYProperty().addListener((obs,oldValue,newValue)->this.scrollY(newValue.doubleValue()));	
		
		CopyOfHeroStats heroStats = encoder.getMyGame().getHeroStats();
		
		this.linkHP(heroStats.getHPBinding(),heroStats.getMaxHP(),encoder.getHPLabel(),encoder.getHPProgressBar());
		this.linkMP(heroStats.getMPBinding(),heroStats.getMaxMP(),encoder.getMPLabel(),encoder.getMPProgressBar());
		
		
		ViewUsefulMethods.linkImage(heroStats.getAtkBinding(),heroStats.getAtkImageBinding(),encoder.getImageViewAt(ATTACKINDEX),encoder.getButtonAt(ATTACKINDEX),"Points d'attaque : ",this.attackTooltip);
		ViewUsefulMethods.linkImage(heroStats.getDefBinding(),heroStats.getDefImage(),encoder.getImageViewAt(DEFENSEINDEX),encoder.getButtonAt(DEFENSEINDEX),"Points de défense : ",this.defenseTooltip);
	
	}
		
		
	private void linkHP(IntegerBinding HPproperty,IntegerBinding maxHP ,Label HPlabel,ProgressBar HPprogress) {
		ViewUsefulMethods.linkPropertyToLabelAndProgress(HPproperty,maxHP,HPlabel,HPprogress);
	}
	
	private void linkMP(IntegerBinding MPProperty,IntegerBinding maxMP,Label MPLabel,ProgressBar MPProgress) {
		ViewUsefulMethods.linkPropertyToLabelAndProgress(MPProperty,maxMP,MPLabel,MPProgress);	
	}
	
	
	private void scrollX(double newValue) {
		double diff = newValue - this.shownColumn/2;
		boolean isNotAtLimit = (newValue - (ConversionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH) < -this.shownColumn/2);
		
		if(diff < 0) {
			diff = 0;
		}
		else if(!isNotAtLimit) {
			diff = ConversionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH - this.shownColumn;
		}
		
		this.myAnchorPane.setTranslateX(-diff);

		
	}
	
	private void scrollY(double newValue) {
		double diff = newValue - this.shownRow/2;
		boolean isNotAtLimit = (newValue - (ConversionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH) < -this.shownRow/2);
		
		if(diff < 0)
			diff = 0;
		
		else if(!isNotAtLimit)
			diff = ConversionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH - this.shownRow ;
		
		this.myAnchorPane.setLayoutY(-diff);

	}
}
