package resources.additionalClass;

public class Fusion {

	public static Integer[] fuseIntegerWithArray(int[] values,int ... lastValues) {
		Integer[] newArray = new Integer[values.length + lastValues.length];
		
		fuseIntegerArray(newArray,values,0);
		fuseIntegerArray(newArray,lastValues,values.length);
		
		return newArray;
	}
	
	
	public static void fuseIntegerArray(Integer[] destArray,int[] sourceArray,int startIndex) {
		for(int i = 0 ; i < sourceArray.length;i++) {
			destArray[i+startIndex] = sourceArray[i];
		}
	}
	
}
