package model.gameMap.cell;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.Movable;
import model.character.attack.Attack;
import model.character.item.Item;
import model.gameMap.GameMap;

public class Cell {

	
	private Background background;
	private Item item;
	private GameCharacter gameCharacter;
	private IntegerProperty changeProperty;
	private IntegerProperty safeProperty;
	
	public Cell(int backgroundValue){
		this.background = new Background(backgroundValue);
		this.changeProperty = new SimpleIntegerProperty();
		this.safeProperty = new SimpleIntegerProperty();
		this.safeProperty.bind(changeProperty);

	}

	public Cell(int backgroundValue,Item item) {
		this(backgroundValue);
		this.item=item;
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
		byte number = 0;
		if(this.gameCharacter!=null) {
			this.gameCharacter.attaquer(attack);
			System.out.println("The character was attacked");
			number += 1;
		}
		return number;
			
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
	
	public final IntegerProperty changeProperty() {
		return this.safeProperty;
	}
	
}
