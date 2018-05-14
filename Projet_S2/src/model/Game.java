package model;
import java.util.ArrayList;

import character.GameCharacter;
import character.Hero;
import javafx.scene.image.ImageView;
import map.GameMap;
import texture.TexturePack;
import map.MapGenerator;
public class Game {

	private Hero hero;
	private ArrayList<GameCharacter> players;
	private GameMap map;
	private ArrayList<String> mapList;
	private TexturePack textu;
	private boolean mapEnd;
	private int mapProg;

	public Game(ArrayList<String> mapList, String textuPath, int textWidth) {
		mapProg=0;
		mapEnd=false;
		this.mapList=mapList;
		textu = new TexturePack(textuPath, textWidth, 32);
		players= new ArrayList<>();
		hero= new Hero(100);
		map=new GameMap(MapGenerator.getBackMap(mapList.get(0)), MapGenerator.getItemMap(mapList.get(0)), MapGenerator.getCharacMap(mapList.get(0), players), textu);
		//hero.relocate(players.get(0).getX(),players.get(0).getY());
	}
	public boolean turn() {
		charPlay(hero);
		for (GameCharacter gChar : players) {
			charPlay(gChar);
		}
		mapEnd=hero.endOfMap();
		return mapEnd;
	}
	private void charPlay(GameCharacter gChar) {
		GameCharacter charTaken=map.takeCharac(gChar.getX(), gChar.getY());
		if(charTaken!=gChar) {
			throw new Error("mauvais placement joueur");
		}
		gChar.actionTurn(map, textu);
		map.addCharac(gChar);
		
	}
	public ArrayList<ImageView> getAllText() {
		return map.getTexture();
	}
	
	
	
	
	
}
