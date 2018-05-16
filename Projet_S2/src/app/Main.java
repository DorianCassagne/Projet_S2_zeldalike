package app;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        FXMLLoader loader = new FXMLLoader();
        URL url=null;
		try {
			url = new File("src/vue/src/GuiView.fxml").toURI().toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new Error("Error path to fxml file");
		}
        loader.setLocation(url);
        BorderPane root = new BorderPane(); 
        try {
			root=loader.load();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Error loading javaFX");
		} 

        //Controleur controller= loader.getController();

        Scene  scene = new Scene(root,600,400);

        //controller.startScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        
       
    }
}

	
