package vue.gameClass;

import java.util.LinkedList;
import java.util.Queue;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.TextArea;

public class MessageView {

	private TextArea textArea;
	private	BooleanProperty waitingProperty;
	private Queue<String> messagesToShow;
	private StringProperty textProperty;
	
	public MessageView(TextArea textArea,StringProperty textProperty) {
		this.initTextArea(textArea);
		this.initWaitingProperty();
		
		this.messagesToShow = new LinkedList<String>();
		this.textProperty = textProperty;
		
		textProperty.addListener(
				(obs,oldValue,newValue)->addText(newValue)
		);

	}
	
	private void initWaitingProperty() {
		this.waitingProperty = new SimpleBooleanProperty(false);
		this.waitingProperty.addListener((obs,oldValue,newValue)->{
			if(!newValue) {
			
				this.showNext();
				
			}
		});
	}
	
	private void initTextArea(TextArea textArea) {
		this.textArea = textArea;
		this.textArea.setVisible(false);

	}
	
	private void addText(String newText) {
		if(newText.length() > 0) {
			this.messagesToShow.add(format(newText));
			this.showNext();
			this.textProperty.set("");
		}
	}
	
	
	private String format(String newText) {
		return newText.replace("\\n", "\n");
	}
	
	private void showNext() {
		
		if(!this.waitingProperty.get()) {
			boolean stillMessaging = !this.messagesToShow.isEmpty();
			
			this.waitingProperty.set(stillMessaging);
			this.textArea.setVisible(stillMessaging);
			
			if(stillMessaging){
				this.textArea.setText(this.messagesToShow.poll());
			}
		}
	}
	
	public BooleanProperty waitingProperty() {
		return this.waitingProperty;
	}
	
	
}
