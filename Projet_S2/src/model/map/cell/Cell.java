package model.map.cell;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.map.cell.background.Background;
import model.map.cell.item.Item;
import model.movable.GameCharacter;

public class Cell {
	private Background background;
	private Item item;
	private GameCharacter gameCharacter;
	private IntegerProperty changeValue;
	
	public Cell() {
		this.changeValue = new SimpleIntegerProperty(0);
	}
	
	
	public boolean isWalkable() {
		return background.isWalkable();
	}
	
	public void addCharacter(GameCharacter newCharacter) {
		if(gameCharacter!=null) {
			throw new Error("can't add charac to the cell");
		}
		else {
			this.gameCharacter=newCharacter;
		}
	}
	
	public GameCharacter takeCharacter() {
		return this.gameCharacter;
	}
	
	public void setBackground(int backgroundIntVal) {
		this.background.setBackground(backgroundIntVal);
		this.changeValue.set(this.background.getTextureVal());
	}
	
	public void setItem(Item newItem) {
		this.item=newItem;
		this.notifyChange(this.item.getImgVal());
	}
	
	
	private void notifyChange(int newValImg) {
		setChangeValue(newValImg);
	}

	public IntegerProperty getChangeValue() {
		return changeValue;
	}

	private void setChangeValue(int changeValue) {
		this.changeValue.set(changeValue);
	}
	
	
	
}
