package vue.gameClass;

import controler.Controleur;
import javafx.beans.binding.IntegerBinding;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewUsefulMethods {
	public static void linkPropertyToLabelAndProgress(IntegerBinding property,Label label,ProgressBar progress) {
		double maxValue = property.get();
		System.out.println(label + " has being linked");
		label.setText(property.getValue().toString());

		property.addListener(
				(obs,oldValue,newValue)->{
					System.out.println("My value was changed to ");
					label.setText(newValue.toString());
					progress.setProgress(property.get()/maxValue);
				}
		);
		
	}
	
	public static void linkImage(IntegerBinding imageValueProperty, ImageView imageView) {
		imageValueProperty.addListener(
				(obs,oldValue,newValue)->{
					Image newImage = Controleur.TEXTURE.getImg(newValue.intValue());
					imageView.setImage(newImage);
				}
		);
	}


}
