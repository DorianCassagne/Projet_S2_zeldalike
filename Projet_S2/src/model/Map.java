package model;

public class Map {
	title[][] map;
	
	public Map(title[][] map) {
		this.map=map;
	}
	public Map(title[][] map, Npc[][] npc ) {
		this(map);
		
	}
}
