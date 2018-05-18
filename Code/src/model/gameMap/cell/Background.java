package model.gameMap.cell;


public class Background {

	private int value;
	private int imageValue;
	private boolean isWalkable;
	
	public Background() {
		this.isWalkable=false;
	}
	
	//Renvoie une IllegalArgumentException, si un des parametres est faux
		public Background(int value,boolean isWalkable, int image) throws IllegalArgumentException { 
			if(value < 0 || image < 0) {
				throw new IllegalArgumentException("FALSE ARGUMENT ON BACKGROUND");
			}else {
				this.value=value;
				this.isWalkable=isWalkable;
				this.imageValue= image;
			}
		}	
		public int getValue() {
			return value;
		}
		public Background(int imageValue) {
			this.imageValue=imageValue;
			
		}
		
		//retourne vrai si le fond est traversable, sinon renvoie faux.
		public boolean getIsWalkable () {
			return isWalkable;
		}

		//renvoie l�image qui renvoie la valeur de l�image
		public int getImageValue() { 
			return this.imageValue;
		}
	
	public void setBackground(int imgVal) {
		this.imageValue=imgVal;
		/*this.isWalkable=BackgroundFactory.getWalkableImgVal(imgVal);*/ 
	}
}