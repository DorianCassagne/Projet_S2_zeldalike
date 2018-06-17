package resources.additionalClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/*
 * Classe qui contient des methodes divers 
 */
public class UsefulMethods {
	
	/*
	 * methode qui retourne vrai si un char est dans une suite
	 */
	public static boolean isCharInCharList(char toTest,char ... correctValues) {
		for(int i = 0 ; i < correctValues.length; i++) {
			if(toTest == correctValues[i]) {
				return true;
			}
		}
		return false;
	}
	/*
	 * methode qui compare des nombres
	 */
	public static boolean compareDouble(double firstOne,double secondOne) {
		return (Math.abs(firstOne - secondOne) > 0.001);
	}
	
	public static boolean isBetween(double value,double firstParam,double secondParam) {
		return value >= firstParam && value < secondParam;
	}
	
	/*
	 * Methode qui copie un integerproperty
	 */
	public static IntegerProperty copyIntegerProperty(IntegerProperty source) {
		IntegerProperty copy = new SimpleIntegerProperty();
		copy.bind(source);
		return copy;
	}
	
	/*
	 * methode qui affiche le contenu d'un tableau
	 */
	public static void showIntegerTab(Integer[] tab) {
		System.out.println("Start");
		for(Integer i : tab) {
			System.out.print(i + "\t" );
		}
		
		System.out.println("End");
	}
}
