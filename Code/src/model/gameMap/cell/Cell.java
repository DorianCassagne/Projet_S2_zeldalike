package model.gameMap.cell;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;


import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.item.Item;
import model.character.item.factory.ItemFactory;
import resources.additionalClass.Fusion;

public class Cell {

	public final static byte NOEFFECT = 1;
	public final static byte CHARACTERISPRESENT = 3;
	public final static byte ITEMISPRESENT = 5;
	public final static byte NOTWALKABLE = 7 ;

	
	private ArrayList<Attack> attackList;
	private Background background;
	private Item item;
	private GameCharacter gameCharacter;
	private IntegerProperty mapProperty;
	private int cellId;
	
	
	public Cell(Integer[] backgroundValue,int itemValue,IntegerProperty mapProperty,int cellId){
		this.background = new Background(backgroundValue);
		this.mapProperty = mapProperty;
		this.item = ItemFactory.getItem(itemValue);
		this.cellId = cellId;
		this.attackList = new ArrayList<Attack>();
	}

	public boolean containsItem() {
		return this.item == null;
	}
	
	public void removeMovable() {
		this.gameCharacter=null;
	}
	
	public void setToWalkable() {
		this.background.setToWalkable();
		this.triggerChange();
	}

	public boolean addMovable(GameCharacter movable) {
		boolean isSet = this.gameCharacter == null && this.isWalkable();
		if (isSet && movable != null) {
			this.gameCharacter = movable;
			this.launchAttacks();
			if(item!=null && this.item.effectOn(movable)) {
				this.delItem();
			}
		}
		
		return isSet;
	}


	public byte addAttack(Attack attack) {
		byte number = NOEFFECT;
				
		if(this.item!=null)
			number *= ITEMISPRESENT;
		if(!this.background.isWalkable())
			number *= NOTWALKABLE;
		
		this.attackList.add(attack);
		this.launchAttacks();
		
		return number;
	}
	
	private void launchAttacks() {
		int index = 0;
		while(!this.attackList.isEmpty()) {
			this.attack(this.attackList.get(index));
		}
	}
	
	private void attack(Attack attack) {
		attack.attack(this.gameCharacter);
		this.attackList.remove(attack);
	}
	
	public void turn() {
		this.attackList.clear();
	}
	
	
	private void delItem () {
		this.item = null;
		triggerChange();
	}

	public boolean setItem (Item item) {
		boolean isSet = this.item == null;
		if(isSet) {
			this.item=item;
			this.triggerChange();
		}
		return isSet;
	}

	public boolean isWalkable() {
		return (this.background.isWalkable() && this.gameCharacter==null);
	}

	public Integer[] getCellBackgroundLayer() {
		if(item != null)
			return Fusion.fuseIntegerWithArray(this.background.getBackgroundList(),this.item.getImageValue());
		else
			return this.background.getBackgroundList();
	}
	
	
	public void setBackground(Integer[] backValue) {
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
