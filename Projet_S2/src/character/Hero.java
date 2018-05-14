package character;

import java.util.ArrayList;

import javafx.beans.property.StringProperty;
import map.GameMap;
import texture.TexturePack;

public class Hero extends GameCharacter{
	private StringProperty inputKeyboard;
	int imgInt;
//	public Hero(int imgInt,int hpMax, GameMap map, TexturePack textu,int x, int y) {
//		super(imgInt,hpMax, map, textu,x,y);
//		this.onANewMapp();
//	}
	private boolean endMap;
	
	public Hero(int imgInt,int hpMax, int def,GameMap map, TexturePack textu, StringProperty inputKeyboard,int x,int y ) {
		super(imgInt, hpMax, def, map, textu,x,y);
		this.inputKeyboard=inputKeyboard;
		//System.out.println(inputKeyboard);
	}

	@Override
	public int[] actionTurn(ArrayList<Attack> ar){
		int[] mov=new int[2];
		final String st= inputKeyboard.get();
		//System.out.println(st+"string proper");
		if (st!=null &&st!= "") {
			switch (st) {
			case "up":
				mov[0]=this.getX();
				mov[1]=this.getY()-1;
				break;
			case "down":
				mov[0]=this.getX();
				mov[1]=this.getY()+1;
				break;
			case "right":
				mov[0]=this.getX()+1;
				mov[1]=this.getY();
				break;
			case "left":
				mov[0]=this.getX()-1;
				mov[1]=this.getY();
				break;
			default:
				mov[0]=this.getX();
				mov[1]=this.getY();
				break;
			}
		}
		else {

			mov[0]=this.getX();
			mov[1]=this.getY();
		}
		//this.relocate(x*Controleur.SIZETILE, y*Controleur.SIZETILE);
		inputKeyboard.set("");
		return mov;
	}
	public void onANewMapp(){
		this.endMap=false;
	}
	public boolean endOfMap() {
		return endMap;
	}
	
	@Override
	public void getDmg(Attack att) {
		super.getDmg(att);
	}
}
