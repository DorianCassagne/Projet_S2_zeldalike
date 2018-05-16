package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.util.Duration;
import model.Game;
import vue.Vue;

public class Controleur implements Initializable{
	
	private Game game;
	private Vue vue;
	private static Timeline GameLoop = new Timeline();

    public static void loadGameLoop() {
        GameLoop.setCycleCount(Timeline.INDEFINITE);
        GameLoop.play();
    }
    
    private static void addKeyGameLoop(EventHandler<ActionEvent> e) {
        KeyFrame keyf = new KeyFrame(Duration.seconds(1),e);
        GameLoop.getKeyFrames().add(keyf);
    }
    
    public static void pauseGameLoop() {
		GameLoop.pause();
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.game=new Game();
		this.vue=new Vue(this.game.getChangedList());
		Controleur.addKeyGameLoop(e -> this.turn());
		Controleur.loadGameLoop();
	}
	
	public void turn() {
		System.out.println("echo");
	}
	

}
