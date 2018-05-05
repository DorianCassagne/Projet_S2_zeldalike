package model;

public class Map {
	int[][] map;
	
	public Map(int[][] map) {
		this.map=map;
	}
	public Map(int[][] map, Npc[][] npc ) {
		this(map);
		
	}
}
