package view;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

public class TexturePack {

	private int titlePaneWidth;
	private int titlePx;
	private BufferedImage titleImg;
	
	public TexturePack(String imagePath, int titlePaneWith, int titlePx) {
		try {
			titleImg= ImageIO.read(new File(imagePath).toURI().toURL());
		} catch (IOException e) {
			throw new IllegalArgumentException("Creation failed");
		}
		this.titlePaneWidth = titlePaneWith;
		this.titlePx = titlePx;
	}
	
	public WritableImage getImg(int val) {
		if (val ==-1)
			return null;
		return SwingFXUtils.toFXImage(titleImg.getSubimage(val%titlePaneWidth*titlePx, (int)val/titlePaneWidth*titlePx, titlePx, titlePx), null);
	}
}