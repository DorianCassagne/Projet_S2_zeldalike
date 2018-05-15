package controleur;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import additionalMethods.Tile;
import cell.Background;
import cell.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import mapZelda.Map;
import model.Game;
import personnage.GameCharacter;

public class Controleur implements Initializable{
	
	@FXML private TilePane mapPane;
	@FXML private AnchorPane underMapPane;
	
	private final static int SHOWNROW = 10;
	private final static int HALFSHOWNROW = 5;
	private final static int SHOWNCOLUMN = 16;
	private final static int OLDVALUEINDEX = 0;
	private final static int NEWVALUEINDEX = 1 ;
	private final static double MAINDIFFERENCEINHEIGHTANDWIDTHCOEF = 2.0/(SHOWNCOLUMN*SHOWNROW);
	private final static Image MAINIMAGE = new Image(Game.IMAGESPATH + "pokemonTiles.png");
	private final static String MAPFILENAME = "newMap.csv";
	private final static int MINIMUMSIZE = 16;
	
	private double mainDifference;
	private ImageView[][] tileMapImage;
	private Game game;
	private HashMap<Integer,ImageView> gameCharacterInMap;
	private Integer[] lastCellChangedElements;
	private Timeline timeline;
	private IntegerProperty tileDimension;
	
	
	public Controleur() {
		this.game = new Game(MAPFILENAME);
		this.gameCharacterInMap = new HashMap<Integer,ImageView>();
		this.lastCellChangedElements = new Integer[2];
		this.timeline = new Timeline();
		timeline.setAutoReverse(false);
		this.tileDimension = new SimpleIntegerProperty(MINIMUMSIZE); 
		this.tileDimension.addListener(
				(obs,oldValue,newValue)->relateDimensionToSceneElements(newValue,oldValue)
		);
	}	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mapPane.prefTileWidthProperty().bind(this.tileDimension);
		this.mapPane.prefTileHeightProperty().bind(this.tileDimension);
		this.game.changeProperty().addListener(
				(obs,oldValue,newValue)->executeAction(newValue.intValue())
		);
		initialiseCurrentMap();
		this.game.startGame();

	}
	
	public void startScene(Scene scene) {
		scene.widthProperty().addListener(
				(obs,oldValue,newValue)->{
					this.mainDifference = newValue.doubleValue() * MAINDIFFERENCEINHEIGHTANDWIDTHCOEF;
					this.tileDimension.set(newValue.intValue()/SHOWNCOLUMN);
				}
		
		);
		scene.setOnKeyPressed(
			e -> handleKey(e)
		);

	}
	
	
	private void relateDimensionToSceneElements(Number newValue,Number oldValue) {
		if(oldValue.intValue() < MINIMUMSIZE)
			oldValue = MINIMUMSIZE;
		if(newValue.intValue() >= MINIMUMSIZE) {
			defineWidthAndHeight();
			updateAllGameCharacterViewer(oldValue.intValue());
			updateCellImages();
		}
		else
			this.tileDimension.set(MINIMUMSIZE);
	}

	private void defineWidthAndHeight() {
		int maxRowNumber = this.game.getMapHeight();
		int maxColumnNumber = this.game.getMapWidth();
		this.underMapPane.setMinHeight(convertToViewSize(maxRowNumber));
		this.underMapPane.setMaxHeight(convertToViewSize(maxRowNumber));
		this.underMapPane.setMinWidth(convertToViewSize(maxColumnNumber));
		this.underMapPane.setMaxWidth(convertToViewSize(maxColumnNumber));
	}

	
	public void initialiseCurrentMap() {
		int maxRowNumber = this.game.getMapHeight();
		int maxColumnNumber = this.game.getMapWidth();
		tileMapImage = new ImageView[maxRowNumber][maxColumnNumber];
		mapPane.setPrefRows(maxRowNumber);
		mapPane.setPrefColumns(maxColumnNumber);
		for(int row = 0 ; row < tileMapImage.length;row++) {
			for(int column= 0 ; column < tileMapImage[row].length;column++) {
				this.tileMapImage[row][column]  = new ImageView(MAINIMAGE);
				 addImageToScene(row,column);
			}
		}

	}

	private void addImageToScene(int row,int column) {
		Background cellBackground = this.game.getBackground(row, column);
		int rowIndexInTileSet = cellBackground.getTile()[Map.ROWINDEX] * Tile.TILESDIMENSION ;
		int columnIndexInTileSet = cellBackground.getTile()[Map.COLUMNINDEX] * Tile.TILESDIMENSION;
		Rectangle2D rectangle = new Rectangle2D(rowIndexInTileSet, columnIndexInTileSet, Tile.TILESDIMENSION, Tile.TILESDIMENSION);
		this.tileMapImage[row][column].setViewport(rectangle);
		this.mapPane.getChildren().add(tileMapImage[row][column]);
		updateCellImage(row,column);
	}

	
	private void updateCellImage(int row,int column) {
		this.tileMapImage[row][column].setScaleY(this.tileDimension.get()*1.0/Tile.TILESDIMENSION);
		this.tileMapImage[row][column].setScaleX(this.tileDimension.get()*1.0/Tile.TILESDIMENSION);
	}
	
	private void updateCellImages() {
		int maxRowNumber = this.game.getMapHeight();
		int maxColumnNumber= this.game.getMapWidth();
		for(int row = 0;row < maxRowNumber;row++) {
			for(int column = 0 ; column < maxColumnNumber;column++) {
				updateCellImage(row,column);
			}
		}
	}
	
	private void setViewerPrefDimension(Integer characterIndex,int oldValue) {
		ImageView currentViewer = this.gameCharacterInMap.get(characterIndex);

		Double currentCellY = currentViewer.getLayoutY()/oldValue;
		Double currentCellX = currentViewer.getLayoutX()/oldValue;
				
		double currentValue = this.tileDimension.get();
		double scaleValue = currentValue/GameCharacter.CHARACTERDEFAULTSIZE;
		currentViewer.prefHeight(currentValue);
		currentViewer.prefWidth(currentValue);
		currentViewer.setScaleX(scaleValue);
		currentViewer.setScaleY(scaleValue);
		currentViewer.setLayoutX(this.convertToViewSize(currentCellX.intValue()) + this.mainDifference);
		currentViewer.setLayoutY(this.convertToViewSize(currentCellY.intValue()) + this.mainDifference);

	}
	
	private void updateAllGameCharacterViewer(int oldValue) {
		for(Integer characterKey : this.gameCharacterInMap.keySet()) {
			setViewerPrefDimension(characterKey,oldValue);
		}
	}

	
	
	
	private void handleKey(KeyEvent event) {
		KeyCode code = event.getCode();
		try {
			if(code.equals(KeyCode.UP)) {
				this.game.toTop(Game.HEROKEY);
			}
			else if(code.equals(KeyCode.LEFT)) {
				this.game.toLeft(Game.HEROKEY);
			}
			else if(code.equals(KeyCode.RIGHT)) {
				this.game.toRight(Game.HEROKEY);
			}
			else if(code.equals(KeyCode.DOWN)) {
				this.game.toBottom(Game.HEROKEY);
			}
		}catch(IllegalArgumentException e) {
			
		}
	}
	
	private void executeAction(Integer newValue) {
		if(this.game.getLastCharChangeCategory() == Cell.CHARCHANGE) {
			if(this.lastCellChangedElements[OLDVALUEINDEX] == null)
				this.lastCellChangedElements[OLDVALUEINDEX] = newValue;
			else if(this.lastCellChangedElements[NEWVALUEINDEX] == null) {
				this.lastCellChangedElements[NEWVALUEINDEX] = newValue;
				this.moveCharacter();
			}
		}
	}

	
	
	private void addCharacterToScene(Integer key,int[] startLocation) {
		ImageView viewer = new ImageView(new Image(Game.IMAGESPATH + this.game.characterImageNameProperty(key).get()));
		
		this.game.characterImageNameProperty(key).addListener(
				(obs,oldValue,newValue)->{
					Image image = new Image(Game.IMAGESPATH +  newValue);
					viewer.setImage(image);
				}
		);
		
		this.gameCharacterInMap.put(key, viewer);
		this.underMapPane.getChildren().add(viewer);
		viewer.setLayoutX(convertToViewSize(startLocation[Map.COLUMNINDEX]));
		viewer.setLayoutY(convertToViewSize(startLocation[Map.ROWINDEX]));
		this.setViewerPrefDimension(key,this.tileDimension.get());
		
		
		
		if(key == Game.HEROKEY) {
			viewer.layoutXProperty().addListener(
				(obs,oldValue,newValue)->{
					double x = newValue.doubleValue();
					if(x >= this.convertToViewSize(SHOWNCOLUMN/2) ) {
						if(x <= this.convertToViewSize(this.game.getMapWidth() - SHOWNCOLUMN/2))
							this.underMapPane.setLayoutX(convertToViewSize(SHOWNCOLUMN/2) - newValue.doubleValue() );
					}
				}	
			);	
			
			viewer.layoutYProperty().addListener(
					(obs,oldValue,newValue)->{
						double y = newValue.doubleValue();
						if(y >= this.convertToViewSize(HALFSHOWNROW) ) {
							if(y <= this.convertToViewSize(this.game.getMapHeight() - (HALFSHOWNROW)))
								this.underMapPane.setLayoutY(convertToViewSize(HALFSHOWNROW) - newValue.doubleValue()  );
						}
					}	
				);	

		}
		
		

	}
	
	
	
	
	private void moveCharacter() {
		Integer characterKey = this.game.getLastChangedId();
		int[] newLocation = this.game.convertFromCellId(this.lastCellChangedElements[NEWVALUEINDEX]);
		int[] oldLocation = this.game.convertFromCellId(this.lastCellChangedElements[OLDVALUEINDEX]);
		if(this.game.isFromVoidCell(this.lastCellChangedElements[OLDVALUEINDEX])) {
			this.addCharacterToScene(characterKey,newLocation);
		}
		
		else if(this.game.isFromVoidCell(this.lastCellChangedElements[1])) {
			//TODO
		}
		
		else {
			int movementSpeed = this.game.getWalkTime(characterKey);
			int movementToRight = oldLocation[Map.COLUMNINDEX] - newLocation[Map.COLUMNINDEX] ;
			int movementToBottom = oldLocation[Map.ROWINDEX] - newLocation[Map.ROWINDEX];
			ImageView current = Controleur.this.gameCharacterInMap.get(characterKey);
			DoubleProperty layoutYProperty = current.layoutYProperty();
			DoubleProperty layoutXProperty = current.layoutXProperty();
			Duration rightMovingEndDuration = new Duration(movementSpeed * Math.abs(movementToRight));
			Duration bottomMovingEndDuration = new Duration(rightMovingEndDuration.toMillis() + movementSpeed * Math.abs(movementToBottom));
			System.out.println("Right Moving : "+rightMovingEndDuration.toMillis());
			this.timeline.getKeyFrames().clear();
			this.timeline.getKeyFrames().addAll(
					new KeyFrame(Duration.ZERO, new KeyValue(layoutXProperty,layoutXProperty.get())),
					new KeyFrame(rightMovingEndDuration,new KeyValue(layoutXProperty,layoutXProperty.get() - convertToViewSize(movementToRight))),
					new KeyFrame(rightMovingEndDuration, new KeyValue(layoutYProperty,layoutYProperty.get())),
					new KeyFrame(bottomMovingEndDuration,new KeyValue(layoutYProperty,layoutYProperty.get() - convertToViewSize(movementToBottom)))
			);
			this.timeline.play();
		}
		this.lastCellChangedElements[OLDVALUEINDEX] = this.lastCellChangedElements[NEWVALUEINDEX];
		this.lastCellChangedElements[NEWVALUEINDEX] = null;
	}

	

	private int convertToViewSize(int rowNumber) {
		return rowNumber * this.tileDimension.intValue();
	}

	
	

}
