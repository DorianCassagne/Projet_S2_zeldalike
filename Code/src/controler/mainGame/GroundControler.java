package controler.mainGame;

import java.io.IOException;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import app.Main;
import controler.Controleur;
import controler.menu.Accueil1Controler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
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
	
	public final static int ERRORSTATE = 1;
	private static final String VIEWPATH = "/vue/"; 
	public final static String FXMLPATH = "/vue/template/Ground.fxml";
	public final static int DEFAULTWIDTH = 960;
	public final static int DEFAULTHEIGHT = 640;
	//Le stackPane qui contiendra l'ensemble des éléments ajouté à la fenêtre.
	
	@FXML private StackPane mainStackPane; 
	
	private HashMap<SceneLoader,Node> child;
	private HashMap<SceneLoader,Node> parent;
	private Scene scene;
	
	public GroundControler() {
		this.child = new HashMap<SceneLoader,Node>();
		this.parent = new HashMap<SceneLoader,Node>();
	}
	
	
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.changeView(Controleur.FXMLMAINMENUPATH,null);
	}

	
	public void changeView(String  viewName,SceneLoader changer ){
		this.mainStackPane.getChildren().clear();
		addElements(viewName, changer);
	}
	
	public void addElements(String viewName,SceneLoader changer) {
		int lastIndex = this.mainStackPane.getChildren().size() - 1;
		Node parent = null;
		if(lastIndex > - 1)
			parent = this.mainStackPane.getChildren().get(lastIndex);
		this.addElement(viewName, changer,parent);
	}
	
	private void addElement(String viewName,SceneLoader parent,Node parentSource) {
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource(VIEWPATH + viewName));
			AnchorPane newView = loader.load();
			newView.setPrefHeight(DEFAULTHEIGHT);
			newView.setPrefWidth(DEFAULTWIDTH);
			SceneLoader sceneLoader = loader.getController();
			sceneLoader.loadScene(this);
			this.mainStackPane.getChildren().add(newView);
			
			this.parent.put(sceneLoader,parentSource);
			this.child.put(parent,newView);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void delElements(int nbView) {
		this.mainStackPane.getChildren().remove(nbView);
	}
	
	public void remove(SceneLoader loader) {
		
	}
	
	
	public void setScene(Scene scene) {
		if(scene != null)
			this.scene = scene;
		else
			throw new IllegalArgumentException("ERROR IN GROUND ");
	}
	
	public Scene getScene() {
		return this.scene;
	}
	

	
	
	private void replaceTo(Node newAnchor) {
		if(newAnchor != null) {
			this.mainStackPane.getChildren().clear();
			this.mainStackPane.getChildren().add(newAnchor);

		}

	}
	
	public void loadParent(SceneLoader loader) {
		Node parent = null;
		if(loader != null)
			parent = this.parent.get(loader);
	
		this.replaceTo(parent);
	}
	
	public void loadChild(SceneLoader loader) {
		Node children = null;
		if(loader != null) 
			children = this.child.get(loader);
		this.replaceTo(children);
		
	}

	
	
	
	
	
}
