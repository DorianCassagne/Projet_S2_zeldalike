package model;

import java.awt.image.BufferedImage;

import texture.TexturePack;

public class BackgroundMap {
	private final int[] traversable = {1,2,3};
	private boolean walkable;
	private BufferedImage img;
	public BackgroundMap(int val, TexturePack texture) {
		walkable =false;
		for(int i =0; i< traversable.length; i++) {
			if (val == traversable[i])
				walkable = true;
		}
		img=texture.getImg(val);
		img.
	}
	
	public boolean isWalkable() {
		return walkable;
	}
	
}
