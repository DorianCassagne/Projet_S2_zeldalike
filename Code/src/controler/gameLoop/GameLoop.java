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
	
	public final static int FRAMEDURATION = 7;
	public final static int FADEDURATION = 200;
	
	private Timeline gameLoop ;
	private HashMap<Integer,MovableView> movableList;
	private ControlerEncoder workingData;
	private boolean isStopped;
	
	
	/*
	 * Instancie la gameLoop
	 * @param StringProperty messageZone : la propriété qui contiendra tous les messages générés dans le jeu
	 * @param ControlerEncoder data : Contient les informations nécéssaires au déroulement continu du jeu
	 * 
	 * Sans excéption
	 */
	
	public GameLoop(StringProperty messageZone,ControlerEncoder data) {
		this.movableList = new HashMap<Integer,MovableView>();
		this.gameLoop = new Timeline();
		this.workingData = data;
		this.isStopped=false;
		initialiseLoop();

	}
		
	/*
	 * démarre la gameloop
	 */
	public void start() {
		this.gameLoop.play();
		this.isStopped=false;
	}
	
	/*
	 * Arrête la gameLoop
	 */
	public void stop() {
		this.gameLoop.stop();
		this.isStopped=true;
	}
	

	
	/*
	 * Retourne le status de la gameLoop
	 * 
	 * @return boolean : - Retourne vrai si la gameLoop est en arrêt
	 *					 - Retourne faux si la gameloop est en marche
	 */
	public boolean getIsStopped() {
		return isStopped;
	}
	
	/*
	 * Private Methods
	 */
	
	/*
	 * Initialise la gameLoop
	 * Dans cette méthode on définit : 
	 * -> Le nombre de cycle de gameLoop : infini.
	 * -> L'action a exécuté pendant chaque tour.
	 */
	
	private void initialiseLoop() {
		this.gameLoop.setCycleCount(Timeline.INDEFINITE);
		KeyFrame frame = new KeyFrame(
				Duration.millis(FRAMEDURATION),
				(ev->turn())
		);
		this.gameLoop.getKeyFrames().add(frame);
	}
	
	/*
	 * L'exécution prévu chaque tour : 
	 * -Si le jeu s'arrête alors on arrête la gameLoop 
	 * -On affiche les nouveaux personnages crées
	 * -On supprime les personnages qui sont meurts
	 * -On joue la liste des mouvement
	 */
	private void turn() {
		
		if(this.workingData.getMyGame().end()) {
			gameLoop.stop();
			//On affiche le menu du gameOver
			this.workingData.getGround().addElement(Controleur.FXMLGAMEOVERMENUPATH);
		}
		else {
			addPlayers(this.workingData.getMyGame().getNewPlayers());
			removePlayers(this.workingData.getMyGame().getRemovedMovable());
			playMoves(this.workingData.getMyGame().turn());			
			allTick();
		}
	}
	
	/*
	 * Supprime les joueurs au niveau visuel :
	 * 
	 *  @param int[] playersId : le tableau contenant tous les identifiants qui vont être supprimées
	 * 
	 *  La suppréssion d'un élément se fait par une animation (Fade)
	 */
	
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
	
	
	/*
	 * Fait jouer un tour à tous les éléments visuels
	 */
	private void allTick() {
		for(MovableView viewMovabe : this.movableList.values()) {
			viewMovabe.tick();
		}
	}
	
	/*
	 * Joue la liste des mouvements effectués au model : Déplacements
	 * 
	 * @param Move[] moves : la liste des mouvements effectués (vérifiez la classe Move)
	 * 
	 * 
	 */
	private void playMoves(Move[] moves ) {
		for (Move move : moves) {
				MovableView movable = this.movableList.get(move.getMovableId());
				if(movable != null)
					movable.moveTo(move.getEndCellId(),move.getSpeed());
		}
		
		
	}
	
	/*
	 * Ajoute une liste de joueurs à la vue
	 * 
	 * @param NewMovable[] newPlayers: la liste des movables à synchroniser visuellement
	 *
	 */
	private void addPlayers(NewMovable[] newPlayers) {
		for(NewMovable newPlayer : newPlayers) {
			addMovable(newPlayer);
		}
	}
	
	
	/*
	 * Ajoute un movable unique à la vue en créant l'instance visuelle convenable (Héro ou autre)
	 *  
	 * @param NewMovable newCharacter : L'élément à afficher
	 * 
	 */
	private void addMovable(NewMovable newMovable) {
		MovableView newMovableView ;
		System.out.println("MY iD : " + newMovable.getKey() + " Me : "+newMovable);
		if(newMovable != null) {
			if(newMovable.getKey() == Game.HEROKEY) 
				newMovableView = new HeroView(newMovable.getCellId(),newMovable.getImageValue(),this.workingData);
			else
				newMovableView = new MovableView(newMovable.getCellId(),newMovable.getImageValue());
			this.addToMovableList(newMovableView,newMovable.getKey());
		}
	}
	
	
	/*
	 * Ajoute un movable à la liste des éléments affichables
	 */
	private void addToMovableList(MovableView movable,Integer movableId) {
		this.workingData.getCharacterAnchorPane().getChildren().add(movable);
		this.movableList.put(movableId, movable);
	}

	
}
