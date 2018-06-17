package resources.additionalClass;
/*
 * classe qui regroupe des methode de fusion de tableau
 */
public class Fusion {

	public static Integer[] fuseIntegerWithArray(Integer[] values,Integer ... lastValues) {
		Integer[] newArray = new Integer[values.length + lastValues.length];
		
		fuseIntegerArray(newArray,values,0);
		fuseIntegerArray(newArray,lastValues,values.length);
		
		return newArray;
	}
	
	/*
	 * methode qui fusionne deux tableaux
	 */
	public static void fuseIntegerArray(Integer[] destArray,Integer[] sourceArray,int startIndex) {
		for(int i = 0 ; i < sourceArray.length;i++) {
			destArray[i+startIndex] = sourceArray[i];
		}
	}
	
}
