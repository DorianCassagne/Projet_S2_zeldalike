package model;

import javafx.beans.property.*;

public abstract class Personnage {
	private final static String[] IMAGESNAMES = {"fairyBbackHilight.png","fairyface.png","fairyprofil.png"};
	private String nom;
	private IntegerProperty caseProperty;
	private StringProperty representationImage;
	private boolean movementPossible;
	
	
	public Personnage(String nom) {
		if(nom != null) {
			this.nom = nom;
			this.representationImage = new SimpleStringProperty(IMAGESNAMES[0]);
			this.movementPossible = true;
		}
	}
	
	final IntegerProperty caseProperty() {
		IntegerProperty property = new SimpleIntegerProperty();
		property.bind(this.caseProperty);
		return property;
	}
	
	public abstract void bouger() ;
	
	private void setRepresentationImage(String imageName) {
		if(imageName != null)
			representationImage.set(imageName);
	}
	
	public void toTop() {
		setRepresentationImage(getTopImage());
	}
	
	public void toRight() {
		setRepresentationImage(getRightImage());
	}
	
	public void toBottom() {
		setRepresentationImage(getBottomImage());
	}
	
	public void toLeft() {
		setRepresentationImage(getLeftImage());
	}
	
	protected abstract String getTopImage() ;
	
	protected abstract String getRightImage() ;
	
	protected abstract String getBottomImage() ;
	
	protected abstract String getLeftImage() ;
	
	
	@Override
	public String toString() {
		return " a pour nom : " + this.nom;
	}
	
	
	

	
	
	
}
