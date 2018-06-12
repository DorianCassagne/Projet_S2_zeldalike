package controler.menu;

import controler.Controleur;
import controler.mainGame.GroundControler;
import controler.mainGame.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class PauseControleur implements SceneLoader{


	private GroundControler ground;


	@FXML
	void exit(ActionEvent event) {
		System.exit(1);
	}

	@FXML
	void loadMenuGame(ActionEvent event) {
		if(ground != null) {
    		ground.changeView(Controleur.FXMLLOADMENUPATH);
		}
    	else
    		System.exit(1);
	}

	@FXML
	void resumeGame(ActionEvent event) {
		this.ground.removeLast();
		this.ground.startGameLoop();
	}

	@FXML
	void saveGame(ActionEvent event) {
		
	}

	@Override
	public void loadScene(GroundControler controller) {
		this.ground = controller;

	}


}
