package model.character.npc;

public enum NPCFactory {
	MARIO(72),
	FAIRY1TOP(73),
	FAIRY1RIGHT(74),
	FAIRY1BACK(75),
	FAIRY1LEFT(76),
	FAIRY2TOP(66),
	FAIRY2RIGHT(67),
	FAIRY2BACK(68),
	FAIRY2LEFT(69);
	
	private int id;
	
	NPCFactory(int id){
		this.id = id;
	}
	
	public int getId() {
		return this.id;
	}
	

}
