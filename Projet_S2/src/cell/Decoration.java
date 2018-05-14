package model;

public class Decoration {

	static enum Signification{
		NEIGE,PIERRE
	}
	
	
	
	private Signification value;
	
	public Decoration(Signification value) {
		if(value != null)
			this.value = value;
		else
			throw new IllegalArgumentException();
	}
	
	
	@Override 
	public String toString() {
		return this.value.toString();
	}
	

}
