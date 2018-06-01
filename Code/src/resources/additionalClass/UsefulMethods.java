package resources.additionalClass;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
		return (Math.abs(firstOne - secondOne) > 0.001);
	}
	
	public static IntegerProperty copyIntegerProperty(IntegerProperty source) {
		IntegerProperty copy = new SimpleIntegerProperty();
		copy.bind(source);
		return copy;
	}
}
