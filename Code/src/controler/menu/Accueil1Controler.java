/*package controler.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import controler.GameLoop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Accueil1Controler implements Initializable {
	
		
    @FXML
    private Button NewGameButton;
    @FXML
    private Button LoadButton;
    @FXML
    private Button LeaveButton;
    
    public Accueil1Controler () {
    	
    }
    
    @FXML
    void createNewGame(ActionEvent event) {
    	
    }

    @FXML
    void leave(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void loadGame(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader();
		try {
			loader.setLocation(Main.class.getResource("/vue/menu/MenuChargerMap.fxml"));
			AnchorPane root = loader.load();
			this.mainAnchorPane.getChildren().clear();
			this.mainAnchorPane.getChildren().add(root);
		} catch (IOException e) {
			
		}
    }

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    }
}
*/