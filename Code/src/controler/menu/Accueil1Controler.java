package controler.menu;

import controler.Controleur;
import controler.mainGame.GroundControler;
import controler.mainGame.SceneLoader;
import javafx.fxml.FXML;


public class Accueil1Controler implements SceneLoader{

	
	public final static String FXMLPATH = "menu/MenuAccueil.fxml";
	
	private GroundControler ground;
	
    @FXML
    private void newGame() {
    	if(ground != null)
    		ground.changeView(Controleur.FXMLPATH);
    	else
    		System.exit(1);
    		
    }
    
    @FXML
    private void exit() {
    	System.exit(1);
    }
    
    @FXML
    private void loadGame() {
    	//TODO
    }

	@Override
	public void loadScene(GroundControler controller) {
		this.ground = controller;
	}
    

}
