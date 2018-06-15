package model.gameMap.move;

import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import resources.additionalClass.Conversion;
import resources.additionalClass.UsefulMethods;

public enum Movement {
	

	TOP(-Statics.STEP,0),
	RIGHT(0,Statics.STEP),
	BOTTOM(Statics.STEP,0),
	LEFT(0,-Statics.STEP),
	
	STAY(0,0),
	DIAGTR(-Statics.STEP,Statics.STEP),
	DIAGTL(-Statics.STEP,-Statics.STEP),
	DIAGDR(Statics.STEP,Statics.STEP),
	DIAGDL(Statics.STEP,-Statics.STEP);
	
	private final static int MAXPOSSIBILERANGE = 66;
	
	private int verticaly;
	private int horizontaly;
	
	Movement(int verticaly,int horizontaly){
		this.verticaly = verticaly;
		this.horizontaly = horizontaly;
	}
	
	public int getVerticalIncrement() {
		return this.verticaly;
	}
	
	public int getHorizontalIncrement() {
		return this.horizontaly;
	}
	
	public int getIndex() {
		return this.ordinal();
	}
	
	public static Movement getDirectionInto(int cellStart,int cellEnd) {
		int diff = (cellStart - cellEnd);
		int isNegative = Conversion.getNegatifCoefficient(diff);
		diff = (Math.abs(cellStart - cellEnd))%(MAXPOSSIBILERANGE);
		Movement movement;
		
		switch(diff * isNegative) {
		case 64 :
			movement = Movement.BOTTOM;
		case -64 :
			movement = Movement.TOP;
		case 1 :
			movement = Movement.RIGHT;
		case -1 :
			movement = Movement.LEFT;
		case 65 :
			movement = Movement.DIAGTR;
		case 63 :
			movement = Movement.DIAGTL;
		case -65 :
			movement = Movement.DIAGDR;
		case -63 :
			movement = Movement.DIAGDL;
		case 0 :
			movement = Movement.STAY;
		default :
			movement = null;
		}
		
		return movement;

		
	}
	
	

}
