/*
 * Controleur principale qui contient : - les chemins vers toutes les autres vues
 * 										- le chemin vers le tileset
 * qui s'occupe de : 	- l'affichage de la TileMap
 * 						- l'affichage de la barre contenant les informations relatives au Hero (HP,MP, items, score, ...)
 * 
 * 										
 */

package controler;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;
import java.util.function.Supplier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import controler.conversion.ConversionAndStatics;
import controler.gameLoop.ControlerEncoder;
import controler.gameLoop.GameLoop;
import controler.input.CommandInterpreter;
import controler.mainGame.GroundControler;
import controler.mainGame.SceneLoader;
import controler.menu.ChargerMapController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Text;
import model.Game;
import model.GameStatus;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import vue.gameClass.HeroView;
import vue.gameClass.MapView;
import vue.gameClass.MessageView;
import vue.other.TexturePack;

public class Controleur implements Initializable,SceneLoader{
	
	public final static TexturePack TEXTURE ;
	public final static String FXMLGAMEPATH = "template/GuiView.fxml";
	public final static String FXMLMAINMENUPATH = "menu/MenuAccueil.fxml";
	public final static String FXMLMAINMENU2PATH = "menu/MenuAccueil2.fxml";
	public final static String FXMLLOADMENUPATH = "menu/MenuChargerMap.fxml";
	public final static String FXMLPAUSEPATH = "menu/MenuDePause.fxml";
	public final static String FXMLGAMEOVERMENUPATH = "menu/GameOverMenu.fxml";
	private final static String TILESETPATH = "src/resources/tileset/Image/samedi16.png";
	
	private static GameStatus myGameStat ;

	@FXML   private AnchorPane mainAnchorPane;
    @FXML   private AnchorPane characterAnchorPane;
    @FXML   private TilePane mapTilePane;
    @FXML   private ImageView avatarImage;
    @FXML   private Label HPLabel;
    @FXML   private ProgressBar HPProgressBar;
    @FXML   private Label MPLabel;
    @FXML   private ProgressBar MPProgressBar;
    @FXML   private ImageView attackImageView;
    @FXML   private ImageView defImageView;
    @FXML   private TextArea messageText;
    @FXML	private Text scoreText;
    @FXML	private Button DefenseButton;
    @FXML   private Button attackButton;
    
	private Game myGame;
	private CommandInterpreter interpreter;
	private GameLoop gameLoop;
	private MapView myMapView;
	private StringProperty messageZone;
	private ControlerEncoder controllerData;
	private GroundControler sceneChanger;
	
	
	static {
		TEXTURE = new TexturePack(TILESETPATH,Statics.LINELENGTH, ConversionAndStatics.TILEDIMENSION);
		myGameStat = null;
	}
	
	

	public static void setGameStat(ChargerMapController mapLoader , GameStatus gameStatus) {
		if(mapLoader != null) {
			myGameStat = gameStatus;
		}
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
		this.scoreText.textProperty().bind(GameMap.getScoreBinding().asString());
	}
	
	
	/*
	 * Public methods
	 */

	@Override
	public void loadScene(GroundControler groundControler) {
		if(groundControler != null) {
			this.sceneChanger = groundControler;
			Supplier<Void> resumeGameLoop = ()->{
				this.gameLoop.start();
				return null;
			};
			this.controllerData.setGround(groundControler);
			this.sceneChanger.getScene().setOnKeyPressed(e->interpreter.handleKey(e,groundControler));
			this.sceneChanger.setGameLoopStart(resumeGameLoop);
		}
	}
	
	public void resume() {
		this.gameLoop.start();
	}
	
	/*
	 * Private methods
	 */
	
	//Initialiser
	private void initGame() {
		this.myGame = new Game(this.messageZone,myGameStat);
		this.myGame.changeMapProperty().addListener(
				(obs,oldValue,newValue)->this.createMap()
		);
	}
	
	private void initControllerData() {
		this.controllerData = new ControlerEncoder(this.characterAnchorPane, this.HPLabel, this.HPProgressBar,this.MPLabel,this.MPProgressBar,this.myGame);
		this.controllerData.addButtonImage(attackImageView, attackButton,HeroView.ATTACKINDEX);
		this.controllerData.addButtonImage(defImageView, DefenseButton,HeroView.DEFENSEINDEX);

	}

	/*
	 * Methode qui - initialise la gameloop et la demarre 
	 * 			   - initialise le controleur de commande
	 */
	private void initGameInterpreterAndLoop() {
		MessageView message = new MessageView(this.messageText, this.messageZone);
		
		this.gameLoop = new GameLoop(this.messageZone,this.controllerData);
		this.interpreter = new CommandInterpreter(this.myGame,this.gameLoop,message.waitingProperty());
		
		this.gameLoop.start();
	
	}
	
	/*
	 * Methode qui initialise la map avec les dimensions requises
	 */
	private void initMap() {
		ConversionAndStatics.fixPaneDimension(MapReader.MAPLENGTH * ConversionAndStatics.TILEDIMENSION,this.mapTilePane,this.characterAnchorPane);
		this.createMap();
	}
	
	/*
	 * MapChange Handler
	 */
	 
	/*
	 * Methode qui creer la map
	 */
	private void createMap() {	
		clearCharacterPane();
		Function< Integer,Integer[]> backgroundSource = element->this.myGame.getLayerForCell(element);
		this.myMapView = new MapView(backgroundSource,this.mapTilePane,this.myGame.getMapChangeProperty());
		this.myMapView.initialise();
	}
	
	/*
	 * Methode qui nettoie le CharacterAnchorPane
	 */
	private void clearCharacterPane() {
		int i = 0;
		while(i < this.characterAnchorPane.getChildren().size()){
			if(this.characterAnchorPane.getChildren().get(i) != this.mapTilePane)
				this.characterAnchorPane.getChildren().remove(this.characterAnchorPane.getChildren().get(i));
			else
				i++;
		}
	}	
}
 