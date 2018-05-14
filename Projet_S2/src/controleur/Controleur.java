package controleur;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import cell.Background;
import cell.Cell;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import mapZelda.Map;
import mapZelda.MapReader;
import model.Game;

public class Controleur implements Initializable{
	
	@FXML private TilePane mapPane;
	@FXML private AnchorPane underMapPane;
	
	private final static int SHOWNROW = 10;
	private final static int SHOWNCOLUMN = 10;
	private final static int OLDVALUEINDEX = 0;
	private final static int NEWVALUEINDEX = 1 ; 
	private final static int STEPTIME = 200 ;
	private final static Image MAINIMAGE = new Image(Game.IMAGESPATH + "pokemonTiles.png");
	private final static String MAPFILENAME = "newMap.csv";
	private ImageView[][] tileMapImage;
	private Game game;
	private HashMap<Integer,ImageView> persoMap;
	private Integer[] caseChangeList;
	private boolean mouvementPersonnagePossible;
	private Timeline timeline;

	private int getTileDimension() {
		return 16;
	}
	
	public Controleur() {
		this.game = new Game(MAPFILENAME);
		this.persoMap = new HashMap<Integer,ImageView>();
		this.caseChangeList = new Integer[2];
		this.mouvementPersonnagePossible = true;
		this.timeline = new Timeline();
		timeline.setOnFinished(e->{
			this.mouvementPersonnagePossible = true;
		});
		timeline.setAutoReverse(false);
		
	}

	
	
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mapPane.setPrefTileHeight(getTileDimension());
		this.mapPane.setPrefTileWidth(getTileDimension());
		this.game.changeProperty().addListener(
				(obs,oldValue,newValue)->executeAction(newValue.intValue())
		);
		initialiseCurrentMap();
		this.game.startGame();
	}

	public void startScene(Scene scene) {
		scene.setOnKeyPressed(
			e -> handleKey(e)
		);
	}
	
	private void handleKey(KeyEvent event) {
		KeyCode code = event.getCode();
		if(this.mouvementPersonnagePossible) {
			this.mouvementPersonnagePossible = false;
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
				else 
					this.mouvementPersonnagePossible = true;
			}catch(IllegalArgumentException e) {
				this.mouvementPersonnagePossible = true;
			}
		}
	}
	
	private void executeAction(Integer newValue) {
		if(this.game.getLastCharChangeCategory() == Cell.CHARCHANGE) {
			if(this.caseChangeList[OLDVALUEINDEX] == null)
				this.caseChangeList[OLDVALUEINDEX] = newValue;
			else if(this.caseChangeList[NEWVALUEINDEX] == null) {
				this.caseChangeList[NEWVALUEINDEX] = newValue;
				this.movePersonnage();
			}
		}
	}

	
	private void addPersonnageToList(Integer key,int[] startLocation) {
		ImageView viewer = new ImageView(new Image(Game.IMAGESPATH + this.game.characterImageNameProperty(key).get()));
		viewer.prefWidth(getTileDimension());
		viewer.prefHeight(getTileDimension());
		viewer.setScaleX(1.8);
		viewer.setScaleY(1.8);
		this.game.characterImageNameProperty(key).addListener(
				(obs,oldValue,newValue)->{
					Image image = new Image(Game.IMAGESPATH +  newValue);
					
					viewer.setImage(image);
				}
		);
		this.persoMap.put(key, viewer);
		this.underMapPane.getChildren().add(viewer);
		if(key == Game.HEROKEY) {
			viewer.layoutXProperty().addListener(
				(obs,oldValue,newValue)->{
					double x = newValue.doubleValue();
					if(x >= this.convertToViewSize(SHOWNCOLUMN/2) ) {
						if(x <= this.convertToViewSize(this.game.getMapWidth() - (SHOWNCOLUMN+1)/2))
							this.underMapPane.setLayoutX(convertToViewSize(SHOWNCOLUMN/2) - newValue.doubleValue() );
					}
				}	
			);	
			
			viewer.layoutYProperty().addListener(
					(obs,oldValue,newValue)->{
						double y = newValue.doubleValue();
						if(y >= this.convertToViewSize(SHOWNROW/2) ) {
							if(y <= this.convertToViewSize(this.game.getMapHeight() - ((SHOWNROW + 1)/2)))
								this.underMapPane.setLayoutY(convertToViewSize(SHOWNROW/2) - newValue.doubleValue()  );
						}
					}	
				);	

		}
		
		viewer.setLayoutY(convertToViewSize(startLocation[mapZelda.Map.ROWINDEX]));
		viewer.setLayoutX(convertToViewSize(startLocation[mapZelda.Map.COLUMNINDEX]));

	}
	
	private int convertToViewSize(int rowNumber) {
		return rowNumber * getTileDimension();
	}
	
	
	private void movePersonnage() {
		Integer key = this.game.getLastChangedId();
		int[] newLocation = this.game.convertFromCellId(this.caseChangeList[NEWVALUEINDEX]);
		int[] oldLocation = this.game.convertFromCellId(this.caseChangeList[OLDVALUEINDEX]);
		if(this.game.isFromVoidCell(this.caseChangeList[OLDVALUEINDEX])) {
			this.addPersonnageToList(key,newLocation);
		}
		
		else if(this.game.isFromVoidCell(this.caseChangeList[1])) {
			//TODO
		}
		
		else {
			int movementADroite = oldLocation[Map.COLUMNINDEX] - newLocation[Map.COLUMNINDEX] ;
			int movementEnBas = oldLocation[Map.ROWINDEX] - newLocation[Map.ROWINDEX];
			ImageView current = Controleur.this.persoMap.get(key);
			DoubleProperty layoutYProperty = current.layoutYProperty();
			DoubleProperty layoutXProperty = current.layoutXProperty();
			Duration rightMovingEndDuration = new Duration(STEPTIME * Math.abs(movementADroite));
			timeline.getKeyFrames().clear();
			timeline.getKeyFrames().addAll(
					new KeyFrame(Duration.ZERO, new KeyValue(layoutXProperty,layoutXProperty.get())),
					new KeyFrame(rightMovingEndDuration,new KeyValue(layoutXProperty,layoutXProperty.get() - convertToViewSize(movementADroite))),
					new KeyFrame(rightMovingEndDuration, new KeyValue(layoutYProperty,layoutYProperty.get())),
					new KeyFrame(new Duration(rightMovingEndDuration.toMillis() + STEPTIME * Math.abs(movementEnBas)),new KeyValue(layoutYProperty,layoutYProperty.get() - convertToViewSize(movementEnBas)))
			);
			timeline.playFromStart();
		}
		this.caseChangeList[OLDVALUEINDEX] = this.caseChangeList[NEWVALUEINDEX];
		this.caseChangeList[NEWVALUEINDEX] = null;
	}

	private  void defineWidthAndHeight(Pane pane,int maxWidth,int maxHeight) {
		pane.setMinHeight(convertToViewSize(maxHeight));
		pane.setMaxHeight(convertToViewSize(maxHeight));
		pane.setMinWidth(convertToViewSize(maxWidth));
		pane.setMaxWidth(convertToViewSize(maxWidth));
		pane.setLayoutX(0);
		pane.setLayoutY(0);
	}
	
	public void initialiseCurrentMap() {
		int maxHeight = this.game.getMapHeight();
		int maxWidth = this.game.getMapWidth();
		tileMapImage = new ImageView[maxHeight][maxWidth];
		mapPane.setPrefRows(maxHeight);
		mapPane.setPrefColumns(maxWidth);
		this.defineWidthAndHeight(underMapPane, maxWidth, maxHeight);
		
		
		for(int i = 0 ; i < tileMapImage.length;i++) {
			for(int j = 0 ; j < tileMapImage[i].length;j++) {
				this.tileMapImage[i][j]  = new ImageView(MAINIMAGE);
				 setCaseImage(i,j);
				 this.mapPane.getChildren().add(tileMapImage[i][j]);
			}
		}
	}

	private void setCaseImage(int row,int column) {
		Background caseBackground = this.game.getBackground(row, column);
		int xi = caseBackground.getTile()[0] * MapReader.TILESDIMENSION ;
		int yi = caseBackground.getTile()[1] * MapReader.TILESDIMENSION;
		Rectangle2D rectangle = new Rectangle2D(xi, yi, MapReader.TILESDIMENSION, MapReader.TILESDIMENSION);
		this.tileMapImage[row][column].setViewport(rectangle);
		this.tileMapImage[row][column].setScaleY(getTileDimension()*1.0/MapReader.TILESDIMENSION);
		this.tileMapImage[row][column].setScaleX(getTileDimension()*1.0/MapReader.TILESDIMENSION);

	}

}
