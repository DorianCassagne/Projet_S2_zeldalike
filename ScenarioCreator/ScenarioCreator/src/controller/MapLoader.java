package controller;

import java.net.URL;





import java.util.ResourceBundle;
import java.util.function.Function;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.TilePane;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import view.MapView;
import view.TexturePack;

public class MapLoader implements Initializable{
	
	public  static TexturePack TEXTURE ;
	public final static int MAPLENGTH = 768;
	public final static double TILEPREFDIMENSION = MAPLENGTH/MapReader.MAPLENGTH;
	public final static double TILESCALE = TILEPREFDIMENSION/FileUploader.TILEDIMENSION;
	
	@FXML private TilePane newMap;
    
	private MapView myMapView;
	private GameMap map;
	private IntegerProperty changeProperty;
	private Scene scene;
	
	public void setTexturePack(TexturePack texture) {
		TEXTURE = texture;
		
	}
	
	public MapLoader() {
		this.changeProperty = new SimpleIntegerProperty();
	}
	
	public void setMap(GameMap map ) {
		this.map = map;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTile();
	}
	
	public void launch() {
		this.createMap();
	}
	
	private void initTile() {
		this.newMap.setPrefColumns(MapReader.MAPLENGTH);
		this.newMap.setPrefRows(MapReader.MAPLENGTH);
		this.newMap.setPrefWidth(MAPLENGTH);
		this.newMap.setPrefHeight(MAPLENGTH);
		this.newMap.setPrefTileHeight(TILEPREFDIMENSION);
		this.newMap.setPrefTileWidth(TILEPREFDIMENSION);
	}
	
	private void createMap() {

		Function< Integer,Integer[]> backgroundSource = element->this.map.getLayerForCell(element);
		this.myMapView = new MapView(backgroundSource,this.newMap);
		this.myMapView.initialise();
	}
	
	public final  IntegerProperty changeProperty() {
		return this.changeProperty;
	}
	
	public void startScene(Scene scene) {
		this.scene = scene;
	}
	

	
	
	
	
}
