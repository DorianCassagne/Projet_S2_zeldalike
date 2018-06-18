package vue.gameClass;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertMessage {

	private final static Alert GAMEALERT = new Alert(Alert.AlertType.ERROR);
	
	public static void showError(String message) {
		GAMEALERT.setTitle("Alerte Zelda");
		GAMEALERT.setContentText(message);
		GAMEALERT.setAlertType(AlertType.ERROR);
		GAMEALERT.showAndWait();
	
	}
	
	public static void showInfo(String message) {
		GAMEALERT.setTitle("Informations Zelda");
		GAMEALERT.setContentText(message);
		GAMEALERT.setAlertType(AlertType.INFORMATION);
		GAMEALERT.showAndWait();
	
	}

}
