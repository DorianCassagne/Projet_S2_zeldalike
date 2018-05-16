package vue;

import java.util.ArrayList;

import controleur.Movement;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class Vue {
	private AnchorPane mainAnchorPane;
	private Pane map;
	private DoubleProperty decalMapX;
	private DoubleProperty decalMapY;
	TexturePack texturePack ;

	public Vue(IntegerProperty[] changeProperty ,AnchorPane mainAnchorPane) {
		texturePack= new TexturePack(VueConst.TILEMAPPATH, VueConst.TILEMAPWHIDTH, VueConst.TILESIZE);
		this.mainAnchorPane=mainAnchorPane;
		map=new Pane();
//		for (int i = 0; i < 20; i++) {
//			ImageView img= new ImageView(texturePack.getImg(i));
//			img.relocate(i*32, i*32);
//			map.getChildren().add(img);
//			
//		}
		mainAnchorPane.getChildren().add(map);
	}
	
	public void refresh(ArrayList<Movement> movementList) {
	}
	
}
