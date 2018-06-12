package model.character.hero;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
public class CopyOfHeroStats {

	private IntegerBinding def;
	private IntegerBinding hp;
	private IntegerBinding atk;
	private IntegerBinding MP;
	
	public CopyOfHeroStats(IntegerProperty hp,IntegerProperty def,IntegerProperty atk,IntegerProperty MP) {
		if(hp != null && def != null && atk != null) {
			this.hp = hp.add(0);
			this.def = def.add(0);
			this.atk = atk.add(0);
			this.MP = MP.add(0);
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
}
