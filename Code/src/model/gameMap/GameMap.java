package model.gameMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.NewMovable;
import model.gameMap.additional.Statics;
import model.gameMap.cell.Cell;
import model.gameMap.move.Action;
import model.gameMap.move.Move;

public class GameMap {	
	
	private final IntegerProperty changeProperty;
	private final IntegerProperty safeChangeProperty;
	private Cell[] cells ;
	private Action action;
	
	//Cr�e une map en se r�f�rant � un fichier csv qui initilialise les fond des cases
	//Dans ce cas l� il n'est autoris� qu'un layer, d�clenche une exception si le fichier n'est pas valid
	public GameMap(String ... mapPath) {
		
		int[][] values = MapReader.readAndConvertMapFile(mapPath);
		this.action = new Action();
		this.changeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty.bind(this.changeProperty);
		initialiseCells(values);
	
	}
	
	
	/*
	 * Change la case du personnage, la case de d�part et d'arriv�s doivent �tre pass�es en param�tre pour r�duire le temps de calcul
	 * Renvoie vrai si le changement a été bien effectu�, cela veut dire que la case destination accepte le personnage en movement
	 * Renvoie faux si la case d'arrivée ou de départ n'est pas une case disponible ou incorrecte.
	*/
	
	public boolean changeCell(GameCharacter character,int currentRow,int currentColumn,int endRow,int endColumn) {
		int endCellId = Statics.convertToCellId(endRow,endColumn);
		boolean changed = false;
		if(Statics.isInMap(endRow,endColumn) ) {
			changed = this.cells[endCellId].isWalkable();
			if(changed ) {
				int startCellId = Statics.convertToCellId(currentRow,currentColumn);
				if(Statics.isInMap(currentRow,currentColumn)) {
					this.cells[startCellId].removeMovable();
					this.cells[endCellId].addMovable(character);
					character.setCellId(endRow, endColumn);
				}
				else
					changed = false;
			}
		}
		return changed;	
	}
	
	//Initialise l'ensemble des celluls de la map
	private void initialiseCells(int[][] values) {
		this.cells = new Cell[Statics.MAPFULLSIZE];
		int[][] backValues = Statics.getBackgroundValues(values);
		int[] itemValue = Statics.getItemValue(values);
		
		for(int i = 0 ; i < Statics.MAPFULLSIZE ;i++) {
			createCell(i,backValues[i],itemValue[i]);
		}
	}
	
	private void createCell(int cellId,int[] backValue,int itemValue) {
		cells[cellId] = new Cell(backValue,itemValue);			
		cells[cellId].changeProperty().addListener(
				(obs,oldValue,newValue)->{
					//TODO DORIAN
				}
		);
	}

	
	/*
	* Ajoute un caract�re � la Map,le caract�re s'ajoute � la map en s'ajoutant � une case
	* Les caract�res � ajouter sont ajouter dans une liste qui doit �tre l� au moment du tour
	* Renvoie faux si la case d'arriv�e n'est pas correcte ou non disponible
	*/
	public boolean addCharacter(GameCharacter movable,int row,int column) {	
		boolean correctlyPlaced = false;
		
		if(Statics.isInMap(row,column)) {
			int cellId = Statics.convertToCellId(row, column);
			correctlyPlaced = this.cells[cellId].addMovable(movable);
			if(correctlyPlaced) {
				this.action.addMovable(movable,cellId);
			}
		}
		
		return correctlyPlaced;
	}
	
	
	/*
	 * Ajoute une attaque � la map en indiquant sa case de d�part
	 * D�clenche une erreur si l'attaque n'a pas p� �tre plac� sur la case, car l'identifiant de case est incorrecte
	 */
	
	public void  addAttack(Attack attack,int row,int column) {
		if(Statics.isInMap(row,column)) {
		
			int cellId = Statics.convertToCellId(row,column);
			this.action.addMovable(attack,cellId);
		
		}
		else {
			
			throw new IllegalArgumentException("ENDCELL NOT FOUND");
		
		}
	}	
	
	public Integer[] getLayerForCell(int cellId) {
		return this.cells[cellId].getCellBackgroundLayer();
	}	
	
	public boolean isWalkableAt(int id) {
		return this.cells[id].isWalkable();
	}
	
	/*
	 * Renvoie une valeur qui représente l'effet emis par l'attaque dans une case donn�e
	 */
	public byte playAttack(Attack attack,int row,int column) {
		
		byte value = Attack.NOTPLAYED;
		if(Statics.isInMap(row,column)) {
			int endCellId = Statics.convertToCellId(row, column);
			value = this.cells[endCellId].attack(attack);
		}else {
			this.action.delAttack(attack);
		}
		return value;
	}

	/*
	 * Retire un caract�re de la liste courante dans la map
	 */
	public void delCharacter(GameCharacter character,int row,int column) {
		if(this.action.deleteMovableFromList(character)) {
			int cellId = Statics.convertToCellId(row, column);
			this.cells[cellId].removeMovable();
		}
	}
	
	public void delAttack(Attack attack) {
		this.action.delAttack(attack);
	}
	
	public NewMovable[] getNewCharList() {
		return this.action.getNewCharList();
	}
	
	public Move[] turn() {
		return this.action.turn();
	}
	
	public int[] getRemovedCharacter() {
		return this.action.getRemovedCharacter();
	}
}

