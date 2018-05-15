package personnage;

import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import additionalMethods.ErrorCodes;
import javafx.beans.property.*;

import mapZelda.Map;
import mapZelda.Move;

public abstract class GameCharacter {

    private ScheduledExecutorService scheduler ;
	public static final int CHARACTERDEFAULTSIZE = 16;
	protected final static int ONESPEEDPOINTTOTIME = 25;
	private TimerTask characterMoveFreezeTask;
	private boolean isWalkingPossible;
	private String name;
	private StringProperty representationImage;
	private StringProperty copyOfRepresentationImage;
	private Map myMap ;
	
	public GameCharacter(String name,String startImage,Map map,int startRow,int startColumn) {
		if(name != null && map != null) {
			this.name = name;
			this.representationImage = new SimpleStringProperty(startImage);
			this.myMap = map;
			this.myMap.addToCharList(this, startRow, startColumn);
			this.scheduler= Executors.newScheduledThreadPool(1);
			this.isWalkingPossible = true;
			this.characterMoveFreezeTask = new TimerTask() {
				@Override
				public void run() {
					GameCharacter.this.isWalkingPossible = true;
					this.cancel();
				}
			};

		}
		else
			throw new IllegalArgumentException(ErrorCodes.NULLCODE);
	}
	
	
	public StringProperty representationImageProperty() {
		this.copyOfRepresentationImage = new SimpleStringProperty();
		copyOfRepresentationImage.bind(this.representationImage);
		return copyOfRepresentationImage;
	}
	
	
	private void setRepresentationImage(String imageName) {
		if(imageName != null)
			representationImage.set(imageName);
	}
	
	private void stopAction() {
		this.isWalkingPossible = false;
		 this.scheduler.schedule(characterMoveFreezeTask, this.getWalkTime() + 60, TimeUnit.MILLISECONDS);
	}
	
	public  void toTop() {
		if(this.isWalkingPossible) {
			this.setRepresentationImage(this.getTopImage());
			this.myMap.moveCharacter(this, Move.TOP);
			this.stopAction();
		}
	}
	
	public  void toLeft() {
		if(this.isWalkingPossible) {
			this.setRepresentationImage(this.getLeftImage());
			this.myMap.moveCharacter(this, Move.LEFT);
			this.stopAction();
		}

	}
	
	public  void toBottom() {
		if(this.isWalkingPossible) {
			this.setRepresentationImage(this.getBottomImage());
			this.myMap.moveCharacter(this, Move.BOTTOM);
			this.stopAction();

		}
	}
	
	public  void toRight() {
		if(this.isWalkingPossible) {
			this.setRepresentationImage(this.getRightImage());
			this.myMap.moveCharacter(this, Move.RIGHT);
			this.stopAction();
		}
	}
		
	public abstract String getTopImage();
	public abstract String getBottomImage();
	public abstract String getLeftImage();
	public abstract String getRightImage();
	protected abstract int getWalkSpeed();
	
	public int getWalkTime() {
		return this.getWalkSpeed() * ONESPEEDPOINTTOTIME;
	}
	
	public String getNom() {
		return this.name;
	}
	
	

	
	
	
}
