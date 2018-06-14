package controler.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controler.Controleur;
import controler.mainGame.GroundControler;
import controler.mainGame.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.GameStatus;
import model.StatsLoader;

public class ChargerMapController implements SceneLoader,Initializable{

    @FXML	private TableView<GameStatus> statsTable;

    @FXML	private TableColumn<GameStatus, String> dateCol;
    
	private GroundControler ground;

	
	

	@FXML
	private void newGame(ActionEvent event) {
		if(ground != null)
			ground.changeView(Controleur.FXMLGAMEPATH);
		else
			System.exit(1);
	}

	@FXML
	private void loadGame(ActionEvent event) {
		
	}

	@FXML
	private void previousScene(ActionEvent event) {
		if(ground != null) {
			this.ground.loadParent();
		}else
			System.exit(1);
	}

	@Override
	public void loadScene(GroundControler controller) {
		this.ground = controller;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try{
			this.statsTable.setItems(StatsLoader.loadState());
			this.dateCol.setCellValueFactory(cellData->cellData.getValue().getDate());
		}catch(IOException e ) {
			System.err.println("State couldn't be loaded");
		}
		
	}


}
