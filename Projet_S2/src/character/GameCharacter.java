package character;

import java.util.ArrayList;

import controleur.Controleur;
import javafx.scene.image.ImageView;
import map.GameMap;
import texture.TexturePack;

public abstract class GameCharacter  {

	protected int hp;
	protected int pvMax;
	protected int def;
	protected int x;
	protected int y;
	protected ImageView img;
	protected boolean dead;
	protected GameMap map;
	protected TexturePack textu;
	protected int imgInt;
	
	protected GameCharacter(int imgInt,int hpMax, GameMap map, TexturePack textu, int x, int y) {
		dead=false;
		def=0;
		this.map=map;
		this.textu=textu;
		this.x=x;
		this.y=y;
		img=new ImageView(textu.getImg(imgInt));
		img.relocate(x, y);
	}
	public GameCharacter(int imgInt,int hpMax, int def, GameMap map, TexturePack textu, int x, int y) {
		this( imgInt,hpMax,map, textu,x,y);
		this.def=def;
	}
	
	
	public void getDmg(Attack att) {
			if (!dead) {
			hp-=att.getDamage()*(100/def+100);
			if (hp<=0) {
				dead = true;
				this.noImg();
			}
		}
	}
	
	public void getHeal(int heal) {
		if (!dead) {
			if(hp+heal>pvMax) {
				hp=pvMax;
			}
			else {
				hp+=heal;
			}
		}
	}
	
	public int[] actionTurn(ArrayList<Attack> ar){
		return null;
	}
	
	public boolean isDead() {
		return dead;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void relocate(int x,int y) {
		this.x=x;
		this.y=y;
		img.relocate(x*Controleur.SIZETILE, y*Controleur.SIZETILE);
	}
	private void noImg() {
		img.setImage(textu.getImg(TexturePack.EMPTYIMG));
	}
	
	public ImageView getImg() {
		return img;
	}
}
