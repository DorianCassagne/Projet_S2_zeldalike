package model;

public class StaticMethods {

	public static final String INVALIDNAMECODE = "Nom invalid";
	public static final String LESSTHANZEROCODE = "La valeur doit être supérieure à 0";
	public static final String NOTFOUNDCODE = "Valeur introuvable";
	public static final String NULLCODE = "La valeur est nulle";
	public static final String ILLEGALARGUMENTCODE = "Cette valeur n'est pas acceptable";
	
	
	public static boolean arrayContains(int[] array,int value) {
		for(int i = 0 ; i < array.length ; i++) {
			if(value == array[i]) {
				return true;
			}
		}
		return false;
	}
	
	
	public static int getIndex(String[] tab,String value) {
		
		if(value == null) {
			throw new IllegalArgumentException(NULLCODE);
		}
		
		else {
			value = value.toUpperCase();
			for(int i = 0 ; i < tab.length;i++) {
				if(tab[i].toLowerCase().equals(value)) {
					return i;
				}
			}
			return -1;
		}
	}
}
