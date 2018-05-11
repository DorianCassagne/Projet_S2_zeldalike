package controleur;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import model.Case;
import model.Game;
import model.Map;
import model.MapReader;

public class Controleur implements Initializable{
	
	@FXML private TilePane mapPane;
	@FXML private AnchorPane underMapPane;
	private final static String MAINIMAGE = "pokemonTiles.png";
	private final static String MAPFILENAME = "map.csv";
	
	private ImageView[][] tileMapImage;
	private Map map;
	public Controleur() {
		this.map = new Map(MAPFILENAME);
	}

	
	
	
	public void initialiseCurrentMap() {
		Image image = new Image(Game.IMAGESPATH + MAINIMAGE);
		PixelReader imageReader = image.getPixelReader();
		tileMapImage = new ImageView[this.map.getMapHeight()][this.map.getMapWidth()];
		
		mapPane.setPrefRows(this.map.getMapHeight());
		mapPane.setPrefRows(this.map.getMapWidth());
		WritableImage sizedImage;
		for(int i = 0 ; i < tileMapImage.length;i++) {
			for(ImageView viewer : tileMapImage[i]) {
				 image = getImageForCase(i,j,imageReader);
				 viewer = new ImageView(image);
				 this.mapPane.getChildren().add(viewer);
			}
		}

	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}
	
	
	private Image getImageForCase(int row,int column,PixelReader imageReader) {
		Case caseMap = this.map.getCase(row, column);
		int xi = caseMap.getBackground().getTile()[0] ;
		int yi = caseMap.getBackground().getTile()[1];
		int xf = xi + MapReader.TILESDIMENSION;
		int yf = yi + MapReader.TILESDIMENSION;
		return new WritableImage(imageReader,xi,yi,xf,yf);
	}

}
