package texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

public class TexturePack {

	int titlePaneWidth;
	int titlePx;
	BufferedImage titleImg;
	public TexturePack(String imagePath, int titlePaneWith, int titlePx) {
		try {
			titleImg= ImageIO.read(new File(imagePath).toURI().toURL());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.titlePaneWidth = titlePaneWith;
		this.titlePx = titlePx;
	}
	
	
	
	public WritableImage getImg(int val) {
		return SwingFXUtils.toFXImage(titleImg.getSubimage(val%titlePaneWidth*titlePx, (int)val/titlePaneWidth*titlePx, titlePx, titlePx), null);
		//return SwingFXUtils.toFXImage(titleImg.getSubimage(00, 00, 100,100),null);

	}
}