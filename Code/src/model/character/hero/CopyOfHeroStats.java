package model.character.hero;
import javafx.beans.binding.IntegerBinding;

/*
 * Classe qui permet la copie des statistiques du hero, 
 * classe utile pour la sauvegarde des donnees
 */
import javafx.beans.property.IntegerProperty;
public class CopyOfHeroStats {

	private IntegerBinding hp;			// point de vie
	private IntegerBinding atk;			// attaque active
	private IntegerBinding atkImage;	// image de l'item d'attaque
	private IntegerBinding MP;			// point de mana
	private IntegerBinding def;			// point de defense
	private IntegerBinding defImage;	// image de l'item defensif
	private IntegerBinding maxHp;		// maximum de point de vie
	private IntegerBinding maxMp;		// maximum de mana
	
	public CopyOfHeroStats(IntegerProperty hp,IntegerProperty def,IntegerProperty defImage,IntegerProperty atkImage,IntegerProperty atkValue,IntegerProperty MP,IntegerProperty maxHP,IntegerProperty maxMP) {

		if(hp != null && def != null && atkValue != null) {
			this.hp = hp.add(0);
			this.def = def.add(0);
			this.defImage = defImage.add(0);
			this.defImage.addListener((obs,old,newv)->System.out.println("I chnaged to " + newv));
			this.atk = atkValue.add(0);
			this.MP = MP.add(0);
			this.atkImage = atkImage.add(0);
			this.maxHp = maxHP.add(0);
			this.maxMp = maxMP.add(0);
			System.out.println(this.hp.get() + " \t" +  this.MP.get());
		}
		else {
			throw new IllegalArgumentException("HP, DEF OR ATK PROPERTY ARE NULL");
		}
	}
	
	public IntegerBinding getDefBinding() {
		return this.def;
	}
	
	public IntegerBinding getAtkBinding() {
		return this.atk;
	}
	
	public IntegerBinding getHPBinding() {
		return this.hp;
	}
	
	public IntegerBinding getMPBinding() {
		return this.MP;
	}
	
	public IntegerBinding getAtkImage() {
		return this.atkImage;
	}
	
	public IntegerBinding getDefImage() {
		return this.defImage;
	}
	
	public IntegerBinding getAtkImageBinding() {
		return this.atkImage;
	}
	
	public IntegerBinding getMaxHP() {
		return this.maxHp;
	}
	
	public IntegerBinding getMaxMP() {
		return this.maxMp;
	}
	
}
