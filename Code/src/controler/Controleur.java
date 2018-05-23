package controler;

import java.net.URL;

import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;
import model.Game;
import model.character.Hero;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.NewMovable;
import model.gameMap.move.Move;
import vue.TexturePack;

public class Controleur implements Initializable{
	
	final static int TILEDIMENSION = 32;
	final static int ROWINDEX = 0;
	final static int COLUMNINDEX = 1 ;
	final static TexturePack TEXTURE ;
	public final static int FRAMEDURATION = 17;
	
	private final static String TILESETPATH = "src/resources/tileset/futuretileset.png";
	
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
		TEXTURE = new TexturePack(TILESETPATH,GameMap.LINELENGTH, TILEDIMENSION);
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
			this.myGame.communiquerMovement(Hero.MOVEUP);
			break;
		case DOWN:
			this.myGame.communiquerMovement(Hero.MOVEDOWN);
			break;
		case LEFT:
			this.myGame.communiquerMovement(Hero.MOVELEFT);
			break;
		case RIGHT: 
			this.myGame.communiquerMovement(Hero.MOVERIGHT);
			break;
		case A :
			this.myGame.communiquerMovement(Hero.ATTACK);
			System.out.println("Attacl");
			break;
		default :
			System.out.println("Unknown key");
		}
	}
	
	private void initialiseGameLoop() {
		this.gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(
				Duration.millis(FRAMEDURATION),
				(ev->{
					if(this.myGame.end()) {
						gameLoop.stop();
					}
					else {
						addPlayers(this.myGame.getNewPlayers());
						removePlayers(this.myGame.getRemovedMovable());
						playMoves(this.myGame.turn());
						
						allTick();
					}
				})
		);
		gameLoop.getKeyFrames().add(frame);
		gameLoop.playFromStart();
	}

	
	private void removePlayers(int[] playersId) {
	    FadeTransition ft = new FadeTransition(Duration.millis(2000));
	    ft.setFromValue(1.0);
		for(Integer playerId : playersId) {
			MovableView current = this.movableList.get(playerId);
			ft.setToValue(0);
			ft.setNode(current);
			ft.play();
			this.movableList.remove(playerId);
			ft.setOnFinished(e->this.characterAnchorPane.getChildren().remove(current));
		}
	}
	
	private void allTick() {
		for(MovableView viewMovabe : this.movableList.values()) {
			viewMovabe.tick();
		}
	}
	
	private void playMoves(Move[] moves ) {
		for (Move move : moves) {
				MovableView movable = this.movableList.get(move.getMovableId());
				movable.moveTo(move.getEndCellId(), move.getImageValue(),move.getSpeed());
		}
	}
	
	private void addPlayers(NewMovable[] newPlayers) {
		for(NewMovable newPlayer : newPlayers) {
			MovableView newMovable = new MovableView(newPlayer.getCellId(),newPlayer.getImageValue(),this.characterAnchorPane);
			this.characterAnchorPane.getChildren().add(newMovable);
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
			int backgroundId = this.myGame.getBackgroundId(cellId);
			Image image = TEXTURE.getImg(backgroundId);
			ImageView background = new ImageView(image);
			current.getChildren().add(background);
			this.mapTilePane.getChildren().add(current);
		}
	}
	
	
	public static int[] convertToViewSize(int cellId) {
		int row = (cellId / MapReader.MAPLENGTH)*TILEDIMENSION;
		int column = (cellId%MapReader.MAPLENGTH)*TILEDIMENSION;
		int[] position = {row,column};
		return position;
	}
}
