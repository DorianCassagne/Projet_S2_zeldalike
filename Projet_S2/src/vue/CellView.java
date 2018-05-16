package vue;

import javafx.beans.property.IntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class CellView extends StackPane {

	final private ImageView background = new ImageView();

	final private ImageView Item = new ImageView();
	private IntegerProperty change;
	
	public CellView(IntegerProperty change) {
		super();
		this.change=change;
		this.getChildren().add(background);
		this.getChildren().add(Item);
	}
	
	public void check() {
		if (change.get() != 0) {
			if(change.get()>=300*VueConst.TILEMAPWHIDTH)
			
			
			change.set(0);
		}
	}
	
}
