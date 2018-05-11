package model;
import java.util.ArrayList;

import character.GameCharacter;
import character.Hero;
import map.GameMap;
import texture.TexturePack;
import map.MapGenerator;
public class Game {

	private Hero hero;
	private ArrayList<GameCharacter> players;
	private GameMap map;
	private ArrayList<String> mapList;
	private TexturePack textu;

	public Game(ArrayList<String> mapList, String textuPath, int textWidth) {
		this.mapList=mapList;
		textu = new TexturePack(textuPath, textWidth, 32);
		players= new ArrayList<>();
		hero= new Hero();
		map=new GameMap(MapGenerator.getBackMap(mapList.get(0)), MapGenerator.getItemMap(mapList.get(0)), MapGenerator.getCharacMap(mapList.get(0), players));
	}
	
	
	
}
