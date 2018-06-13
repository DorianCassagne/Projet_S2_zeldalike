package model.character.npc;

import javafx.beans.property.StringProperty;

public class TalkingNPC{

	private StringProperty messageChannel;
	private String message;
	private Integer imageId;
	
	public TalkingNPC(StringProperty messageChannel,String message,int imageId) {
		if(messageChannel != null && message != null) {
			this.messageChannel = messageChannel;
			this.message = message;
			this.imageId = imageId;
		}
		else
			throw new IllegalArgumentException("SOMETHING WENT WRONG ON TALKING NPC");
	}
	
	public void talk() {
		this.messageChannel.set(this.message);
	}
	
	public int getImageId() {
		return this.imageId;
	}
	
	
	
	
}
