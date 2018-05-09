package model;

import javafx.scene.image.ImageView;
import texture.TexturePack;

public class BackgroundMap {
	private final int[] traversable = {1,2,3};
	private boolean walkable;
	private ImageView img;
	public BackgroundMap(int val, TexturePack texture, double x, double y) {
		walkable =false;
		for(int i =0; i< traversable.length; i++) {
			if (val == traversable[i])
				walkable = true;
		}
		img= new ImageView();
		img.setImage(texture.getImg(val));
		img.relocate(x, y);
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	public ImageView getImg(){
		return img;
	}
	
}
