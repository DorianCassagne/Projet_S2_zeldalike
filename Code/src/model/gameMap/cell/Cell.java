package model.gameMap.cell;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.Movable;
import model.character.attack.Attack;
import model.character.item.Item;
import model.gameMap.GameMap;

public class Cell {

	
	private Background background;
	private Item item;
	private Movable movable;
	private IntegerProperty changeProperty;
	private IntegerProperty safeProperty;
	
	public Cell(int backgroundValue){
		this.background = new Background(backgroundValue);
	}

	public Cell(int backgroundValue,Item item) {
		this(backgroundValue);
		this.item=item;
		this.changeProperty = new SimpleIntegerProperty();
		this.safeProperty = new SimpleIntegerProperty();
		this.safeProperty.bind(changeProperty);
	}

	public Movable removeMovable() {
		Movable mov=this.movable;
		this.movable=null;
		return mov;
	}

	public boolean addMovable(Movable movable) {
		if (this.movable!=null)
			return false;
		this.movable=movable;
		return true;
	}

	public byte attack(Attack attack) {
		byte number=0;
		if(this.movable!=null) {
			attack.attackCharacter(this.movable);
			number+=1;
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

	public int isWalkable() {
		return (this.background.isWalkable() && this.movable==null);
	}

	public int getBackgroundRepresentation() {
		return this.background.getImage();
	}
	
	public final IntegerProperty changeProperty() {
		return this.changeProperty();
	}
}
