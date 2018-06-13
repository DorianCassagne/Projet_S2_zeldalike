package controller;

import java.io.IOException;
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
        MapLoader mapLoader = launchMapLoder(map,texture);
        launchConditionAddController(mapLoader);
        
    }
    
    private static MapLoader launchMapLoder(GameMap map,TexturePack texture) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        Stage s = new Stage();
        loader.setLocation(Main.class.getResource(MapLoader.PATHFXML));
        Parent p = loader.load();
        Scene scene = new Scene(p,MapLoader.MAPLENGTH,MapLoader.MAPLENGTH);
        MapLoader mapLoader = loader.getController();
        mapLoader.setMap(map);
        mapLoader.startScene(scene);
        mapLoader.setStage(s);
        mapLoader.setTexturePack(texture);
        s.setResizable(false);
        s.setScene(scene);        
        mapLoader.launch();
        return mapLoader;

    }
    
    private static void launchConditionAddController(MapLoader mapLoader) throws IOException {
    	FXMLLoader loader = new FXMLLoader();
        Stage s = new Stage();
        loader.setLocation(Main.class.getResource("/view/SceneCreation.fxml"));
        Parent p = loader.load();
        Scene scene = new Scene(p,1100,700);
        ConditionAddControler controller = loader.getController();
        controller.setMapLoader(mapLoader);
        s.setScene(scene);
        s.show();
        

    }

}
