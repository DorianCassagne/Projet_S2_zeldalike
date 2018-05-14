package model;
/*
 * Cette classe représente les points d'un personnage (Ses points de vie par exemple).
 * Cette classe a pour résponsabilités : 
 * 			-Créer un objet Point ayant un nom et un nombre de points au départ
 * 			-Permettre la réduction ou l'augmentation de ces points à l'aide d'un pourcentage
 * 			-Permettre la réduction ou l'augmentation des points par un nombre précis.
 *			-Afficher ce point avec un format spécifique
 *			-Vérifier si le nombre de points est égal à 0
 */


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Points {
	
	/*
	 * Ces propriétés ne doivent pas être strictement inférieures à 0.
	 */
	//Le nombre initial de points est inchangé
	private double initialVal;
	
	private DoubleProperty currentVal;
	
	//Le nom ne peut pas être null
	private String name;
	
	public Points(double initialVal ,String nom) {
		if(initialVal < 0 || nom == null) {
			throw new IllegalArgumentException("Nombre de points invalide");
		}else {
			this.initialVal = initialVal;
			this.currentVal = new SimpleDoubleProperty(initialVal);
			this.name = nom;
		}
	}
	
	//Le pourcentage doit toujours être supérieur à 100.  
	public void modifierDePourcentage(int percent) {
		if(percent > 0)
			this.currentVal.set((percent * initialVal)/100);
		else
			throw new IllegalArgumentException("Le pourcentage doit être > 0");
	}
	
	//Içi le paramètre valeur doit être supérieur à (-currentVal)
	public void augmenterPoints(double valeur) {
		if(valeur >= 0 )
			this.currentVal.set(this.currentVal.get() + valeur);
		else
			throw new IllegalArgumentException("La valeur doit être >= 0");
	}
	
	
	//Içi le nombre de points à réduire doit être inférieur à currentVal 
	public void reduirePoints(double valeur) {
		if(valeur >= 0 && valeur <= this.currentVal.get())
			this.currentVal.set(this.currentVal.get() - valeur);
		else
			throw new IllegalArgumentException("La valeur doit être >= 0");
	}
	
	public boolean estNul() {
		return this.currentVal.get() == 0;
	}
	
	@Override
	public String toString() {
		return this.name + " : " + this.currentVal.get() + "(" +getPercent() +  "%)" + " pts";
	}
	
	
	public int getPercent() {
		Double d = new Double(this.currentVal.get()*100/this.initialVal);
		return d.intValue();
	}
	
}
