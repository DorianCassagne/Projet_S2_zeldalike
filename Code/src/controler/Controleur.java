package controler;

import java.net.URL;


import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import model.Game;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import vue.TexturePack;

public class Controleur implements Initializable{
	
	final static int TILEDIMENSION = 32;
	final static int ROWINDEX = 0;
	final static int COLUMNINDEX = 1 ;
	final static TexturePack TEXTURE ;
	private final static String TILESETPATH = "src/resources/tileset/jeudi24.png";
	
	@FXML private AnchorPane mainAnchorPane;
	@FXML private AnchorPane characterAnchorPane;
	@FXML private TilePane mapTilePane;
	private StackPane[] cellsItemAndBackground;
	private Game myGame;
	private CommandInterpreter interpreter;
	private GameLoop gameLoop;
	
	static {
		TEXTURE = new TexturePack(TILESETPATH,GameMap.LINELENGTH, TILEDIMENSION);

	}
	
	public Controleur() {

		myGame = new Game();
		this.cellsItemAndBackground = new StackPane[MapReader.MAPLENGTH * MapReader.MAPLENGTH];
		this.interpreter = new CommandInterpreter(myGame,gameLoop);
	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.gameLoop = new GameLoop(myGame,this.characterAnchorPane);
		fixePaneDimension(MapReader.MAPLENGTH * TILEDIMENSION);
		initialiseCells();
		gameLoop.start();
	
	}
	

	public void startScene(Scene scene) {
	
		scene.setOnKeyPressed(e->interpreter.handleKey(e));
	
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
