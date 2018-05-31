package model.gameMap.cell;

import javafx.beans.property.IntegerProperty;

import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.item.Item;
import model.gameMap.move.Movement;
import resources.additionalClass.Fusion;

public class Cell {

	
	private Background background;
	private Item item;
	private GameCharacter gameCharacter;
	private IntegerProperty changeProperty;
	private IntegerProperty safeProperty;
	
	public Cell(int[] backgroundValue,int itemValue){
		
		this.background = new Background(backgroundValue);
		this.changeProperty = new SimpleIntegerProperty();
		this.safeProperty = new SimpleIntegerProperty();
		this.safeProperty.bind(changeProperty);

	}

	public void removeMovable() {
		this.gameCharacter=null;
	}

	public boolean addMovable(GameCharacter movable) {
		if (this.gameCharacter != null)
			return false;
		this.gameCharacter = movable;
		return true;
	}


	public byte attack(Attack attack) {
		byte number = 1;
		if(this.gameCharacter != null) {
			attack.attack(this.gameCharacter);
			number *= 3;
		}
		if(this.item!=null)
			number *= 5;
		if(!this.background.isWalkable())
			number *= 7;
		return number;
	}
	
	
	public void notifyEnemy(Movement movement) {
		if(this.gameCharacter != null) {
			if(GameCharacter.getType(gameCharacter) == GameCharacter.ENEMYTYPE) {
				gameCharacter.launchAttack(movement);
			}
		}
	}
	
	public void unnotifyEnemy() {
		notifyEnemy(null);
	}

	public boolean setItem (Item item) {
		if (this.item!=null) 
			return false;
		this.item=item;
		this.changeProperty.set(item.getImageName());
		return true;
	}

	public boolean isWalkable() {
		return (this.background.isWalkable() && this.gameCharacter==null);
	}

	public Integer[] getCellBackgroundLayer() {
		return Fusion.fuseIntegerWithArray(this.background.getBackgroundList(),200);
	}
	
	public final IntegerProperty changeProperty() {
		return this.safeProperty;
	}
	
}
