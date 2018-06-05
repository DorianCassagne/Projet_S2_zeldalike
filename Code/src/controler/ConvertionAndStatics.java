package controler;

import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import model.gameMap.additional.MapReader;



public class ConvertionAndStatics {
	
	public final static int TILEDIMENSION = 32;
	public final static int ROWINDEX = 0;
	public final static int COLUMNINDEX = 1 ;

	
	
	public static int[] convertToViewSize(int cellId) {
		int row = (cellId / MapReader.MAPLENGTH)*TILEDIMENSION;
		int column = (cellId%MapReader.MAPLENGTH)*TILEDIMENSION;
		
		int[] position = {row,column};
		return position;
	}

	public static void  fixPaneDimension(int dimension,TilePane tile,Pane container) {
		container.setMinHeight(dimension);
		container.setMaxHeight(dimension);
		
		container.setMinWidth(dimension);
		container.setMaxWidth(dimension);
		
		tile.setPrefColumns(MapReader.MAPLENGTH);
		tile.setPrefRows(MapReader.MAPLENGTH);
	
	}

	
}
