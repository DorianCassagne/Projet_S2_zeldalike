package controler;

import java.net.URL;





import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import model.Game;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import vue.MapView;
import vue.MessageView;
import vue.TexturePack;

public class Controleur implements Initializable{
	
	public final static TexturePack TEXTURE ;

	private final static String TILESETPATH = "src/resources/tileset/Image/lastTileset.png";
	
	@FXML private TextArea messageText;
	@FXML private AnchorPane mainAnchorPane;
	@FXML private AnchorPane characterAnchorPane;
	@FXML private TilePane mapTilePane;
	@FXML private ImageView avatarImage;
    @FXML private Label MPLabel;
    @FXML private ProgressBar MPProgressBar;
    @FXML private ImageView itemImage;
    @FXML private ImageView attackImage;
    @FXML private Label HPLabel;
    @FXML private ProgressBar HPProgressBar;
    
	private Game myGame;
	private CommandInterpreter interpreter;
	private GameLoop gameLoop;
	private MapView myMapView;
	private StringProperty messageZone;
	
	static {
		TEXTURE = new TexturePack(TILESETPATH,Statics.LINELENGTH, ConvertionAndStatics.TILEDIMENSION);
	}
	
	public Controleur() {
		this.messageZone = new SimpleStringProperty();
		this.myGame = new Game(messageZone);
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		initMap();
		initGameInterpreter();
		this.gameLoop.start();
		this.myGame.changeMapProperty().addListener(
				(obs,oldValue,newValue)->this.createMap()
		);
		
	}
	
	private void initGameInterpreter() {
		MessageView message = new MessageView(this.messageText, this.messageZone);
		this.gameLoop = new GameLoop(myGame,this.characterAnchorPane,this.HPLabel,this.HPProgressBar,this.messageZone);
		this.interpreter = new CommandInterpreter(myGame,gameLoop,message.waitingProperty());
	}
	
	private void initMap() {
		ConvertionAndStatics.fixPaneDimension(MapReader.MAPLENGTH * ConvertionAndStatics.TILEDIMENSION,this.mapTilePane,this.characterAnchorPane);
		this.createMap();

	}
	
	private void createMap() {
		
		clearCharacterPane();
		Function< Integer,Integer[]> backgroundSource = element->this.myGame.getLayerForCell(element);
		this.myMapView = new MapView(backgroundSource,this.mapTilePane,this.myGame.getMapChangeProperty());
		this.myMapView.initialise();
	
	}
	
	private void clearCharacterPane() {
		int i = 0;
		while(i < this.characterAnchorPane.getChildren().size()){
			if(this.characterAnchorPane.getChildren().get(i) != this.mapTilePane)
				this.characterAnchorPane.getChildren().remove(this.characterAnchorPane.getChildren().get(i));
			else
				i++;
		}
	}

	public void startScene(Scene scene) {
		scene.setOnKeyPressed(e->interpreter.handleKey(e));
	}
	

	
	
	
	
}
