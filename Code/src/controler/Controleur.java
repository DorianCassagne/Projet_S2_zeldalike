package controler;

import java.net.URL;


import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	final static int TILEDIMENSION = 32; // dimension des tiles en pixel
	final static int ROWINDEX = 0 ; 
	final static int COLUMNINDEX = 1 ;
	final static TexturePack TEXTURE ; 
	private final static String TILESETPATH = "src/resources/tileset/jeudi24.png"; // chemin vers le fichier de tilesset
	@FXML private AnchorPane mainAnchorPane; 
	@FXML private AnchorPane characterAnchorPane;
	@FXML private TilePane mapTilePane;
	private StackPane[] cellsItemAndBackground;
	private Game myGame; 
	private CommandInterpreter interpreter;
	private GameLoop gameLoop;
	private IntegerProperty changeProperty;
	
	static { // initialise les valeurs statique
		TEXTURE = new TexturePack(TILESETPATH,GameMap.LINELENGTH, TILEDIMENSION);
	}
	/*
	 *  créer la game , 
	 *  le stackpane recoit un new stackpane de dimension MAPLENGTH*MAPLENGTH (64*64) qui est une taille fixe
	 *  recoit le commandinterpreter qui sert a recuperer les entrées du clavier 
	 */
	
	public Controleur() { 
		myGame = new Game();
		this.cellsItemAndBackground = new StackPane[MapReader.MAPLENGTH * MapReader.MAPLENGTH];
		this.interpreter = new CommandInterpreter(myGame,gameLoop); 
	
	}
	
	/*initialise la gameloop 
	 * fait appel a la methode fixePaneDimension qui fixe les dimensions du personnage et de la maptilepane
	 * on fait appel a la methode InitialiseCells
	 * on recupere le changeproperty avec la GAME
	 * on démarre la gameloop
	 * on met un listener a change property qui recoit une arraylist 
	 * 		qui a une methode changement (changed) 
	 * 		
	 * 		
	 * 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.gameLoop = new GameLoop(myGame,this.characterAnchorPane);
		fixePaneDimension(MapReader.MAPLENGTH * TILEDIMENSION); //64 *32 
		initialiseCells();
		changeProperty = myGame.getChangeProperty();
		gameLoop.start();
		changeProperty.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				System.out.println(newValue.intValue()); 							// newValue.intValue() = id de la case changé
				cellsItemAndBackground[newValue.intValue()].getChildren().clear(); 	// on clear les children du stackpane a la case X
				int backgroundId = myGame.getBackgroundId(newValue.intValue()); 	// on donne la valeur de l'id du background a la case X  
				Image image = TEXTURE.getImg(backgroundId); 						// on recupere l'image associer a l'id du background
				ImageView background = new ImageView(image); 						// l'imageview recupere l'image
				cellsItemAndBackground[newValue.intValue()].getChildren().add(background); // le stackpane a la case X recoit l'imageview
			}
			
		});
	
	}
	
	public void startScene(Scene scene) {
	
		scene.setOnKeyPressed(e->interpreter.handleKey(e));
	
	}
	

	/*
	 * fixe la dimension de l'anchorpane du character
	 * 
	 * recoit en parametre la dimension (64cases*32pixels)
	 * dimension toujours 2048 
	 *  
	 */
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
