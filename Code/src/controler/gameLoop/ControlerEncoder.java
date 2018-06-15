package controler.gameLoop;

import java.util.ArrayList;

import javafx.scene.control.Button;
import controler.mainGame.GroundControler;
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
	
	private ArrayList<ImageView> imageList;
	private ArrayList<Button> buttonList;
	
	private Game myGame;
	
	private GroundControler ground;


	public ControlerEncoder(AnchorPane characterAnchorPane, Label hpLabel, ProgressBar hpProgressBar, Label mpLabel,
			ProgressBar mpProgressBar, Game myGame) {
		this.myGame = myGame;
		this.characterAnchorPane = characterAnchorPane;
		this.HPLabel = hpLabel;
		this.MPLabel = mpLabel;
		this.HPProgressBar = hpProgressBar;
		this.MPProgressBar = mpProgressBar;
		this.imageList = new ArrayList<ImageView>();
		this.buttonList = new ArrayList<Button>();
	}
	
	/*
	 * @Throws IndexOutOfBoundsException
	 */
	public void addButtonImage(ImageView view,Button button,int index) {
		this.imageList.add(view);
		this.buttonList.add(button);

	}
	
	public GroundControler getGround() {
		System.out.println(this.ground);
		return this.ground;
	}
	
	public void setGround(GroundControler ground) {
		System.out.println("The ground is set " + ground);
		this.ground= ground;
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

	/*
	 * @Throws IndexOutOfBoundsException
	 */
	public Button getButtonAt(int index){
		return this.buttonList.get(index);
	}
	
	/*
	 * @Throws IndexOutOfBoundsException
	 */
	public ImageView getImageViewAt(int index) {
		return this.imageList.get(index);
	}
	
}
