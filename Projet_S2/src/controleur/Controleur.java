package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import map.GameMap;
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textu = new TexturePack("img/tile.png", 8, 32);
		
		
		
		decalMapX = new SimpleDoubleProperty();
		decalMapY = new SimpleDoubleProperty();
		System.out.println("test");
		 ArrayList<String> arPath= new ArrayList<String>();
		arPath.add("/file/test");
		game = new Game(arPath, "img/tile.png", 8);
		
		//int[][] tab = {{1115,1123,1131},{1116,1124,1132},{1117,1125,1133}};
		//GameMap map =new GameMap( tab );/*, {{94,94,94},{94,94,94},{94,94,94}} , {null,null,null} */
			
		ArrayList<ImageView> ar= game.getAllText();
		for (ImageView imageView : ar) {
			imageView.translateXProperty().bind(decalMapX);
			imageView.translateYProperty().bind(decalMapY);
		}

		System.out.println("test");
		mainAnchorPane.getChildren().addAll(ar);
		//mainAnchorPane.getChildren().clear();
		//map.getTexture().get(1).relocate(100, 100);
//		app.Main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//			public void handle(KeyEvent event) {
//				System.out.println("test");
//				
//			}
//		});
		
		   
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
				System.out.println("top");
			}
			else if(code.equals(KeyCode.LEFT)) {
				System.out.println("top");
			}
			else if(code.equals(KeyCode.RIGHT)) {
				System.out.println("top");
			}
			else if(code.equals(KeyCode.DOWN)) {
				System.out.println("top");
			}
		
			}catch(IllegalArgumentException e) {
			}
		
	}
	
	public void MewMap(int x, int y) {
		decalMapX.set(x*32+decalMapX.get()); 
		decalMapY.set(y*32+decalMapY.get()); 
	}
	@FXML
	public void loadMap() {
		
	}
	

	
	
//	private EventHandler<KeyEvent> keyListener = new EventHandler<KeyEvent>() {
//		    public void keyReleased(KeyEvent event) {
//
//	    		System.out.println("test");    
//		    	if(event.getCode() == KeyCode.SPACE) {
//		    		System.out.println("test");
//		    		img.setTranslateX(img.getTranslateX()+10);
//		    	}
//		    	event.consume();
//		    }
//	    };
//	    @FXML
//		private void testKey(KeyEvent event) {
//	    	System.out.println("test");
//
//
//		img.setImage(textu.getImg(2));
//	}


}
