package controleur;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import model.Game;
import vue.Vue;

public class Controleur implements Initializable{
	
	private Game game;
	private Vue vue;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.game=new Game();
		
	}
	

}
