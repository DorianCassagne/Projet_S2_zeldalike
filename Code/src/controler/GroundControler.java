package controler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class GroundControler implements Initializable {
	
	@FXML 
	private StackPane mainAnchorPane; 
	private static final String VIEWPATH = "/vue/"; 
	
	public GroundControler() {

	}
	
	
	public void changeView(String ... viewNames){
		
		this.mainAnchorPane.getChildren().clear();
		for(String viewName : viewNames) {
			this.addElements(viewName);
		}
	}
	
	private void addElements(String viewName) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(VIEWPATH + viewName));
			AnchorPane newView = loader.load();
			this.mainAnchorPane.getChildren().add(0,newView);
		} catch (IOException e) {
			
		}

	}
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("");
		this.changeView("menu/MenuAccueil.fxml");
	}
	
	
	
}
