package texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

public class TexturePack {

	int titlePaneWith;
	int titlePx;
	BufferedImage titleImg;
	public TexturePack(String imagePath, int titlePaneWith, int titlePx) throws MalformedURLException, IOException {
		titleImg= ImageIO.read(new File(imagePath).toURI().toURL());
		this.titlePaneWith = titlePaneWith;
		this.titlePx = titlePx;
	}
	
	
	
	public BufferedImage getImg(int val) {
		return titleImg.getSubimage(val%titlePaneWith, (int)val/titlePaneWith, titlePx, titlePx);
	}
}