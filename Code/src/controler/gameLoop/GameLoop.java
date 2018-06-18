/*
 * Classe GameLoop (Boucle de Jeu)
 * permet de determiner le rythme/ l'affichage du jeu 
 */

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
	
	public final static int FRAMEDURATION = 10;
	public final static int FADEDURATION = 200;
	
	private Timeline gameLoop ;							
	private HashMap<Integer,MovableView> movableList;	//liste des Movables 
	private ControlerEncoder workingData;				//Contient les informations relatives au Heros
	private boolean isStopped;							//statut de la gameloop
	
	
	/*
	 * Instancie la gameLoop
	 * @param StringProperty messageZone : la propriete qui contiendra tous les messages generess dans le jeu
	 * @param ControlerEncoder data : Contient les informations necessaires au deroulement continu du jeu
	 * 
	 * Sans exception
	 */
	
	public GameLoop(StringProperty messageZone,ControlerEncoder data) {

		this.movableList = new HashMap<Integer,MovableView>();
		this.gameLoop = new Timeline();
		this.workingData = data;
		this.isStopped=false;
		initialiseLoop();

	}
		
	/*
	 * demarre la gameloop
	 */
	public void start() {
		this.gameLoop.play();
		this.isStopped=false;
	}
	
	/*
	 * Arrete la gameLoop
	 */
	public void stop() {
		this.gameLoop.stop();
		this.isStopped=true;
	}
	

	
	/*
	 * Retourne le statut de la gameLoop
	 * 
	 * @return boolean : - Retourne vrai si la gameLoop est en arret
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
	 * Dans cette methode on definit : 
	 * -> Le nombre de cycle de gameLoop : infini.
	 * -> L'action a execute pendant chaque tour.
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
	 * L'execution prevu pour chaque tour : 
	 * -Si le jeu s'arrete alors on arrete la gameLoop 
	 * -On affiche les nouveaux personnages crees
	 * -On supprime les personnages qui sont mort
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
	 *  @param int[] playersId : le tableau contenant tous les identifiants qui vont etre supprimees
	 * 
	 *  La suppression d'un ele©ment se fait par une animation (Fade) 
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
	 * Fait jouer un tour a  tous les elements visuels
	 */
	private void allTick() {
		for(MovableView viewMovabe : this.movableList.values()) {
			viewMovabe.tick();
		}
	}
	
	/*
	 * Joue la liste des mouvements effectues au model : Deplacements
	 * 
	 * @param Move[] moves : la liste des mouvements effectues (verifiez la classe Move)
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
	 * Ajoute une liste de joueurs a la vue
	 * 
	 * @param NewMovable[] newPlayers: la liste des movables a synchroniser visuellement
	 *
	 */
	private void addPlayers(NewMovable[] newPlayers) {
		for(NewMovable newPlayer : newPlayers) {
			addMovable(newPlayer);
		}
	}
	
	
	/*
	 * Ajoute un movable unique a la vue en creant l'instance visuelle convenable (Hero ou autre)
	 *  
	 * @param NewMovable newCharacter : L'element a afficher
	 * 
	 */
	private void addMovable(NewMovable newMovable) {
		MovableView newMovableView ;

		if(newMovable != null) {
			if(newMovable.getKey() == Game.HEROKEY) 
				newMovableView = new HeroView(newMovable.getCellId(),newMovable.getImageValue(),this.workingData);
			else
				newMovableView = new MovableView(newMovable.getCellId(),newMovable.getImageValue());
			this.addToMovableList(newMovableView,newMovable.getKey());
		}
	}
	
	
	/*
	 * Ajoute un movable a  la liste des elements affichables
	 */
	private void addToMovableList(MovableView movable,Integer movableId) {
		this.workingData.getCharacterAnchorPane().getChildren().add(movable);
		this.movableList.put(movableId, movable);
	}

	
}
