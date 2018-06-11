package controler.withGame;


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
		case UP : 
			nextMove = Hero.MOVEUP;
			break;
		case DOWN:
			nextMove = Hero.MOVEDOWN;
			break;
		case LEFT:
			nextMove = Hero.MOVELEFT;
			break;
		case RIGHT: 
			nextMove = Hero.MOVERIGHT;
			break;
		case Z :
			nextMove = Hero.ATTACKUP;
			break;
		case S :
			nextMove = Hero.ATTACKDOWN ;
			break;
		case D :
			nextMove = Hero.ATTACKRIGHT;
			break;
		case Q : 
			nextMove = Hero.ATTACKLEFT;
			break;
		case ESCAPE :
			if(this.gameLoop.getIsStopped()) {
				this.gameLoop.start();
			}
			else {
				this.gameLoop.stop();
			}
			break;
		case E : 
			nextMove = Hero.CHANGEATTACK;
			break;
		case T : 
			this.messagingProperty.set(false);
			break;
		default :
			System.out.println("Unknown key");
		}
		this.myGame.communiquerMovement(nextMove);


	}
}
