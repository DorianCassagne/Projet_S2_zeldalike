package cell;

import additionalMethods.ErrorCodes;

public class Decoration {

	static enum DecorationMeaning{
		NEIGE,PIERRE
	}
	
	
	private DecorationMeaning decorationValue;
	
	public Decoration(DecorationMeaning value) {
		if(value != null)
			this.decorationValue = value;
		else
			throw new IllegalArgumentException(ErrorCodes.INVALIDNAMECODE);
	}
	
	
	@Override 
	public String toString() {
		return this.decorationValue.toString();
	}
	

}
