package vue.gameClass;

import controler.Controleur;
import javafx.beans.binding.IntegerBinding;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewUsefulMethods {
	public static void linkPropertyToLabelAndProgress(IntegerBinding property,IntegerBinding propertyMax,Label label,ProgressBar progress) {
		label.setText(property.getValue().toString());
		property.addListener(
				(obs,oldValue,newValue)->{
					label.setText(newValue.toString());
					progress.setProgress(newValue.doubleValue()/propertyMax.get());
				}
		);
		propertyMax.addListener(
				(obs,oldValue,newValue)->{
					progress.setProgress(property.get()/newValue.doubleValue());
				}
		);
	}
	
	public static void linkImage(IntegerBinding valueProperty,IntegerBinding imageValueProperty, ImageView imageView,Button button,String textPrepend,Tooltip imageTooltip) {

		setImage(imageValueProperty.get(),imageView);
		button.setTooltip(imageTooltip);
		imageTooltip.setText("Aucun");
		valueProperty.addListener(
				(obs,oldValue,newValue)->{
					setImage(newValue.intValue(),imageView);
					imageTooltip.setText(textPrepend + " " + newValue.intValue() + " pts"); 
				}
		);
	}
	
	private static void setImage(Integer imageValue,ImageView imageView ) {
		Image newImage = Controleur.TEXTURE.getImg(imageValue);
		imageView.setImage(newImage);

	}


}
