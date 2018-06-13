package app;


import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import controler.mainGame.GroundControler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

	private final static String STARTVIEW = GroundControler.FXMLPATH;
	//private final static String STARTVIEW = "/vue/" + Controleur.FXMLPATH;
	
    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader();
        URL url = null;
        StackPane root = null; 
		
        try {
			url = Main.class.getResource(STARTVIEW).toURI().toURL();
	        loader.setLocation(url);
		} catch (MalformedURLException e) {
			throw new Error("ERROR WHILE READING FXML FILE");
		}catch (URISyntaxException e) {
			throw new Error("FXML FILE IS IN BAD STATE");
		}

        try {
			root = loader.load();
		} catch (IOException e) {
			throw new Error("ERROR LOADING JAVAFX");
		} 
        

        Scene  scene = new Scene(root,GroundControler.DEFAULTWIDTH,GroundControler.DEFAULTHEIGHT);
        GroundControler ground = loader.getController();
        ground.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

	
