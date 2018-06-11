package controler.mainGame;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import app.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

/*
 * Cette classe représente d'une part le controleur de Ground.fxml et d'autre part
 * les éléments affichables dans la fenêtre du jeu et qui sont lu à partir des fichiers fxml
 * Responsabilités : -> Changer totalement les éléments de la fenêtre
 * 					 -> Charger 
 * 					 ->
 */

public class GroundControler implements Initializable {
	/*
	 * Variables statiques : 
	 * VIEWPATH : le chemin vers tous les fichiers du package de la vue.
	 * FXMLPATH : le chemin vers le fichier dont GroundControler est un controleur.
	 */
	
	private static final String VIEWPATH = "/vue/"; 
	public final static String FXMLPATH = "/vue/template/Ground.fxml";
	
	//Le stackPane qui contiendra l'ensemble des éléments ajouté à la fenêtre.
	@FXML private StackPane mainAnchorPane; 
	
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
