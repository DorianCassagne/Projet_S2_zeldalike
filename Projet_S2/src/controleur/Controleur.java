package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Game;
import vue.Vue;

public class Controleur implements Initializable{
	
	@FXML
	private AnchorPane mainAnchorPane;
	
	private Game game;
	private Vue vue;
	private StringProperty KeyboardInput;
	private boolean running;
	private IntegerProperty countKeys;
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
	
    public static void playGameLoop() {
		GameLoop.play();
	}
	
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.game=new Game();
		this.running=true;
		this.vue=new Vue(this.game.getChangedList(), mainAnchorPane);
		Controleur.addKeyGameLoop(e -> this.turn());
		Controleur.loadGameLoop();
	}
	
	public void turn() {
		System.out.println("echo");
	}
	
	public void startScene(Scene scene) {
		scene.setOnKeyPressed(
			e -> handleKey(e)
		);
	}
	
	
	private void handleKey(KeyEvent event) {
		KeyCode code = event.getCode();
		try {
			if(code.equals(KeyCode.UP)) {
				KeyboardInput.set("up");
				//System.out.println("uppp");
			}
			else if(code.equals(KeyCode.LEFT)) {
				KeyboardInput.set("left");
			}
			else if(code.equals(KeyCode.RIGHT)) {
				KeyboardInput.set("right");
			}
			else if(code.equals(KeyCode.DOWN)) {
				KeyboardInput.set("down");
			}
			else if(code.equals(KeyCode.S)) {
				if(running) {
					Controleur.pauseGameLoop();
					running=!running;
				}
				else {
					Controleur.playGameLoop();
					running=!running;
				}
			}
			}catch(IllegalArgumentException e) {
				
			}
		
		
	}
	
}
