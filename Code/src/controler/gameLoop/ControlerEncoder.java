package controler.gameLoop;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.Game;

public class ControlerEncoder {

	private AnchorPane characterAnchorPane;
	
	private Label HPLabel ;
	private Label MPLabel;
	
	private ProgressBar HPProgressBar;
	private ProgressBar MPProgressBar;
	
	private ImageView attackImageView;
	private ImageView defImageView;
	
	private Game myGame;

	
	public ControlerEncoder(AnchorPane characterAnchorPane, Label hpLabel, ProgressBar hpProgressBar, Label mpLabel,
			ProgressBar mpProgressBar, ImageView defImageView, ImageView attackImageView, Game myGame) {
		this.myGame = myGame;
		this.characterAnchorPane = characterAnchorPane;
		this.HPLabel = hpLabel;
		this.MPLabel = mpLabel;
		this.HPProgressBar = hpProgressBar;
		this.MPProgressBar = mpProgressBar;
		this.attackImageView = attackImageView;
		this.defImageView = defImageView;
	}

	public AnchorPane getCharacterAnchorPane() {
		return this.characterAnchorPane;
	}
	
	public Label getHPLabel() {
		return this.HPLabel;
	}
	
	public ProgressBar getHPProgressBar() {
		return this.HPProgressBar;
	}
	
	public Game getMyGame() {
		return this.myGame;
	}
	
	public Label getMPLabel() {
		return MPLabel;
	}


	public ProgressBar getMPProgressBar() {
		return MPProgressBar;
	}


	public ImageView getAttackImageView() {
		return attackImageView;
	}


	public ImageView getDefImageView() {
		return defImageView;
	}

}
