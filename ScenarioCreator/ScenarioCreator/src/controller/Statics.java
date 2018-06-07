package controller;

import java.util.function.Consumer;

import app.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.gameMap.GameMap;
import view.TexturePack;

public class Statics {
    public static final  void newMap(GameMap map,TexturePack texture,Consumer<Integer> consumer) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Stage s = new Stage();
        loader.setLocation(Main.class.getResource("/view/Map.fxml"));
        Parent p = loader.load();
        Scene scene = new Scene(p,MapLoader.MAPLENGTH,MapLoader.MAPLENGTH);
        MapLoader mapLoader = loader.getController();
        mapLoader.setMap(map);
        mapLoader.startScene(scene);
        mapLoader.setTexturePack(texture);
        s.setResizable(true);
        s.setScene(scene);
        if(consumer != null)
        	mapLoader.changeProperty().addListener((obs,oldValue,newValue)->consumer.accept(newValue.intValue()));
        
        mapLoader.launch();


        s.showAndWait();
    }
    

}
