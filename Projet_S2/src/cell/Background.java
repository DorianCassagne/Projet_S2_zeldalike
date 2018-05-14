package cell;

import additionalMethods.ErrorCodes;
import additionalMethods.Tile;

public class Background {
	
	static enum BackgroundMeaning {
		back1(false,0),
		back2(true,1539),
		back3(true,1),
		table1(false,963),
		table2(false,964),
		table3(false,962),
		table4(false,1155),
		table5(false,1154),
		table6(false,1156),
		cuisine1(false,1729),
		cuisine2(false,1730),
		cuisine3(false,1728),
		cuisine4(false,1536),
		cuisine5(false,1538),
		cuisine6(false,1537),
		cuisine7(false,1344),
		cuisine8(false,1345),
		cuisine9(false,1346),
		frigo(false,768),
		frigo2(false,960);
		
		private boolean canWalk;
		private int id ;
		
		BackgroundMeaning(boolean canWalk,int id) {
			this.canWalk = canWalk;
			this.id = id;
		}
		
		public static BackgroundMeaning getMeaningFromIntegerValue(int value) {
			for(BackgroundMeaning sign : values()){
				if(sign.id == value)
					return sign;
			}
			return null;
		}
		
	
	};	
	
	private BackgroundMeaning backgroundValue;

	
	public Background(BackgroundMeaning backgroundValue) {
		if(backgroundValue != null) {
			this.backgroundValue = backgroundValue;
		}
		else 
			throw new IllegalArgumentException(ErrorCodes.INVALIDNAMECODE);
	}
	
	public Background(int backgroundValue) {
		this(BackgroundMeaning.getMeaningFromIntegerValue(backgroundValue));
	}
		
	@Override
	public String toString() {
		return this.backgroundValue.toString() ;
	}
	
	
	public boolean estTraversable() {
		return this.backgroundValue.canWalk;
	}

	public int[] getTile() {
		return Tile.getTileInImage(this.backgroundValue.id);
	}

	
	
	
}
