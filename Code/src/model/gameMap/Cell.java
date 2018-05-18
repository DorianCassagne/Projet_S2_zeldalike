package app.model;

public class Cell {

	Background background;
	Item item;
	Movable movable;
	public Cell(int backgroundValue) {
		this.background = new Background(backgroundValue);
	}

	public Cell(int backgroundValue,Item item) {
		this(backgroundValue);
		this.item=item;
	}

	public Movable removeMovable() {
		Movable mov=this.movable;
		this.movable=null;
		return mov;
	}

	public boolean addMovable(Movable movable) {
		if (this.movable!=null)
			return false;
		this.movable=movable;
		return true;.
	}

	public Byte attack(Attack attack) {
		Byte byte=0;
		if(this.movable!=null) {
			attack.attackCharacter(this.movable);
			byte+=1;
		}
		return byte;
			
	}

	public boolean setItem (Item item) {
		if (this.item!=null) 
			return false;
		this.item=item;
		return true;
	}

	public int isWalkable() {
		return this.(this.background.isWalkable()&&this.movable==null);
	}

	public int getBackgroundRepresentation() {
		return this.background.getImage();
	}
}
