package vue.gameClass;


import controler.conversion.ConversionAndStatics;
import controler.gameLoop.ControlerEncoder;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import model.Game;
import model.character.hero.CopyOfHeroStats;
import model.gameMap.additional.MapReader;

public class HeroView extends MovableView{
	public final static int ATTACKINDEX = 0;
	public final static int ITEMINDEX = 1;
	public final static int DEFENSEINDEX = 2;
	
	
	
	private double shownRow;
	private double shownColumn;
	private AnchorPane myAnchorPane;
	private int maxHP;
	private int maxMP;
	private Tooltip tooltip;
	
	public HeroView(int cellId,IntegerProperty imageValue,ControlerEncoder data) {
		super(cellId,imageValue);
		this.initShownDimension(data.getCharacterAnchorPane());
		
		this.scrollX(this.getLayoutX());
		this.scrollY(this.getLayoutY());
		
		this.tooltip = new Tooltip();
		relateEveryThing(data);
		
	}
	
	
	private void initShownDimension(AnchorPane characterAnchorPane) {
		this.shownRow = characterAnchorPane.getScene().getHeight() ;
		this.shownColumn = characterAnchorPane.getScene().getWidth() ;
		this.myAnchorPane = characterAnchorPane;

	}
	
	
	private void relateEveryThing(ControlerEncoder encoder) {
		this.layoutXProperty().addListener((obs,oldValue,newValue)->this.scrollX(newValue.doubleValue()));
		this.layoutYProperty().addListener((obs,oldValue,newValue)->this.scrollY(newValue.doubleValue()));	
		
		CopyOfHeroStats heroStats = encoder.getMyGame().getHeroStats();
		
		this.linkHP(heroStats.getHPBinding(),encoder.getHPLabel(),encoder.getHPProgressBar());
		this.linkMP(heroStats.getMPBinding(),encoder.getMPLabel(),encoder.getMPProgressBar());
		
		
		ViewUsefulMethods.linkImage(heroStats.getAtkBinding(),heroStats.getAtkImageBinding(),encoder.getImageViewAt(ATTACKINDEX),encoder.getButtonAt(ATTACKINDEX),"Points d'attaque : ",this.tooltip);
		//ViewUsefulMethods.linkImage(heroStats.getDefBinding(), encoder.getDefImageView());
	
	}
		
		
	private void linkHP(IntegerBinding HPproperty,Label HPlabel,ProgressBar HPprogress) {
		this.maxHP = HPproperty.get();
		ViewUsefulMethods.linkPropertyToLabelAndProgress(HPproperty,HPlabel,HPprogress);
	}
	
	private void linkMP(IntegerBinding MPProperty,Label MPLabel,ProgressBar MPProgress) {
		this.maxMP = MPProperty.get();
		ViewUsefulMethods.linkPropertyToLabelAndProgress(MPProperty,MPLabel,MPProgress);
	}
	
	
	
	private void scrollX(double newValue) {
		double diff = newValue- this.shownColumn/2;
		boolean isNotAtLimit = (newValue - (ConversionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH) < -this.shownColumn/2);
		
		if(diff > 0 && isNotAtLimit) {
			this.myAnchorPane.setTranslateX(-diff);
		}
	}
	
	private void scrollY(double newValue) {
		double diff = newValue - this.shownRow/2;
		boolean isNotAtLimit = (newValue - (ConversionAndStatics.TILEDIMENSION * MapReader.MAPLENGTH) < -this.shownRow/2);
		
		if(diff > 0 && isNotAtLimit) {
			this.myAnchorPane.setTranslateY(-diff);
		}

	}
}
