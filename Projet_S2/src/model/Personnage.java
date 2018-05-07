package model;

import javafx.beans.property.*;

public abstract class Personnage {
	private final static String[] IMAGESNAMES = {"fairyBbackHilight.png","fairyface.png","fairyprofil.png"};
	private IntegerProperty posX;
	private IntegerProperty posY;
	private String nom;
	private StringProperty representationImage;
	private boolean movementPossible;
	
	
	public Personnage(String nom,int posX,int posY) {
		if(posX >= 0 && posY >= 0 && nom != null) {
			this.posX = new SimpleIntegerProperty(posX);
			this.posY = new SimpleIntegerProperty(posY);
			this.nom = nom;
			this.representationImage = new SimpleStringProperty(IMAGESNAMES[0]);
			this.movementPossible = true;
		}
	}
	

	
	
	
}
