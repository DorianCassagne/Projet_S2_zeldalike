package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import character.ThreadTime;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import model.Game;
import texture.TexturePack;

public class Controleur implements Initializable{

	 @FXML
	    private Button butO;

	@FXML
	private AnchorPane mainAnchorPane;
	
	private Game game;

	@FXML
	static TexturePack textu;
	private DoubleProperty decalMapX;

	private DoubleProperty decalMapY;
	private StringProperty KeyboardInput;
	private ThreadTime tr;
	
	public final static int MAPWIDTH=8;
	public final static int SIZETILE=32;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textu = new TexturePack("img/tile.png", MAPWIDTH, SIZETILE);
		KeyboardInput = new SimpleStringProperty();
		decalMapX = new SimpleDoubleProperty();
		decalMapY = new SimpleDoubleProperty();
		 ArrayList<String> arPath= new ArrayList<String>();
		arPath.add("/file/test");
		game = new Game(arPath, "img/tile.png", MAPWIDTH, this,this.KeyboardInput);
		
		ArrayList<ImageView> ar= game.getAllText();
		for (ImageView imageView : ar) {
			imageView.translateXProperty().bind(decalMapX);
			imageView.translateYProperty().bind(decalMapY);
		}

		mainAnchorPane.getChildren().addAll(ar);

		tr=new ThreadTime(game);
		   
	}
	
	public void startScene(Scene scene) {
		scene.setOnKeyPressed(
			e -> handleKey(e)
		);
	}
	private void handleKey(KeyEvent event) {
		KeyCode code = event.getCode();
		try {
			if(code.equals(KeyCode.UP)) {
				KeyboardInput.set("up");
				//System.out.println("uppp");
			}
			else if(code.equals(KeyCode.LEFT)) {
				KeyboardInput.set("left");
			}
			else if(code.equals(KeyCode.RIGHT)) {
				KeyboardInput.set("right");
			}
			else if(code.equals(KeyCode.DOWN)) {
				KeyboardInput.set("down");
			}
			else if(code.equals(KeyCode.S)) {
				System.out.println("test");
				tr.start();
			}
			}catch(IllegalArgumentException e) {
			}
		
		
	}
	
	public void MewMap(int x, int y) {
		decalMapX.set(x*32+decalMapX.get()); 
		decalMapY.set(y*32+decalMapY.get()); 
	}
	public void loadMap() {
		
	}
	
//	@FXML
//	public void test(Event e) {
//		//System.out.println("test");
//		ThreadTime tr=new ThreadTime(game);
//		tr.start();
//	}
	

	


}
