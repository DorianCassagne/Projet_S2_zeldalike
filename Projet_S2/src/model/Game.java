package model;
import java.util.ArrayList;

import character.Attack;
import character.GameCharacter;
import character.Hero;
import controleur.Controleur;
import javafx.beans.property.StringProperty;
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
	private ArrayList<Attack> attackList;


	public Game(ArrayList<String> mapList, String textuPath, int textWidth, Controleur controleur, StringProperty inputKeyboard) {
		mapProg=0;
		mapEnd=false;
		this.mapList=mapList;
		textu = new TexturePack(textuPath, textWidth, 32);
		players= new ArrayList<>();
		hero= new Hero(10,100,0, map, textu, inputKeyboard,0,0);
		map=new GameMap(MapGenerator.getBackMap(mapList.get(0)), MapGenerator.getItemMap(mapList.get(0)), MapGenerator.getCharacMap(mapList.get(0), players, map, textu, hero), textu);
		attackList=new ArrayList<Attack>();
	}
	public boolean turn() {
		for (GameCharacter gChar : players) {
			charPlay(gChar);
		}
		mapEnd=hero.endOfMap();
		return mapEnd;
	}
	private void charPlay(GameCharacter gChar) {
		GameCharacter charTaken=map.takeCharac(gChar.getY(), gChar.getX());
		//System.out.println(charTaken+"char");
		//System.out.println(charTaken.getX()+" "+charTaken.getY());
		int[]tab=charTaken.actionTurn(attackList);
		//System.out.println(tab+"tab");
		//System.out.println(charTaken.getX()+" "+charTaken.getY());
		//System.out.println(tab[0]+" "+tab[1]);
		charTaken.relocate(tab[0],tab[1]);
		map.addCharac(charTaken);

		//System.out.println(charTaken.getX()+" "+charTaken.getY());
		//if(charTaken!=gChar) {
		//	throw new Error("mauvais placement joueur");
		//}
		//gChar.actionTurn(attackList);
		//map.addCharac(gChar);
		
	}
	
	public ArrayList<ImageView> getAllText() {
		for (GameCharacter gc : players) {
			
		}
		ArrayList<ImageView> ar = map.getTexture();
		for (GameCharacter gc : players) {
			ar.add(gc.getImg());
		}
		return ar;
	}
	
	
	
	
	
}
