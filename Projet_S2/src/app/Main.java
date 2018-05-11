package app;

import java.io.File;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {

        FXMLLoader loader = new FXMLLoader();
        URL url = new File("src/gui/GuiView.fxml").
        toURI().toURL();
        loader.setLocation(url);
        BorderPane root ; 
        root=loader.load(); 
        Scene scene = new Scene(root,480,432);
        primaryStage.setScene(scene);
        primaryStage.show();
       } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

	
