package controleur;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.GameMap;
import texture.TexturePack;

public class Controleur implements Initializable{

	 @FXML
	    private Button butO;

	@FXML
	private AnchorPane mainAnchorPane;

	@FXML
	ImageView img;
	static TexturePack textu;
	private DoubleProperty decalMapX;

	private DoubleProperty decalMapY;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		textu = new TexturePack("img/tile.png", 8, 32);
		
		
		
		decalMapX = new SimpleDoubleProperty();
		decalMapY = new SimpleDoubleProperty();
		
//		img = new ImageView();
//		img.setImage(textu.getImg(362));
//		mainAnchorPane.getChildren().add(img);
//		img.relocate(10, 10);
//		img.setTranslateX(100);
		int[][] tab = {{1115,1123,1131},{1116,1124,1132},{1117,1125,1133}};
		GameMap map =new GameMap( tab );/*, {{94,94,94},{94,94,94},{94,94,94}} , {null,null,null} */
			
		ArrayList<ImageView> ar= map.getTexture();
		for (ImageView imageView : ar) {
			imageView.translateXProperty().bind(decalMapX);
			imageView.translateYProperty().bind(decalMapY);
		}
		mainAnchorPane.getChildren().addAll(map.getTexture());
//		app.Main.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
//
//			public void handle(KeyEvent event) {
//				System.out.println("test");
//				
//			}
//		});
		   
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

		@FXML
		private void test(ActionEvent event) {
		img.setTranslateX(10+img.getTranslateX());
		

		img.setImage(textu.getImg(2));
	}
}
