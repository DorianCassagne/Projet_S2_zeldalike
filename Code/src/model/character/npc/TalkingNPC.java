package model.character.npc;
/*
 * Classe des personnages non joueur, 
 * peut parler avec le joueur lorsque le joueur tente l'action 
 * texte défini 
 */
import javafx.beans.property.StringProperty;
import model.gameMap.GameMap;

public class TalkingNPC{

	private StringProperty messageChannel; 	// Property qui permettra l'affichage en vue
	private String message;					//message original
	private Integer imageId;				// Image du NPC
	
	public TalkingNPC(StringProperty messageChannel,String message,String NPCName,GameMap map,int startRow,int startColumn) {
		NPCFactory npc = NPCFactory.valueOf(NPCName);
		if(messageChannel != null && message != null && map != null && npc != null ) {
			this.messageChannel = messageChannel;
			this.message = message;
			this.imageId = npc.getId();
			map.addNPC(this, startRow, startColumn);
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
