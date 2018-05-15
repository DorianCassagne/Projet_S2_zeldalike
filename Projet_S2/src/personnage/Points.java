package personnage;
/*
 * Cette classe représente les points d'un personnage (Ses points de vie par exemple).
 * Cette classe a pour résponsabilités : 
 * 			-Créer un objet Point ayant un nom et un nombre de points au départ
 * 			-Permettre la réduction ou l'augmentation de ces points à l'aide d'un pourcentage
 * 			-Permettre la réduction ou l'augmentation des points par un nombre précis.
 *			-Afficher ce point avec un format spécifique
 *			-Vérifier si le nombre de points est égal à 0
 */


import additionalMethods.ErrorCodes;
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
	
	private DoubleProperty currentValue;
	
	//Le nom ne peut pas être null
	private String name;
	
	public Points(double initialValue ,String name) throws IllegalArgumentException {
		if(initialValue < 0 || name == null) {
			throw new IllegalArgumentException(ErrorCodes.ILLEGALARGUMENTCODE);
		}else {
			this.initialVal = initialValue;
			this.currentValue = new SimpleDoubleProperty(initialValue);
			this.name = name;
		}
	}
	
	//Le pourcentage doit toujours être supérieur à 100.  
	public void increaseByPercent(int percent) throws IllegalArgumentException{
		if(percent > 0)
			this.currentValue.set((percent * initialVal)/100);
		else
			throw new IllegalArgumentException(ErrorCodes.LESSTHANZEROCODE);
	}
	
	//Içi le paramètre valeur doit être supérieur à (-currentVal)
	public void increaseByValue(double incrementValue) throws IllegalArgumentException{
		if(incrementValue >= 0 )
			this.currentValue.set(this.currentValue.get() + incrementValue);
		else
			throw new IllegalArgumentException(ErrorCodes.LESSTHANZEROCODE);
	}
	
	
	//Içi le nombre de points à réduire doit être inférieur à currentVal 
	
	public void reducePointByValue(double valeur) throws IllegalArgumentException{
		if(valeur >= 0 && valeur <= this.currentValue.get())
			this.currentValue.set(this.currentValue.get() - valeur);
		else
			throw new IllegalArgumentException("La valeur doit être >= 0");
	}
	
	public boolean isZero() {
		return this.currentValue.get() == 0;
	}
	
	@Override
	public String toString() {
		//@TODO
		return null;
	}
	
	
	public int getPercent() {
		Double d = new Double(this.currentValue.get()*100/this.initialVal);
		return d.intValue();
	}
	
}
