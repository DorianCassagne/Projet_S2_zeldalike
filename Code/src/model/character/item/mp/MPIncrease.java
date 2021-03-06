package model.character.item.mp;
/*
 * 
 */
/*
 * Classe MPincrease qui etend item
 * modifie le points de MP max du hero 
 */
import model.character.hero.Hero;
import model.character.item.Item;
import model.character.item.mapChange.MapChangerEnum;

public class MPIncrease extends Item{

	private MPItemEnum mpItem;
	
	public MPIncrease(int imageId){
		super(imageId);
		int diff = imageId - Item.MPITEMSTARTINDEX;
		if(diff >= 0 && diff < MPItemEnum.values().length ) {
			this.mpItem = MPItemEnum.values()[diff];
		}
		else
			throw new IllegalArgumentException("ITEM NOT A POTION");
	}
	
	public MPIncrease(String mapName) throws IllegalArgumentException {
		this(MPItemEnum.valueOf(mapName).getImage());
	}

	
	@Override
	protected void applyTo(Hero hero) {
		hero.increaseMP(mpItem);
	}

}
