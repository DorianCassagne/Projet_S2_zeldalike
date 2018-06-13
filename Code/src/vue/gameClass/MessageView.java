package vue.gameClass;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

public class MessageView {

	private TextArea textArea;
	private	BooleanProperty waitingProperty;

	public MessageView(TextArea textArea,StringProperty textProperty) {
		this.initTextArea(textArea);
		this.initWaitingProperty();
		textProperty.addListener(
				(obs,oldValue,newValue)->changeText(newValue)
		);

	}
	
	private void initWaitingProperty() {
		this.waitingProperty = new SimpleBooleanProperty(false);
		this.waitingProperty.addListener((obs,oldValue,newValue)->{
			this.textArea.setVisible(newValue);
		});
	}
	
	private void initTextArea(TextArea textArea) {
		this.textArea = textArea;
		this.textArea.setVisible(false);

	}
	
	private void changeText(String newText) {
		this.waitingProperty.set(true);
		this.textArea.setText(newText);
	}
	
	public BooleanProperty waitingProperty() {
		return this.waitingProperty;
	}
	
	
}
