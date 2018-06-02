package controler;


import javafx.scene.input.KeyEvent;
import model.Game;
import model.character.Hero;

public class CommandInterpreter {
	private Game myGame;
	private GameLoop gameLoop;
	
	public CommandInterpreter(Game game,GameLoop gameLoop)  {
		this.myGame = game;
		this.gameLoop = gameLoop;
	}
	
	void handleKey(KeyEvent event) {
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
		case B :
			this.gameLoop.stop();
		default :
			System.out.println("Unknown key");
		}
		this.myGame.communiquerMovement(nextMove);


	}
}
