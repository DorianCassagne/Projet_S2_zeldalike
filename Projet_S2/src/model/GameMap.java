package model;

import java.util.ArrayList;

import Character.Npc;
import javafx.scene.image.ImageView;
import texture.TexturePack;

public class GameMap {
	Square[][] map;
	TexturePack textu;
	
	public GameMap(int[][] backMap) {


		textu = new TexturePack("img/tile.png", 8, 32);
		map= new Square[backMap.length][backMap[0].length];

		for (int i =0 ; i<backMap.length; i++) {

			for (int j =0 ; j<backMap[0].length; j++) {
				map[i][j]=new Square(i*32,j*32,backMap[i][j],0,null,textu);
			}
		}

	}
	public GameMap(int[][] backMap, int[][] itemMap,Npc[][] npcMap) {
		
	}
	
	public ArrayList<ImageView> getTexture(){
		ArrayList<ImageView> ar= new ArrayList<ImageView>();
		for (Square[] squares : map) {
			for (Square square : squares) {
				ar.add(square.getBackText());
			}
		}
		return ar;
	}
}
