package item;

import personnage.GameCharacter;

public interface Item {

	public void equiper(GameCharacter p);
	public double getPointAttaque();
	public double getPointDefense();
	public double getPointMagie();
	public double getPointAgilite();
	
}
