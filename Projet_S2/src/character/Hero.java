package character;

public class Hero extends GameCharacter{
	public Hero(int hpMax) {
		super(hpMax);
		this.onANewMapp();
	}
	private boolean endMap;
	
//	public Hero() {
//		this.onANewMapp();
//	}

	
	public void onANewMapp(){
		this.endMap=false;
	}
	public boolean endOfMap() {
		return endMap;
	}
	public boolean alive() {
		return (hp>0);
	}
}
