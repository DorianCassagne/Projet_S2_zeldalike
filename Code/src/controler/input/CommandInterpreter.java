/*
 * Classe relative a l'interpretation des entrees du clavier
 * Associe une action a une touche du clavier
 */
package controler.input;

import controler.Controleur;
import controler.gameLoop.GameLoop;
import controler.mainGame.GroundControler;
import javafx.beans.property.BooleanProperty;
import javafx.scene.input.KeyEvent;
import model.Game;
import model.character.hero.Hero;

public class CommandInterpreter {
	private Game myGame;
	private GameLoop gameLoop;
	private BooleanProperty messagingProperty;

	public CommandInterpreter(Game game,GameLoop gameLoop,BooleanProperty waitingForAnswer)  {
		
		this.myGame = game;
		this.gameLoop = gameLoop;
		this.initMessageProperty(waitingForAnswer);

	}

	private void initMessageProperty(BooleanProperty waitingForMessage) {

		this.messagingProperty = waitingForMessage;
		this.messagingProperty.addListener((obs,oldValue,newValue)->{
			if(newValue) {
				gameLoop.stop();
			}
			else
				this.gameLoop.start();
		});

	}

	public void handleKey(KeyEvent event,GroundControler ground) {
		char nextMove = Hero.STAY;
		
		switch(event.getCode()) {
		case UP: 
			nextMove = Hero.ATTACKUP;
			break;
		case DOWN:
			nextMove = Hero.ATTACKDOWN ;
			break;
		case LEFT:
			nextMove = Hero.ATTACKLEFT;
			break;
		case RIGHT: 
			nextMove = Hero.ATTACKRIGHT;
			break;
		case Z :
			nextMove = Hero.MOVEUP; 
			break;
		case S :
			nextMove = Hero.MOVEDOWN; 
			break;
		case D :
			nextMove = Hero.MOVERIGHT;
			break;
		case Q : 
			nextMove = Hero.MOVELEFT;
			break;
		case E : 
			nextMove = Hero.CHANGEATTACK;
			break;
		case ESCAPE :
			this.showMenu(ground);
			break;
		case SPACE : 
			this.messagingProperty.set(false);
			break;
		case X :
			nextMove = Hero.TALK;
			break;
		default :
			System.out.println("Unknown key");
		}
		this.myGame.communiquerMovement(nextMove);


	}
	
	/*
	 * Si la gameloop n'est pas a l'arret affiche le menu de pause et stop la gameloop
	 * Si la gameloop est en arret, retire le menu de pause et active la gameloop
	 */
	private void showMenu(GroundControler ground) {
		if(this.gameLoop.getIsStopped()) {
			ground.removeLast();
			this.gameLoop.start();
		}
		else {
			ground.addElement(Controleur.FXMLPAUSEPATH);
			this.gameLoop.stop();
		}

	}
}
