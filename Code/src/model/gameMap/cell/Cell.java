package model.gameMap.cell;

import javafx.beans.property.IntegerProperty;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.item.Item;
import model.gameMap.additional.Statics;
import resources.additionalClass.Fusion;

public class Cell {

	
	private Background background;
	private Item item;
	private GameCharacter gameCharacter;
	private IntegerProperty mapProperty;
	private int cellId;
	
	
	public Cell(int[] backgroundValue,int itemValue,IntegerProperty mapProperty,int cellId){
		
		this.background = new Background(backgroundValue);
		this.mapProperty = mapProperty;
	
	}

	public void removeMovable() {
		this.gameCharacter=null;
	}

	public boolean addMovable(GameCharacter movable) {
		boolean isSet = this.gameCharacter == null && this.isWalkable();
	
		if (isSet) {
			this.gameCharacter = movable;
			if(item!=null && this.item.effectOn(movable)) {
				this.delItem();
			}
		}
		
		return isSet;
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
	
	public void delItem () {
		this.item = null;
		this.mapProperty.set(cellId);
	}

	public boolean setItem (Item item) {
		if (this.item!=null) 
			return false;
		this.item=item;
		this.mapProperty.set(cellId);
		return true;
	}

	public boolean isWalkable() {
		return (this.background.isWalkable() && this.gameCharacter==null);
	}

	public Integer[] getCellBackgroundLayer() {
		return Fusion.fuseIntegerWithArray(this.background.getBackgroundList(),200);
	}
	
	
	public void setBackground(int[] backValue) {
        this.background = new Background(backValue);
        this.triggerChange();
	}
	
	private void triggerChange() {
		if(mapProperty.get()==this.cellId)
			this.mapProperty.set(-1);
		else
			this.mapProperty.set(this.cellId);
	}
	
	
	
	
	
}
