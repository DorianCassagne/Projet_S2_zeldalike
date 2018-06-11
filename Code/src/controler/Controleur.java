package controler;

import java.io.IOException;
import java.net.URL;




import java.util.ResourceBundle;
import java.util.function.Function;

import app.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import model.Game;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import model.scenario.Scenario;
import vue.MapView;
import vue.TexturePack;

public class Controleur implements Initializable{

	public final static TexturePack TEXTURE ;

	private final static String TILESETPATH = "src/resources/tileset/Image/LastTileset.png";

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

	@FXML private TableView<Scenario> ScenarioTable;
	@FXML private TableColumn<Scenario, String> colGame;
	@FXML private Button NewGameButton;
	@FXML private Button LoadMenuButton;
	@FXML private Button LoadGameButton;
	@FXML private Button LeaveButton;
	@FXML private Button ContinueButton;
	@FXML private Button DelGameButton;
	@FXML private Button PreviousButton;

	
	@FXML
    void playPreviousGame(ActionEvent event) {
		System.out.println("1");
    }

	@FXML
	void createNewGame(ActionEvent event) {
		System.out.println("1");
		//myGame = new Game();
	}

	@FXML
	void leaveButton(ActionEvent event) {
		System.exit(1);
	}

	
	@FXML
    void delGame(ActionEvent event) {
		System.out.println("1");
    }

    @FXML
    void loadGame(ActionEvent event) {
    	System.out.println("1");
    }

    @FXML
    void previousMenu(ActionEvent event) {
    	System.out.println("1");
    }
    

	static {
		TEXTURE = new TexturePack(TILESETPATH,Statics.LINELENGTH, ConvertionAndStatics.TILEDIMENSION);
	}

	public Controleur() {
		myGame = new Game();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.gameLoop = new GameLoop(myGame,this.characterAnchorPane,this.HPLabel,this.HPProgressBar);
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(Main.class.getResource("/vue/menu/MenuAccueil.fxml"));
			AnchorPane root = loader.load();
			this.mainAnchorPane.getChildren().clear();
			this.mainAnchorPane.getChildren().add(root);
		} catch (IOException e) {

		}

		ConvertionAndStatics.fixPaneDimension(MapReader.MAPLENGTH * ConvertionAndStatics.TILEDIMENSION,this.mapTilePane,this.characterAnchorPane);
		this.createMap();
		gameLoop.start();
		this.interpreter = new CommandInterpreter(myGame,gameLoop);

		this.myGame.changeMapProperty().addListener(
				(obs,oldValue,newValue)->this.createMap()
				);

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
