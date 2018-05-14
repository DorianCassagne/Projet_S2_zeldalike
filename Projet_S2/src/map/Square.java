package map;

import character.GameCharacter;
import javafx.scene.image.ImageView;
import texture.TexturePack;

public class Square {
	private BackgroundMap back;
	private Item item;
	private GameCharacter charac;
	public Square(int x, int y,int back, int item, GameCharacter charac, TexturePack tp) {
		this.back= new BackgroundMap(back, tp, x, y);
		this.item= new Item(item, tp, x, y);
		this.charac=charac;
	}
	public ImageView getBackText() {
		if(item!=null)
			return back.getImg();
		return null;
	}
	public ImageView getItemText() {
		if(item!=null)
			return item.getImg();
		return null;
	}
	public GameCharacter removeCharater() {
		GameCharacter c = charac;
		charac=null;
		return c;
	}
	public void addCharacter(GameCharacter newPlayer) {
		System.out.println(charac);
		if(charac != null && !charac.isDead()) {
			throw new Error("impossible dajouter nouveau personage a la case");
		}
		charac=newPlayer;
	}
	public boolean isWalkable() {
		return back.isWalkable()&&item.isWalkable()&&(charac == null || charac.isDead());
	}
	
}
