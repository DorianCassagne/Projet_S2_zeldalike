package controler.conversion;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import model.gameMap.additional.MapReader;

/*
 * Cette classe contient les methodes statiques utilises par le controleur, cette classe peut servir a  : 
 * 	->Convertir un identifiant de case coordonnee x,y
 *  ->fixer la hauteur et la largeur du tilePane et du Pane
 */

public class ConversionAndStatics {
	
	public final static int TILEDIMENSION = 32;
	public final static int ROWINDEX = 0;
	public final static int COLUMNINDEX = 1 ;

	
	
	/*
	 * Convertit en pixel l'identifiant d'une case
	 * @param int cellId : l'identifiant de la case 
	 * @return int[] : coordonnée x,y associé a l'id de la case 
	 */

	public static int[] convertToViewSize(int cellId) {
		int row = (cellId / MapReader.MAPLENGTH)*TILEDIMENSION;
		int column = (cellId%MapReader.MAPLENGTH)*TILEDIMENSION;
		
		int[] position = {row,column};
		return position;
	}

	
	/*
	 * Fixe la dimension  du pane passee en parametre et definit le nombre de case de TilePane passe en parametre 
	 * @param int : definit la dimension en pixel du pane et du tilepane
	 * @param TilePane tile : c'est le tilePane Ã  qui on definira le nombre de case
	 * @param Pane container : c'est le pane contenant le tilePane
	 */
	
	public static void fixPaneDimension(int dimension,TilePane tile,Pane container) {
		container.setMinHeight(dimension);
		container.setMaxHeight(dimension);
		
		container.setMinWidth(dimension);
		container.setMaxWidth(dimension);
		
		tile.setPrefColumns(MapReader.MAPLENGTH);
		tile.setPrefRows(MapReader.MAPLENGTH);
	
	}

	
}
