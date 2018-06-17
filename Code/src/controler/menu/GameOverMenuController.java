/*
 * Controleur du menu de GameOver 
 * qui permet de : 	- Rejouer
 * 					- Afficher le menu principale
 * 					- Naviguer dans le menu de chargement de sauvegarde
 * 					- Quittez le jeu 
 */
package controler.menu;

import controler.Controleur;
import controler.mainGame.GroundControler;
import controler.mainGame.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class GameOverMenuController implements SceneLoader {
	
	private GroundControler ground;
	
	@Override
	public void loadScene(GroundControler controller) {
		this.ground = controller;
	}
	
	@FXML
    private void showMainMenu(ActionEvent event) {
		if(ground != null)
    		ground.changeView(Controleur.FXMLMAINMENUPATH);
    	else
    		System.exit(1);
    }
	
	@FXML
    private void exit(ActionEvent event) {
    	System.exit(1);
    }
	
	@FXML
    private void createNewGame(ActionEvent event) {
    	if(ground != null) {
    		ground.changeView(Controleur.FXMLGAMEPATH);
    	}else
    		System.exit(1);
    		
    }

}
