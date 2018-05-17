

public abstract class Item {
	private int imgVal;
	private int hp;
	private int mp;
	private int atk;
	
	public Item(int imgVal) {
		this.setImgVal(imgVal);
	}
	
	//crée un objet de type Item. Renvoie une erreure si l’une des valeurs est négatives
	public Item(int hp, int mp, int atk) throws IllegalArgumentException{ 
		if(hp < 0 || mp < 0 || atk < 0) {
			throw new IllegalArgumentException("FALSE ARGUMENT ON ITEM");
		}
		else {
			this.hp=hp;
			this.mp=mp;
			this.atk=atk;
		}
	}

	//public abstract int getImageName(): retourne le nom de l’imag
	public int getImgVal() {
		return imgVal;
	}

	private void setImgVal(int imgVal) {
		this.imgVal = imgVal;
	}
	
	//modifie les caractéristique du personnage à partir des propriétés de l’item,
	public abstract void applyTo(Hero hero); 
	
}
