package controler.gameLoop;

import java.util.HashMap;


import controler.Controleur;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.StringProperty;
import javafx.util.Duration;
import model.Game;
import model.gameMap.additional.NewMovable;
import model.gameMap.move.Move;
import vue.gameClass.HeroView;
import vue.gameClass.MovableView;

public class GameLoop {
	
	public final static int FRAMEDURATION = 5;
	public final static int FADEDURATION = 200;
	
	private Timeline gameLoop ;
	private HashMap<Integer,MovableView> movableList;
	private ControlerEncoder workingData;
	private boolean isStopped;
	
	public GameLoop(StringProperty messageZone,ControlerEncoder data) {
		this.movableList = new HashMap<Integer,MovableView>();
		this.gameLoop = new Timeline();
		this.workingData = data;
		this.isStopped=false;
		initialiseLoop();

	}
	
	/*Public Methods*/
	public void start() {
		this.gameLoop.play();
		this.isStopped=false;
	}
	
	
	public boolean getIsStopped() {
		return isStopped;
	}

	public void stop() {
		this.gameLoop.stop();
		this.isStopped=true;
	}
	
	
	/*
	 * Private Methods
	 */
	private void initialiseLoop() {
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(
				Duration.millis(FRAMEDURATION),
				(ev->turn())
		);
		this.gameLoop.getKeyFrames().add(frame);
	}
	
	private void turn() {
		
		if(this.workingData.getMyGame().end()) {
			gameLoop.stop();
			this.workingData.getGround().addElement(Controleur.FXMLGAMEOVERMENUPATH);
		}
		else {
			addPlayers(this.workingData.getMyGame().getNewPlayers());
			removePlayers(this.workingData.getMyGame().getRemovedMovable());
			playMoves(this.workingData.getMyGame().turn());			
			allTick();
		}
	}
	
	private void removePlayers(int[] playersId) {
		FadeTransition ft;
		for(Integer playerId : playersId) {
		    ft = new FadeTransition(Duration.millis(FADEDURATION));
		    ft.setFromValue(1.0);
			MovableView current = this.movableList.get(playerId);
			ft.setToValue(0);
			ft.setNode(current);
			ft.playFromStart();
			this.movableList.remove(playerId);
			ft.setOnFinished(e->this.workingData.getCharacterAnchorPane().getChildren().remove(current));
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
			addMovable(newPlayer);
		}
	}
	
	private void addMovable(NewMovable newCharacter) {
		MovableView newMovable ;
		if(newCharacter != null) {
			if(newCharacter.getKey() == Game.HEROKEY) 
				newMovable = new HeroView(newCharacter.getCellId(),newCharacter.getImageValue(),this.workingData);
			else
				newMovable = new MovableView(newCharacter.getCellId(),newCharacter.getImageValue());
			this.addToMovableList(newMovable,newCharacter.getKey());
		}
	}
	
	private void addToMovableList(MovableView movable,Integer movableId) {
		this.workingData.getCharacterAnchorPane().getChildren().add(movable);
		this.movableList.put(movableId, movable);
	}

	
}
