package model;

public class Background {
	private Signification value;
	static enum Signification {
		back1(false,0),
		back2(true,1539),
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
		;
		
		private boolean estTraversable;
		private int numero ;
		
		Signification(boolean traversable,int numero) {
			this.estTraversable = traversable;
			this.numero = numero;
		}
		
		public static Signification getSignification(int value) {
			for(Signification sign : values()){
				if(sign.numero == value)
					return sign;
			}
			return null;
		}
		
	
	};	
	
	public Background(Signification value) {
		if(value != null) {
			this.value = value;
		}
		else 
			throw new IllegalArgumentException(ErrorCodes.INVALIDNAMECODE);
	}
	
	public Background(int value) {
		this(Signification.getSignification(value));
	}
		
	@Override
	public String toString() {
		return this.value.toString() ;
	}
	
	
	public boolean estTraversable() {
		return this.value.estTraversable;
	}

	public int[] getTile() {
		return Tile.getTileInImage(this.value.numero);
	}

	
	
	
}
