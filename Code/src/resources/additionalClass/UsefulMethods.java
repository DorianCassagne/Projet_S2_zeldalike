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
}
