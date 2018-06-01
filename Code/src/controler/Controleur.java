package controler;

import java.net.URL;




import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import model.Game;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import vue.MapView;
import vue.TexturePack;

public class Controleur implements Initializable{
	
	public final static TexturePack TEXTURE ;

	private final static String TILESETPATH = "src/resources/tileset/jeudi24.png";
	
	@FXML private AnchorPane mainAnchorPane;
	@FXML private AnchorPane characterAnchorPane;
	@FXML private TilePane mapTilePane;

	private Game myGame;
	private CommandInterpreter interpreter;
	private GameLoop gameLoop;
	private MapView myMapView;
	
	static {
		TEXTURE = new TexturePack(TILESETPATH,Statics.LINELENGTH, ConvertionAndStatics.TILEDIMENSION);
	}
	
	public Controleur() {

		myGame = new Game();
	
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.gameLoop = new GameLoop(myGame,this.characterAnchorPane);
		ConvertionAndStatics.fixPaneDimension(MapReader.MAPLENGTH * ConvertionAndStatics.TILEDIMENSION,this.mapTilePane,this.characterAnchorPane);
		this.createMap();
		gameLoop.start();
		this.interpreter = new CommandInterpreter(myGame,gameLoop);
		
	}
	
	private void createMap() {
		Function< Integer,Integer[]> backgroundSource = element->this.myGame.getLayerForCell(element);
		this.myMapView = new MapView(backgroundSource,this.mapTilePane);
		this.myMapView.initialise();
	}

	public void startScene(Scene scene) {
		scene.setOnKeyPressed(e->interpreter.handleKey(e));
	}
	

	
	
	
	
}
