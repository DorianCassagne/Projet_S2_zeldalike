package map;

import java.util.ArrayList;

import character.GameCharacter;
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
				map[i][j]=new Square(i*32,j*32,backMap[i][j],95,null,textu);
			}
		}

	}
	public GameMap(int[][] backMap, int[][] itemMap,GameCharacter[][] npcMap, TexturePack textu) {
		this.textu=textu;
		map=new Square[backMap.length][backMap[0].length];
		for (int i =0 ; i<backMap.length; i++) {
			for (int j =0 ; j<backMap[0].length; j++) {
				map[i][j]=new Square(j*32,i*32,backMap[i][j],itemMap[i][j],npcMap[i][j],textu);
			}
		}
	}
	
	public ArrayList<ImageView> getTexture(){
		ArrayList<ImageView> ar= new ArrayList<ImageView>();
		for (Square[] squares : map) {
			for (Square square : squares) {
				if (square.getItemText()!=null)
					ar.add(square.getBackText());
			}
		}
		for (Square[] squares : map) {
			for (Square square : squares) {
				if (square.getItemText()!=null)
					ar.add(square.getItemText());
			}
		}
		return ar;
	}
	
	public void addCharac(GameCharacter gChar) {
		map[gChar.getY()][gChar.getX()].addCharacter(gChar);
	}
	public GameCharacter takeCharac(int x, int y) {
		return map[x][y].removeCharater();
	}
	
	
	
}
