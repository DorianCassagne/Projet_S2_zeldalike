package vue;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

public class TexturePack {

	public final static int EMPTYIMG=100;
	private int titlePaneWidth;
	private int titlePx;
	private BufferedImage titleImg;
	private HashMap<Integer, WritableImage> memo;
	
	public TexturePack(String imagePath, int titlePaneWith, int titlePx) {
		try {
			titleImg= ImageIO.read(new File(imagePath).toURI().toURL());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.titlePaneWidth = titlePaneWith;
		this.titlePx = titlePx;
		memo =  new HashMap<Integer, WritableImage>();
	}
	
	public WritableImage getImg(int val) {
		if (val ==-1)
			val=1606;
		if (memo.get(val)==null)
			memo.put(val, SwingFXUtils.toFXImage(titleImg.getSubimage(val%titlePaneWidth*titlePx, (int)val/titlePaneWidth*titlePx, titlePx, titlePx), null));
		return memo.get(val);
	}
}