package model.character.item.speed;
/*
 * Classe Speeditem qui etend item
 * modifie la vitesse du joueur 
 */
import java.util.Timer;
import java.util.TimerTask;

import model.character.hero.Hero;
import model.character.item.Item;

public class SpeedItem extends Item{

	private SpeedItemEnum speedEnum;
	private Timer timer;
	private TimerTask timerTask;
	private Hero hero;
	
	public SpeedItem(int imageValue) {
		super(imageValue);
		int diff = imageValue - Item.SPEEDITEMSTARTINDEX;
		if(diff >= 0 && diff < SpeedItemEnum.values().length ) {
			this.speedEnum = SpeedItemEnum.values()[diff];
			initTimer();
		}
		else
			throw new IllegalArgumentException("ITEM NOT A SPEED ITEM");

	}
	
	public SpeedItem(String name) {
		this(SpeedItemEnum.valueOf(name).getImage());
	}


	@Override
	protected void applyTo(Hero hero) {
		this.hero = hero;
		this.startTimer();
	}
	
	private void initTimer() {
		this.timerTask = new TimerTask() {
			
			@Override
			public void run() {
				if(SpeedItem.this.speedEnum.getTimer() > 0 && SpeedItem.this.hero != null) {
					SpeedItem.this.hero.setSpeed(SpeedItem.this.speedEnum);
				}
				else
					this.cancel();
			}
			
		};
		
		this.timer = new Timer();
	}

	private void startTimer() {
		this.timer.scheduleAtFixedRate(this.timerTask, 0,30);
	}

}
