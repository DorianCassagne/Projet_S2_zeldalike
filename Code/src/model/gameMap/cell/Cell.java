package model.gameMap.cell;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.Game;
import model.character.GameCharacter;
import model.character.Movable;
import model.character.attack.Attack;
import model.character.item.Item;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Cell {

	private int cellId;
	private Background background;
	private Item item;
	private GameCharacter gameCharacter;
	private IntegerProperty changeProperty;
	
	public Cell(int backgroundValue, int cellId){
		this.background = new Background(backgroundValue);
		this.changeProperty = new SimpleIntegerProperty();
		this.cellId=cellId;

	}
	
	public void setChangeProperty(IntegerProperty prop) {
		this.changeProperty=prop;
	}

	public Cell(int backgroundValue,Item item, int cellId) {
		this(backgroundValue, cellId);
		this.item=item;
	}

	public void removeMovable() {
		this.gameCharacter=null;
	}
	
	public void setBooth(int back, Item item) {
		this.item=item;
		this.background= new Background(back);
		this.changeProperty.set(cellId);
	}

	public boolean addMovable(GameCharacter movable) {
		if (this.gameCharacter != null)
			return false;
		this.gameCharacter = movable;
		return true;
	}
	public void setBackground(int backValue) {
        this.background=new Background(backValue);
        this.changeProperty.set(cellId);
    }



	public byte attack(Attack attack) {
		byte number = 1;
		if(this.gameCharacter != null) {
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

	public int getBackgroundRepresentation() {
		return this.background.getImage();
	}
	
	
	
}
