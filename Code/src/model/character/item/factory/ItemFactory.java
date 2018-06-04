package model.character.item.factory;

import model.character.item.Item;
import model.character.item.attack.AttackItem;
import model.character.item.def.DefenseItem;
import model.character.item.hp.Healer;
import model.character.item.mapChange.MapChanger;
import resources.additionalClass.UsefulMethods;

public class ItemFactory {
	public static Item getItem(int id) {
		Item item = null;
		
		if(UsefulMethods.isBetween(id, Item.ATTACKITEMSTARTINDEX, Item.DEFENSEINTEMSTARTINDEX)) {
			item = new AttackItem(id);
			
		}
		else if(UsefulMethods.isBetween(id, Item.DEFENSEINTEMSTARTINDEX, Item.SPEEDITEMSTARTINDEX))
			item = new DefenseItem(id);
		else if(id == 864){
			item = new MapChanger(1);
		}
					
		return item;
	}
	
	public static Item getItem(String name) {
		Item item = null;
		try {
			item = new AttackItem(name);
		}catch(IllegalArgumentException e1) {
			try {
				item = new DefenseItem(name);
			}catch(IllegalArgumentException e2) {
				item = new Healer(name);
			}
		}
		return item;
	}
	
}
