package model.map.cell.item;

public abstract class ItemFactory {

	public static Item createItem(int itemVal) {
		switch (itemVal){
			case 10:
				return null;
			case 11:
				return null;
			default :
				return null;
		}
	}
}
