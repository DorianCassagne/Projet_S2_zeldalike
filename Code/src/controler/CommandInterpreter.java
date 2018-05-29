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
		switch(event.getCode()) {
		case UP : 
			this.myGame.communiquerMovement(Hero.MOVEUP);
			break;
		case DOWN:
			this.myGame.communiquerMovement(Hero.MOVEDOWN);
			break;
		case LEFT:
			this.myGame.communiquerMovement(Hero.MOVELEFT);
			break;
		case RIGHT: 
			this.myGame.communiquerMovement(Hero.MOVERIGHT);
			break;
		case A :
			this.myGame.communiquerMovement(Hero.ATTACK);
			break;
		case B :
			this.gameLoop.stop();
		default :
			System.out.println("Unknown key");
		}

	}
}
