package model.character.hero;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
public class CopyOfHeroStats {

	private IntegerBinding defImage;
	private IntegerBinding hp;
	private IntegerBinding atkImage;
	private IntegerBinding MP;
	private IntegerBinding atk;
	private IntegerBinding def;
	
	public CopyOfHeroStats(IntegerProperty hp,IntegerProperty def,IntegerProperty atkImage,IntegerProperty atkValue,IntegerProperty MP) {

		if(hp != null && def != null && atkValue != null) {
			this.hp = hp.add(0);
			this.def = def.add(0);
			this.atk = atkValue.add(0);
			this.MP = MP.add(0);
			this.atkImage = atkImage.add(0);
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
}
