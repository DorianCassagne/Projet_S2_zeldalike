package controller;

import java.io.File;

import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import model.gameMap.GameMap;
import view.TexturePack;

public class FileUploader {

    final static FileChooser fileExplorer;
    public final static int TILEDIMENSION = 32;
    
    @FXML
    private Label mapName;

    @FXML
    private Label tilesetName;

    private ArrayList<String> mapPath;
    private String tilesetPath;

    static {
    	fileExplorer = new FileChooser();
    	fileExplorer.setInitialDirectory(new File(System.getProperty("user.home")));
    }
    
    public FileUploader() {
    	this.mapPath = new ArrayList<String>();
    }
    
    
    @FXML
    void loadMap(ActionEvent event) {
        File chosenFile = fileExplorer.showOpenDialog(tilesetName.getScene().getWindow());
        if(chosenFile != null){
        	this.mapPath.add(chosenFile.getAbsolutePath());
        	fileExplorer.setInitialDirectory(chosenFile.getParentFile());
        	this.mapName.setText(this.mapName.getText() + " "+ chosenFile.getName());
        	this.mapName.setVisible(true);
        }
    }

    @FXML
    void loadTileset(ActionEvent event) {
    	File chosenFile = fileExplorer.showOpenDialog(tilesetName.getScene().getWindow());
    	if(chosenFile != null ) {
    		this.tilesetPath = chosenFile.getAbsolutePath();
    		this.tilesetName.setText("Tileset : "+ chosenFile.getName());
        	fileExplorer.setInitialDirectory(chosenFile.getParentFile());

    		this.tilesetName.setVisible(true);
    	}
    }
    
    @FXML
    void clearMap(ActionEvent event) {
    	this.mapPath.clear();
    	this.mapName.setText("Map : ");
    }

    
    
    @FXML
    void switchScene(ActionEvent event) {
    	String[] list = new String[this.mapPath.size()];
    
    	if(this.tilesetName != null && this.tilesetPath != null) {
    		try {
    			GameMap map = new GameMap(this.mapPath.toArray(list));
    			TexturePack texture = new TexturePack(this.tilesetPath,8,TILEDIMENSION);    	        
    			Statics.newMap(map,texture,null);
    		}catch(Exception e) {
    			e.printStackTrace();
    			Alert alert = new Alert(Alert.AlertType.ERROR,"VERIFIEZ BIEN VOS FICHIERS");
    			alert.showAndWait();
    		}
    	}
    }

}
