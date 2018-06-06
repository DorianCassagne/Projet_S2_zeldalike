package vue;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

public class MessageView {

	private TextArea textArea;
	private	BooleanProperty waitingProperty;

	public MessageView(TextArea textArea,StringProperty textProperty) {
		this.textArea = textArea;
		
		this.waitingProperty = new SimpleBooleanProperty(false);
		this.textArea.setVisible(false);
		textProperty.addListener(
				(obs,oldValue,newValue)->changeText(newValue)
		);
		
		this.waitingProperty.addListener((obs,oldValue,newValue)->{
			this.textArea.setVisible(newValue);
		});
		
	}
	
	private void changeText(String newText) {
		this.waitingProperty.set(true);
		this.textArea.setText(newText);
	}
	
	public BooleanProperty waitingProperty() {
		return this.waitingProperty;
	}
	
	
}
