package model.character.hero;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
public class CopyOfHeroStats {

	private IntegerBinding hp;
	private IntegerBinding atk;
	private IntegerBinding atkImage;
	private IntegerBinding MP;
	private IntegerBinding def;
	private IntegerBinding defImage;
	private IntegerBinding maxHp;
	private IntegerBinding maxMp;
	
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
	
	public IntegerBinding getDefValue() {
		return this.defImage;
	}
}
