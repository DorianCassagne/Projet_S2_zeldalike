package model.character.item.factory;
/*
 * classe ItemFactory 
 * permet de facilite la creation des objets selon leur ID
 */
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
			if(UsefulMethods.isBetween(id, Item.ATTACKITEMSTARTINDEX, Item.DEFENSEINTEMSTARTINDEX)) {
				item = new AttackItem(id);
			}
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
			else if(id == 864){
				item = new MapChanger(1);
			}
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
				try{
					item = new Healer(name);
				}catch(IllegalArgumentException e3) {
					try {
						item = new SpecialAttackItem(name);
					}catch(IllegalArgumentException e4) {
						item = new MapChanger(name);
					}
				}
			}
		}
		return item;
	}
	
}
