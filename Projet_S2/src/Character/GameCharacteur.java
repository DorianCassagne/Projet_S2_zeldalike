package Character;

import javafx.scene.image.ImageView;

public abstract class GameCharacteur  {

	protected int pv;
	protected int pvMax;
	protected int def;
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
	
}
