package resources.additionalClass;

public class UsefulMethods {

	public static boolean isCharInCharList(char toTest,char ... correctValues) {
		for(int i = 0 ; i < correctValues.length; i++) {
			if(toTest == correctValues[i]) {
				return true;
			}
		}
		return false;
	}
	
	
	public static boolean compareDouble(double firstOne,double secondOne) {
		System.out.println("I compare : "+firstOne +" with "+secondOne);
		return (Math.abs(firstOne - secondOne) > 0.001);
	}
}
