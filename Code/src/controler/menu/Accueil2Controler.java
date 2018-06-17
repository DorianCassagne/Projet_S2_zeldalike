/*
 * Controleur du Menu d'accueil2 qui s'affiche lorsqu'une partie a deja ete sauvegarde
 * possibilité de : - Continuer la partie precedente
 * 					- Creer une nouvelle partie
 * 					- Charger le menu de chargement de partie
 * 					- Quitter le jeu
 */
package controler.menu;

import controler.Controleur;
import controler.mainGame.GroundControler;
import controler.mainGame.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Accueil2Controler implements SceneLoader{

	private GroundControler ground;
	
    @FXML
    private void newGame(ActionEvent event) {
    	if(ground != null)
    		ground.changeView(Controleur.FXMLGAMEPATH);
    	else
    		System.exit(1);
    }
    
    @FXML
    private void exit(ActionEvent event) {
    	System.exit(1);
    }
    
    @FXML
    private void loadMenuGame(ActionEvent event) {
    	if(ground != null) {
    		ground.changeView(Controleur.FXMLLOADMENUPATH);
    	}
    	else
    		System.exit(1);
    }

	@Override
	public void loadScene(GroundControler controller) {
		this.ground = controller;
	}
	
	

}
