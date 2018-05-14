package character;

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
	
	public GameCharacter(int hpMax) {
		
	}
	
	
	public void getDmg(Attack att) {
		hp-=att.getDamage()*(100/def+100);
		if (hp<=0) {
			dead = true;
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
	
	public int[] actionTurn(GameMap map, TexturePack textu) {
		int[] tab = {x,y};
		return tab;
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
	}
}
