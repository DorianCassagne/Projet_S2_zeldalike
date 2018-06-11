package controler;


import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import controler.conversion.ConversionAndStatics;
import controler.gameLoop.ControlerEncoder;
import controler.gameLoop.GameLoop;
import controler.mainGame.GroundControler;
import controler.mainGame.SceneLoader;
import controler.withGame.CommandInterpreter;
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
import vue.gameClass.MapView;
import vue.gameClass.MessageView;
import vue.other.TexturePack;

public class Controleur implements Initializable,SceneLoader{
	
	public final static TexturePack TEXTURE ;
	public final static String FXMLPATH = "template/GuiView.fxml";
	private final static String TILESETPATH = "src/resources/tileset/Image/jeudi7.png";
	
	@FXML    private AnchorPane mainAnchorPane;
    @FXML    private AnchorPane characterAnchorPane;
    @FXML    private TilePane mapTilePane;
    @FXML    private ImageView avatarImage;
    @FXML    private Label HPLabel;
    @FXML    private ProgressBar HPProgressBar;
    @FXML    private Label MPLabel;
    @FXML    private ProgressBar MPProgressBar;
    @FXML    private ImageView itemImageView;
    @FXML    private ImageView attackImageView;
    @FXML    private ImageView defImageView;
    @FXML 	 private TextArea messageText;
    
	private Game myGame;
	private CommandInterpreter interpreter;
	private GameLoop gameLoop;
	private MapView myMapView;
	private StringProperty messageZone;
	private ControlerEncoder controllerData;
	private GroundControler sceneChanger;
	
	static {
		TEXTURE = new TexturePack(TILESETPATH,Statics.LINELENGTH, ConversionAndStatics.TILEDIMENSION);
	}
	
	/*
	 * Initializer
	 */
	
	public Controleur() {
		this.messageZone = new SimpleStringProperty();
		this.initGame();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.initMap();
		this.initControllerData();
		this.initGameInterpreterAndLoop();	
	}
	
	
	/*
	 * Public methods
	 */

	public void startScene(Scene scene) {
		scene.setOnKeyPressed(e->interpreter.handleKey(e));
	}
	

	/*
	 * Private methods
	 */
	
	//Initialiser
	private void initGame() {
		this.myGame = new Game(this.messageZone);
		this.myGame.changeMapProperty().addListener(
				(obs,oldValue,newValue)->this.createMap()
		);
	}
	
	private void initControllerData() {
		this.controllerData = new ControlerEncoder(this.characterAnchorPane, this.HPLabel, this.HPProgressBar,this.MPLabel,this.MPProgressBar,this.defImageView,this.attackImageView,this.myGame);
	}

	private void initGameInterpreterAndLoop() {
		MessageView message = new MessageView(this.messageText, this.messageZone);
		this.gameLoop = new GameLoop(this.messageZone,this.controllerData);
		System.out.println(this.controllerData);
		this.interpreter = new CommandInterpreter(this.myGame,this.gameLoop,message.waitingProperty());
		this.gameLoop.start();
	}
	
	private void initMap() {
		ConversionAndStatics.fixPaneDimension(MapReader.MAPLENGTH * ConversionAndStatics.TILEDIMENSION,this.mapTilePane,this.characterAnchorPane);
		this.createMap();
	}
	
	/*
	 * MapChange Handler
	 */
	 
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

	@Override
	public void loadScene(GroundControler groundControler) {
		this.sceneChanger = groundControler;
		this.sceneChanger.getScene().setOnKeyPressed(e->interpreter.handleKey(e));
	}

	
	
	
	
}
 