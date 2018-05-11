package controleur;

import java.net.URL;

import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import model.Background;
import model.Game;
import model.Map;
import model.MapReader;

public class Controleur implements Initializable{
	
	@FXML private TilePane mapPane;
	@FXML private AnchorPane underMapPane;
	@FXML private ImageView testView;
	private final static Image MAINIMAGE = new Image(Game.IMAGESPATH + "pokemonTiles.png");
	private final static String MAPFILENAME = "map.csv";
	private ImageView[][] tileMapImage;
	
	private Map map;
	public Controleur() {
		this.map = new Map(MAPFILENAME);
	}

	
	
	
	public void initialiseCurrentMap() {
		int maxHeight = this.map.getMapHeight();
		int maxWidth = this.map.getMapWidth();
		tileMapImage = new ImageView[maxHeight][maxWidth];
		mapPane.setPrefRows(maxHeight);
		mapPane.setPrefColumns(maxWidth);
		this.mapPane.setMinHeight(maxHeight*MapReader.TILESDIMENSION);
		this.mapPane.setMaxHeight(maxHeight*MapReader.TILESDIMENSION);
		this.mapPane.setMinWidth(maxWidth*MapReader.TILESDIMENSION);;
		this.mapPane.setMaxWidth(maxWidth*MapReader.TILESDIMENSION);
	
		for(int i = 0 ; i < tileMapImage.length;i++) {
			for(int j = 0 ; j < tileMapImage[i].length;j++) {
				this.tileMapImage[i][j]  = new ImageView(MAINIMAGE);
				 setCaseImage(i,j);
				 this.mapPane.getChildren().add(tileMapImage[i][j]);
			}
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.mapPane.setPrefTileHeight(MapReader.TILESDIMENSION);
		this.mapPane.setPrefTileWidth(MapReader.TILESDIMENSION);
		initialiseCurrentMap();
	}
	
	
	private void setCaseImage(int row,int column) {
		Background caseBackground = this.map.getBackground(row, column);
		int xi = caseBackground.getTile()[0] * MapReader.TILESDIMENSION ;
		int yi = caseBackground.getTile()[1] * MapReader.TILESDIMENSION;
		Rectangle2D rectangle = new Rectangle2D(xi, yi, MapReader.TILESDIMENSION, MapReader.TILESDIMENSION);
		this.tileMapImage[row][column].setViewport(rectangle);
	}

}
