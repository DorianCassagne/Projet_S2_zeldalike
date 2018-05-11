package model;
import java.util.ArrayList;

import character.GameCharacter;
import character.Hero;
import map.GameMap;
import texture.TexturePack;
public class Game {

	private Hero hero;
	private ArrayList<GameCharacter> players;
	private GameMap map;
	private ArrayList<String> mapList;
	private TexturePack textu;

	public Game(ArrayList<String> mapList, String textuPath, int textWidth) {
		this.mapList=mapList;
		textu = new TexturePack(textuPath, textWidth, 32);
		
	}
	
	
	
}
