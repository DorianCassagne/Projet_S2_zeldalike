package controler;

import java.util.HashMap;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import model.Game;
import model.gameMap.additional.NewMovable;
import model.gameMap.move.Move;
import vue.HeroView;
import vue.MovableView;

public class GameLoop {
	
	public final static int FRAMEDURATION = 17;
	
	private Timeline gameLoop ;
	private Game myGame;
	private HashMap<Integer,MovableView> movableList;
	private AnchorPane characterAnchorPane;
	private Label HPLabel ;
	private ProgressBar HPProgressBar;
	
	public GameLoop(Game myGame,AnchorPane characterAnchorPane,Label HPLabel,ProgressBar HPProgress,StringProperty messageZone) {
		this.movableList = new HashMap<Integer,MovableView>();
		this.characterAnchorPane = characterAnchorPane;
		this.myGame = myGame;
		this.gameLoop = new Timeline();
		this.HPLabel = HPLabel;
		this.HPProgressBar = HPProgress;
		initialiseLoop();

	}
	
	public void start() {
		this.gameLoop.play();
	}
	
	
	public void stop() {
		System.out.println("Stop the gameloop");
		this.gameLoop.stop();
	}
	
	
	private void initialiseLoop() {
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(
				Duration.millis(FRAMEDURATION),
				(ev->turn())
		);
		gameLoop.getKeyFrames().add(frame);
	}
	
	private void turn() {
		
		if(this.myGame.end()) {
			gameLoop.stop();
			Alert alert = new Alert(Alert.AlertType.INFORMATION,"Vous avez perdu !");
			alert.show();
		}
		else {
			addPlayers(this.myGame.getNewPlayers());
			removePlayers(this.myGame.getRemovedMovable());
			playMoves(this.myGame.turn());			
			allTick();
		}

	}
	
	private void removePlayers(int[] playersId) {
		FadeTransition ft;
		for(Integer playerId : playersId) {
		    ft = new FadeTransition(Duration.millis(200));
		    ft.setFromValue(1.0);
			MovableView current = this.movableList.get(playerId);
			ft.setToValue(0);
			ft.setNode(current);
			ft.playFromStart();
			this.movableList.remove(playerId);
			ft.setOnFinished(e->this.characterAnchorPane.getChildren().remove(current));
		}
	}
	
	private void allTick() {
		for(MovableView viewMovabe : this.movableList.values()) {
			viewMovabe.tick();
		}
	}
	
	private void playMoves(Move[] moves ) {
		for (Move move : moves) {
				MovableView movable = this.movableList.get(move.getMovableId());
				movable.moveTo(move.getEndCellId(),move.getSpeed());
		}
		
		
	}
	
	private void addPlayers(NewMovable[] newPlayers) {
		for(NewMovable newPlayer : newPlayers) {
			MovableView newMovable ;
			if(newPlayer.getKey() == Game.HEROKEY)
				newMovable = new HeroView(newPlayer.getCellId(),newPlayer.getImageValue(),this.myGame,this.characterAnchorPane,HPLabel,HPProgressBar);
			else
				newMovable = new MovableView(newPlayer.getCellId(),newPlayer.getImageValue());
			
			this.characterAnchorPane.getChildren().add(newMovable);
			this.movableList.put(newPlayer.getKey(), newMovable);
		}
		
	}
	
}
