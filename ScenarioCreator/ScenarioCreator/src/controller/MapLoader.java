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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import model.gameMap.Conversion;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import view.MapView;
import view.TexturePack;

public class MapLoader implements Initializable{
	
	public  static TexturePack TEXTURE ;
	public static final String PATHFXML = "/view/Map.fxml";
	public final static int MAPLENGTH = 768;
	public final static double TILEPREFDIMENSION = MAPLENGTH/MapReader.MAPLENGTH;
	public final static double TILESCALE = TILEPREFDIMENSION/FileUploader.TILEDIMENSION;
	
	@FXML private TilePane newMap;
    
	private MapView myMapView;
	private GameMap map;
	private Scene scene;
	private Stage stage;
	private int cellId;
	
	public void setTexturePack(TexturePack texture) {
		TEXTURE = texture;
	}
	
	
	public void setStage(Stage stage) {
		if(this.stage == null)
			this.stage = stage;
		else
			throw new IllegalArgumentException("This can only have one Stage");
	}
	
	public void setMap(GameMap map ) {
		if(this.map == null)
			this.map = map;
		else
			throw new IllegalArgumentException("This map must be assigned Once");
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
		
	public void startScene(Scene scene) {
		this.scene = scene;
		this.scene.setOnMouseClicked(e->setCurrentCell(e));

	}
		
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTile();

	}
	
	public int getCurrentCell() {
		this.stage.showAndWait();
		return this.cellId;
	}
	
	private void setCurrentCell(MouseEvent event) {
		Double row = event.getX() / MapLoader.TILEPREFDIMENSION;
		Double column = event.getY() / MapLoader.TILEPREFDIMENSION;
		this.cellId = Conversion.convertToCellId(row.intValue(), column.intValue());
		this.stage.close();
	}

	
	
	
}
