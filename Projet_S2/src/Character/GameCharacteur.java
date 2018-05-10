package Character;

import javafx.scene.image.ImageView;
import model.GameMap;

public abstract class GameCharacteur  {

	protected int pv;
	protected int pvMax;
	protected int def;
	protected int x;
	protected int y;
	protected ImageView img;
	boolean dead;
	
	
	
	
	public void getDmg(Attack att) {
		pv-=att.getDamage()*(100/def+100);
		if (pv<=0) {
			dead = true;
		}
	}
	
	public void getHeal(int heal) {
		if (!dead) {
			if(pv+heal>pvMax) {
				pv=pvMax;
			}
			else {
				pv+=heal;
			}
		}
	}
	
	public int[] actionTurn(GameMap map) {
		int[] tab = {x,y};
		return tab;
		
	}
	
}
