package model.character.hero;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.IntegerProperty;
public class CopyOfHeroStats {

	private IntegerProperty def;
	private IntegerProperty hp;
	private IntegerProperty atk;
	private IntegerProperty MP;
	
	public CopyOfHeroStats(IntegerProperty hp,IntegerProperty def,IntegerProperty atk,IntegerProperty MP) {
		if(hp != null && def != null && atk != null) {
			this.hp = hp;
			this.def = def;
			this.atk = atk;
			this.MP = MP;
		}
		else {
			throw new IllegalArgumentException("HP, DEF OR ATK PROPERTY ARE NULL");
		}
	}
	
	public IntegerBinding getDefBinding() {
		return this.def.add(0);
	}
	
	public IntegerBinding getAtkBinding() {
		return this.atk.add(0);
	}
	
	public IntegerBinding getHPBinding() {
		return this.hp.add(0);
	}
	
	public IntegerBinding getMPBinding() {
		return this.MP.add(0);
	}
}
