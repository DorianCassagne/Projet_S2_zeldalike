package model.gameMap.move;

import model.gameMap.additional.Statics;

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
		
		int rowDifference = Statics.convertToRow(cellEnd) - Statics.convertToRow(cellStart);
		int columnDifference = Statics.convertToColomn(cellEnd) - Statics.convertToColomn(cellStart);
		int verticalIncreement = 0;
		int horizontalIncreement = 0;
		int coefficient = 2;
		
		
		if(rowDifference < 0 && columnDifference > 0)
			verticalIncreement = 0;
		else if(columnDifference < 0 && rowDifference < 0)
			verticalIncreement = 8;
		else if(rowDifference > 0)
			verticalIncreement = 4;
				
		if(columnDifference > 0)
			horizontalIncreement = 2;
		else if(columnDifference < 0)
			horizontalIncreement = 6;
		
		int index = (horizontalIncreement + verticalIncreement) / coefficient ; 
		
		return Movement.getMovementInOrder(index);

		
	}
	
	public static Movement getMovementInOrder(int order) {
		Movement movement;
		
		switch(order ) {
		case 0 :
			movement = Movement.TOP;
			break;
		case 1 :
			movement = Movement.DIAGTR;
			break;
		case 2 :
			movement = Movement.RIGHT;
			break;
		case 3 :
			movement = Movement.DIAGDR;
			break;
		case 4 :
			movement = Movement.BOTTOM;
			break;
		case 5 :
			movement = Movement.DIAGDL;
			break;
		case 6 :
			movement = Movement.LEFT;
			break;
		case 7 :
			movement = Movement.DIAGTL;
			break;
		default:
			movement = Movement.STAY;
		}
		
		return movement;
	}
	
	
	public static Movement getOppositeOf(Movement actualMovement) {
		
		Movement opposite = Movement.STAY;
		
		if(actualMovement != Movement.STAY) {
			int index = actualMovement.getIndex();
			int offset = 0;
			
			if(index > Movement.STAY.ordinal()) {
				offset = 5;
			}
			
			int oppositeIndex = (index + 2) % 4 ;
			
			opposite = Movement.values()[offset + oppositeIndex];
			
		}
		
		return opposite;
		
	}

}
