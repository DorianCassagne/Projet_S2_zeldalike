package model;

import Character.GameCharacteur;
import javafx.scene.image.ImageView;
import texture.TexturePack;

public class Square {
	private BackgroundMap back;
	private Item item;
	private GameCharacteur charac;
	public Square(int x, int y,int back, int item, GameCharacteur charac, TexturePack tp) {
		this.back= new BackgroundMap(back, tp, x, y);
	}
	public ImageView getBackText() {
		return back.getImg();
	}
}
