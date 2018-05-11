package model;

import Character.GameCharacter;
import javafx.scene.image.ImageView;
import texture.TexturePack;

public class Square {
	private BackgroundMap back;
	private Item item;
	private GameCharacter charac;
	public Square(int x, int y,int back, int item, GameCharacter charac, TexturePack tp) {
		this.back= new BackgroundMap(back, tp, x, y);
		this.item= new Item(item, tp, x, y);
	}
	public ImageView getBackText() {
		return back.getImg();
	}
	public ImageView getItemText() {
		return back.getImg();
	}
}
