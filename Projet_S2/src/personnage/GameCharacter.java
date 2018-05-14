package model;

import javafx.beans.property.*;

public abstract class Personnage {
	private String nom;
	private StringProperty representationImage;
	private StringProperty copyOfRepresentationImage;
	private Map myMap ;
	
	public Personnage(String nom,String startImage,Map map,int caseDebutX,int caseDebutY) {
		if(nom != null && map != null) {
			this.nom = nom;
			this.representationImage = new SimpleStringProperty(startImage);
			this.myMap = map;
			this.myMap.addToList(this, caseDebutX, caseDebutY);
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
		this.myMap.etablirDeplacement(this, Map.Deplacement.TOP);
	}
	public  void toLeft() {
		this.setRepresentationImage(this.getLeftImage());
		this.myMap.etablirDeplacement(this, Map.Deplacement.LEFT);
	}
	
	public  void toBottom() {
		this.setRepresentationImage(this.getBottomImage());
		this.myMap.etablirDeplacement(this, Map.Deplacement.BOTTOM);
	}
	
	public  void toRight() {
		this.setRepresentationImage(this.getRightImage());
		this.myMap.etablirDeplacement(this, Map.Deplacement.RIGHT);
	}
	
	
	public abstract String getTopImage();
	public abstract String getBottomImage();
	public abstract String getLeftImage();
	public abstract String getRightImage();
	
	public String getNom() {
		return this.nom;
	}
	
	

	
	
	
}
