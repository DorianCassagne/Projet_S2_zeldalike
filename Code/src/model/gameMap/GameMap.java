package model.gameMap;

/*
 * Classe GameMap
 */
import javafx.beans.binding.IntegerBinding;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.enemy.Enemy;
import model.character.item.Item;
import model.character.npc.TalkingNPC;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.NewMovable;
import model.gameMap.additional.Statics;
import model.gameMap.cell.Cell;
import model.gameMap.move.Action;
import model.gameMap.move.ExternalMover;
import model.gameMap.move.Move;
import resources.additionalClass.UsefulMethods;

public class GameMap {	
	
	
	private static IntegerProperty realScore;
	private static IntegerBinding safeScore;

	private final IntegerProperty changeProperty;
	private final IntegerProperty safeChangeProperty;
	private Cell[] cells ;
	private Action action;
	private int idMap;
	
	static {
		realScore = new SimpleIntegerProperty(0);
		safeScore = realScore.add(0);
	}
	
	public static IntegerBinding getScoreBinding() {
		return safeScore;
	}
	
	private static void addScore(Enemy enemy) {
		if(enemy != null )
			realScore.set(realScore.get() + enemy.getScore());
	}
	

	
	//Cr�e une map en se r�f�rant � un fichier csv qui initilialise les fond des cases
	//Dans ce cas l� il n'est autoris� qu'un layer, d�clenche une exception si le fichier n'est pas valid
	public GameMap(int idMap,String ... mapPath) {
		
		Integer[][] values = MapReader.readAndConvertMapFile(mapPath);
		this.idMap = idMap;
		this.action = new Action();
		this.changeProperty = new SimpleIntegerProperty();
		this.safeChangeProperty = UsefulMethods.copyIntegerProperty(this.changeProperty);
		initialiseCells(values);
	
	}
	

	public IntegerProperty changeProperty() {
		return this.safeChangeProperty;
	}
	

	
	/*
	 * Change la case du personnage, la case de depart et d'arrives doivent etre passees en parametre pour reduire le temps de calcul
	 * Renvoie vrai si le changement a ete bien effectue, cela veut dire que la case destination accepte le personnage en movement
	 * Renvoie faux si la case d'arrive ou de depart n'est pas une case disponible ou incorrecte.
	*/
	
	public boolean changeCell(GameCharacter character,int currentRow,int currentColumn,int endRow,int endColumn,ExternalMover mover) {
		boolean moved = mover != null && this.changeCell(character, currentRow, currentColumn, endRow, endColumn);
		if(moved) {
			this.action.addMove(endRow,endColumn,mover.getSpeed(),character);
		}
		return moved;
	}
	
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
	
	public boolean addItem(Item item,int cellId ) {
		boolean added = this.cells[cellId].setItem(item);
		return added;
	}
	
	public void clearBackgroundConstraint(int cellId,model.scenario.action.Action action,int replace) {
		if(action != null && action.isActive())
			this.cells[cellId].setToWalkable(replace);
	}
	
	public boolean containsItemAt(int cellId) {
		return this.cells[cellId].containsItem();
	}
	
	//Initialise l'ensemble des celluls de la map
	private void initialiseCells(Integer[][] values) {
		this.cells = new Cell[Statics.MAPFULLSIZE];
		Integer[][] backValues = Statics.getBackgroundValues(values);
		Integer[] itemValue = Statics.getItemValue(values);
		
		for(int i = 0 ; i < Statics.MAPFULLSIZE ;i++) {
			createCell(i,backValues[i],itemValue[i]);
		}
		
	}
	
	private void createCell(Integer cellId,Integer[] backValue,int itemValue) {
		cells[cellId] = new Cell(backValue,itemValue,this.changeProperty,cellId);			
	}

	
	/*
	* Ajoute un caractere a la Map,le caractere s'ajoute a la map en s'ajoutant a une case
	* Les caracteres a ajouter sont ajoute dans une liste qui doit etre la au moment du tour
	* Renvoie faux si la case d'arrive n'est pas correcte ou non disponible
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
	 * Ajoute une attaque a la map en indiquant sa case de depart
	 * Declenche une erreur si l'attaque n'a pas d autre place sur la case, car l'identifiant de case est incorrecte
	 */
	public boolean backWalkableAt(int id) {
			return cells[id].backWalkable();
		}
	
	public void  addAttack(Attack attack,int row,int column) {
		if(Statics.isInMap(row,column)) {
			int cellId = Statics.convertToCellId(row,column);
			this.action.addMovable(attack,cellId);
		}
		else {
			throw new IllegalArgumentException("ENDCELL NOT FOUND");
		
		}
	}	
	
	public void addNPC(TalkingNPC npc,int row,int column) {
		if(Statics.isInMap(row,column)) {
			int cellId = Statics.convertToCellId(row, column);
			this.cells[cellId].setNPC(npc);
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
	 * Renvoie une valeur qui represente l'effet emis par l'attaque dans une case donnee
	 */
	public byte playAttack(Attack attack,int row,int column) {
		
		byte value = Attack.NOTPLAYED;
		if(Statics.isInMap(row,column) && attack != null) {
			int endCellId = Statics.convertToCellId(row, column);
			value = this.cells[endCellId].addAttack(attack);
		}else {
			this.action.delAttack(attack);
		}
		return value;
	}

	/*
	 * Retire un caractere de la liste courante dans la map
	 */
	public void delEnemy(Enemy character,int row,int column) {
		if(this.action.deleteMovableFromList(character)) {
			int cellId = Statics.convertToCellId(row, column);
			this.cells[cellId].removeMovable();
			addScore(character);
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
	
	public void clearAttack(int row,int column,Attack attackToClear) {
		if(Statics.isInMap(row, column))
			this.cells[Statics.convertToCellId(row, column)].clearAttack(attackToClear);
	}
	
	public int[] getRemovedCharacter() {
		return this.action.getRemovedCharacter();
	}

	public void talkTo(int row, int column) {
		if(Statics.isInMap(row, column)) {
			this.cells[Statics.convertToCellId(row, column)].talk();
		}	
	}
	
	public int getMapId() {
		return this.idMap;
	}
	
}

