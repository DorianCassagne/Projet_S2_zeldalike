package personnage;

import javafx.beans.property.*;
import mapZelda.Map;
import mapZelda.Map.Deplacement;
import mapZelda.Move;

public abstract class GameCharacter {

	private String name;
	private StringProperty representationImage;
	private StringProperty copyOfRepresentationImage;
	private Map myMap ;
	
	public GameCharacter(String name,String startImage,Map map,int startRow,int startColumn) {
		if(name != null && map != null) {
			this.name = name;
			this.representationImage = new SimpleStringProperty(startImage);
			this.myMap = map;
			this.myMap.addToCharList(this, startRow, startColumn);
		}
	}
	
	
	public StringProperty representationImageProperty() {
		this.copyOfRepresentationImage = new SimpleStringProperty();
		copyOfRepresentationImage.bind(this.representationImage);
		return copyOfRepresentationImage;
	}
	
	
	private void setRepresentationImage(String imageName) {
		if(imageName != null)
			representationImage.set(imageName);
	}
	
	public  void toTop() {
		this.setRepresentationImage(this.getTopImage());
		this.myMap.moveCharacter(this, Move.TOP);
	}
	
	public  void toLeft() {
		this.setRepresentationImage(this.getLeftImage());
		this.myMap.moveCharacter(this, Move.LEFT);
	}
	
	public  void toBottom() {
		this.setRepresentationImage(this.getBottomImage());
		this.myMap.moveCharacter(this, Move.BOTTOM);
	}
	
	public  void toRight() {
		this.setRepresentationImage(this.getRightImage());
		this.myMap.moveCharacter(this, Move.RIGHT);
	}
	
	
	public abstract String getTopImage();
	public abstract String getBottomImage();
	public abstract String getLeftImage();
	public abstract String getRightImage();
	
	public String getNom() {
		return this.name;
	}
	
	

	
	
	
}
