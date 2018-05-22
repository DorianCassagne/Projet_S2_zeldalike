package controler;

import java.net.URL;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import model.Game;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.NewCharacter;
import model.gameMap.move.Move;

public class Controleur implements Initializable{
	
	final static int TILEDIMENSION = 32;
	final static int ROWINDEX = 0;
	final static int COLUMNINDEX = 1 ;
	private static Image TILESETIMAGE ;
	private final static String TILESETPATH = "/resources/tileset/test.png";
	
	@FXML
	private AnchorPane mainAnchorPane;
	@FXML
	private AnchorPane characterAnchorPane;
	@FXML
	private TilePane mapTilePane;
	
	private HashMap<Integer,MovableView> movableList;
	private StackPane[] cellsItemAndBackground;
	private Game myGame;
	private Timeline gameLoop;
	
	static {
		try {
			TILESETIMAGE = new Image(Controleur.class.getResource(TILESETPATH).toURI().toURL().toExternalForm());
		}catch(Exception e) {
			TILESETIMAGE = null;
			Alert alert = new Alert(Alert.AlertType.ERROR,"L'image n'a pas pu être lue ");
			alert.show();
		}
	}	
	
	public Controleur() {
		myGame = new Game();
		this.cellsItemAndBackground = new StackPane[MapReader.MAPLENGTH * MapReader.MAPLENGTH];
		this.movableList = new HashMap<Integer,MovableView>();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fixePaneDimension(MapReader.MAPLENGTH * TILEDIMENSION);
		initialiseCells();
		initialiseGameLoop();
	}
	
	
	
	
	
	public void startScene(Scene scene) {
		scene.setOnKeyPressed(e->handleKey(e));
	}
	
	private void handleKey(KeyEvent event) {
		switch(event.getCode()) {
		case UP : 
			System.out.println("Up");
			break;
		case DOWN:
			System.out.println("Down");
			break;
		case LEFT:
			System.out.println("LEFT");
			break;
		case RIGHT: 
			System.out.println("RIGHT");
			break;
		default :
			System.out.println("Unknown key");
		}
	}
	
	private void initialiseGameLoop() {
		this.gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(
				Duration.seconds(0.017),
				(ev->{
					if(this.myGame.end()) {
						gameLoop.stop();
					}
					else {
						addPlayers(this.myGame.getNewPlayers());
						playMoves(this.myGame.turn());
					}
				})
				);
	}

	private void playMoves(Move[] moves ) {
		for (Move move : moves) {
			MovableView movable = this.movableList.get(move.getMovableId());
			movable.moveTo(move.getEndCellId(), move.getImageValue(),move.getSpeed());
		}
	}
	
	private void addPlayers(NewCharacter[] newPlayers) {
		for(NewCharacter newPlayer : newPlayers) {
			MovableView newMovable = new MovableView(newPlayer.getCellId(),newPlayer.getImageValue());
			this.movableList.put(newPlayer.getKey(), newMovable);
		}
		
	}
	
	private void fixePaneDimension(int dimension) {
		this.characterAnchorPane.setMinHeight(dimension);
		this.characterAnchorPane.setMaxHeight(dimension);
		this.characterAnchorPane.setMinWidth(dimension);
		this.characterAnchorPane.setMaxWidth(dimension);
		this.mapTilePane.setPrefColumns(MapReader.MAPLENGTH);
		this.mapTilePane.setPrefRows(MapReader.MAPLENGTH);
	}
	
	private void initialiseCells() {
		for(int cellId = 0 ; cellId < this.cellsItemAndBackground.length ;cellId++) {
			this.cellsItemAndBackground[cellId] = new StackPane();
			StackPane current = this.cellsItemAndBackground[cellId];
			ImageView background = new ImageView(TILESETIMAGE);
			int backgroundId = this.myGame.getBackgroundId(cellId);
			setViewPort(background,backgroundId);
			this.mapTilePane.getChildren().add(current);
		}
	}
	
	static void setViewPort(ImageView viewer,int imageId) {
		int row = imageId * TILEDIMENSION / GameMap.LINELENGTH ;
		int column = (imageId  % GameMap.LINELENGTH)*TILEDIMENSION;
		viewer.setViewport(new Rectangle2D(row, column, 32, 32));
	
	}
	
	public static int[] convertToViewSize(int cellId) {
		int row = (cellId / MapReader.MAPLENGTH)*TILEDIMENSION;
		int column = (cellId*TILEDIMENSION - row);
		int[] position = {row,column};
		return position;
	}
}
