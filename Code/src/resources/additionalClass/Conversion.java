package resources.additionalClass;
/*
 * classe Conversion qui permet de convertir un string en cha
 * 											 un string en int
 * 								   de recuperer un coefficient selon une dfference
 */	

public class Conversion {
	public final static char toChar(String s) {
		if(s.length() == 1)
			return s.charAt(0);
		else
			throw new IllegalArgumentException("PROBLEM WHILE CONVERTING TO CHAR AND THE ERROR IS IN : " + s);
	}

	public final static int toInt(String s) {
		int result ;
		try {
			result = Integer.parseInt(s);
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException ("PROBLEM OCCURED WHILE CONVERTING TO INT OF : " + s);
		}
		
		return result;
	}
	
	public final static int getNegatifCoefficient(int diff) {
		int coefficient = 1;
		if(diff < 0)
			coefficient = -1;
		else if(coefficient == 0)
			coefficient = 0;
		return coefficient;
	}
}
