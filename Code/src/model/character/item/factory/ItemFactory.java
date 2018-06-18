package model.character.item.factory;

import model.character.item.Item;
import model.character.item.attack.AttackItem;
import model.character.item.def.DefenseItem;
import model.character.item.hpPotion.Healer;
import model.character.item.mapChange.MapChanger;
import model.character.item.mp.MPIncrease;
import model.character.item.mpPotion.MPItem;
import model.character.item.pvItem.HealthItem;
import model.character.item.speed.SpeedItem;
import resources.additionalClass.UsefulMethods;

public class ItemFactory {
	public static Item getItem(int id) {
		Item item = null;
		
		try {
			item = new SpecialAttackItem(id);
		}catch(IllegalArgumentException e) {
						
			if(UsefulMethods.isBetween(id, Item.ATTACKITEMSTARTINDEX, Item.DEFENSEINTEMSTARTINDEX)) 
				item = new AttackItem(id);
			else if(UsefulMethods.isBetween(id, Item.DEFENSEINTEMSTARTINDEX, Item.SPEEDITEMSTARTINDEX))
				item = new DefenseItem(id);
			else if(UsefulMethods.isBetween(id, Item.SPEEDITEMSTARTINDEX, Item.MPITEMSTARTINDEX))
				item = new SpeedItem(id);
			else if(UsefulMethods.isBetween(id, Item.MPITEMSTARTINDEX, Item.HPITEMSTARTINDEX))
				item = new MPIncrease(id);
			else if(UsefulMethods.isBetween(id, Item.HPITEMSTARTINDEX, Item.BOXITEMSTARTINDEX))
				item = new HealthItem(id);
			else if(UsefulMethods.isBetween(id, Item.BOXITEMSTARTINDEX, Item.HPPOTIONSTARTINDEX))
				item = null;
			else if(UsefulMethods.isBetween(id, Item.HPPOTIONSTARTINDEX, Item.MPPOTIONSTARTINDEX))
				item = new Healer(id);
			else if(UsefulMethods.isBetween(id, Item.MPPOTIONSTARTINDEX, Item.MPPOTIONENDINDEX))
				item = new MPItem(id);
		}
					
		return item;
	}
	
	public static Item getItem(String name) {
		Item item = createAttackItem(name);
		
		if(item == null) {
			item = createDefenseItem(name);
		}
		
		if(item == null)
			item = createHealer(name);
		
		if(item == null)
			item = createMapChange(name);
		
		if(item == null)
			item = createMp(name);
		
		if(item == null) 
			item = createMPPotion(name);
		
		if(item == null)
			item = createPVItem(name);
		
		if(item == null)
			item = createSpeedItem(name);
				
		return item;
	}
	
	private static Item createAttackItem(String name) {
		Item item = null;
		
		try {
			item = new AttackItem(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
	private static Item createDefenseItem(String name) {
		Item item = null;
		
		try {
			item = new DefenseItem(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
	private static Item createHealer(String name) {
		Item item = null;
		
		try {
			item = new Healer(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
	private  static Item createMapChange(String name) {
		Item item = null;
		
		try {
			item = new MapChanger(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
	private static Item createMp(String name) {
		Item item = null;
		
		try {
			item = new MPIncrease(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
	private static Item createMPPotion(String name) {
		Item item = null;
		
		try {
			item = new MPItem(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
	private static Item createPVItem(String name) {
		Item item = null;
		
		try {
			item = new HealthItem(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
	private static Item createSpeedItem(String name) {
		Item item = null;
		
		try {
			item = new SpeedItem(name);
		}catch(IllegalArgumentException e) {}
		
		return item;
	}
	
}
