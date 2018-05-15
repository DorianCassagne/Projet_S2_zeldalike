package mapZelda;

import java.util.HashMap;

import additionalMethods.ErrorCodes;
import cell.Background;
import cell.Cell;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import personnage.GameCharacter;

public class Map {

	public final static int ROWINDEX = 0;
	public final static int COLUMNINDEX = 1;
	public static final int STEP = 1 ;
	public final static int TRASHINDEX = Integer.MAX_VALUE;
	
	private Cell[][] cells ;
	private HashMap<Integer,GameCharacter> charList ;
	private IntegerProperty changeProperty;
	private final IntegerProperty safeProperty;
	private int idCharacter;
	private Cell voidCell;
	
	
	
	public Map(String mapName) throws IllegalArgumentException {
		this.safeProperty = new SimpleIntegerProperty();
		MapReader map = new MapReader(mapName);
		this.changeProperty = new SimpleIntegerProperty();
		this.charList = new HashMap<Integer,GameCharacter>();
		this.idCharacter = 0;
		this.voidCell = new Cell(0);
		initialiseCells(map);
	}
	
	
	public boolean addToCharList(GameCharacter character,int startCellRow,int startCellColumn) {
		boolean added = false;
		if(!this.charList.containsValue(character)) {
			this.charList.put(this.idCharacter, character);
			try {
				this.voidCell.emptyCell();
				this.cells[startCellRow][startCellColumn].addCharacter(character);
			}catch(IllegalArgumentException e) {
				this.charList.remove(this.idCharacter);
			}
			this.idCharacter++;
			added = true;
		}
		return added;
	}
	
	private void initialiseCells(MapReader map) {
		int mapHeight = map.getNbTilesHeight();
		int mapWidth = map.getNbTilesWidth();
		this.cells = new Cell[mapHeight][mapWidth];
		this.voidCell.changeProperty().addListener(
				(obs,oldValue,newValue)->{
					this.changeProperty.set(TRASHINDEX);
				}
		);
		
		
		for(int i = 0 ; i < mapHeight;i++) {
			int row = i;
			for(int j = 0 ; j < mapWidth;j++) {
				int column = j;
				this.cells[i][j] = new Cell(map.getRepresentation(i, j));	
				this.cells[i][j].changeProperty().addListener(
						(obs,oldValue,newValue)->{
							this.changeProperty.set(convertToCellId(row, column));
						}
				);
			}
		}
		
		
	}
	
	public int getMapWidth() {
		if(this.cells.length > 0)
			return this.cells[0].length;
		else
			return 0;
	}
	
	public int getMapHeight() {
		return this.cells.length;
	}
	
	
	public Background getBackground(int row,int column) {
		return this.cells[row][column].getBackground();
	}
	
	public StringProperty characterImageNameProperty(int keyPerso) {
		return this.charList.get(keyPerso).representationImageProperty();
	}
	
	public char getCellChangeCategory(int caseIndex) {
		if(caseIndex == TRASHINDEX) {
			return this.voidCell.getChangeCategory();
		}
		int[] position = this.convertFromCellId(caseIndex);
		int row = position[ROWINDEX];
		int column = position[COLUMNINDEX];
		return this.cells[row][column].getChangeCategory();
	}
	
	
	public IntegerProperty changeProperty() {
		this.safeProperty.unbind();
		this.safeProperty.bind(this.changeProperty);
		return this.safeProperty;
	}
	
	void addCharacter(GameCharacter p) {
		boolean set = false;
		Double d;
		while(!set) {
			d = Math.random() * this.getMapHeight();
			int startX = d.intValue() ;
			d = Math.random() * this.getMapWidth() ;
			int startY = d.intValue();
			set = this.addToCharList(p, startX, startY);
		}
	}
	
	
	
	public void moveCharacter(GameCharacter currentCharacter,Move move) throws IllegalArgumentException {
		setLastCharacter(currentCharacter);
		if(this.idCharacter != ErrorCodes.INVALIDINDEX) {	
			int[] startLocation = getCurrentLocation(currentCharacter);
			int[] endLocation = {startLocation[ROWINDEX] + move.getVerticalIncrement(),startLocation[COLUMNINDEX] + move.getHorizontalIncrement()};
			this.moveCharacterTo(currentCharacter,startLocation,endLocation);
		}else {
			throw new IllegalArgumentException(ErrorCodes.IMPOSSIBLEMOVE);
		}
	}
	
	
	
	private void moveCharacterTo(GameCharacter currentCharacter, int[] startLocation,int[] endLocation) throws IllegalArgumentException,ArrayIndexOutOfBoundsException{
		
		int xi = startLocation[ROWINDEX];
		int yi = startLocation[COLUMNINDEX];
		int xf = endLocation[ROWINDEX];
		int yf = endLocation[COLUMNINDEX];
		
		try {
			
			Cell currentCell = this.cells[xi][yi];
			Cell nextCell = this.cells[xf][yf];
			
			if(nextCell.isEmpty()) {
				currentCell.emptyCell();
				nextCell.addCharacter(currentCharacter);
			}
			
			else
				throw new IllegalArgumentException(ErrorCodes.IMPOSSIBLEMOVE);
		
		}catch(ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException();
		}
	}
	
	
	private int[] getCurrentLocation(GameCharacter currentCharacter) throws IllegalArgumentException {
		
		for(int row = 0 ; row < this.getMapHeight();row++) {
			for(int column = 0 ; column < this.getMapWidth();column++) {
				if(this.cells[row][column].containsCharacter(currentCharacter)) {
					int[] location = {row,column};
					return location;
				}
			}
		}
		
		throw new IllegalArgumentException(ErrorCodes.NOTFOUNDCODE);
	}
	
	private void setLastCharacter(GameCharacter currentCharacter) {
		this.idCharacter = this.getPersoIndex(currentCharacter);
	}
	
	public int getPersoIndex(GameCharacter p) {
		
		for(Integer key : this.charList.keySet()) {
			if(this.charList.get(key) == p) {
				return key;
			}
		}
		
		return ErrorCodes.INVALIDINDEX;
	}
	
	public int getLastChangedCharacterId() {
		return this.idCharacter;
	}
	
	public Integer[] getCharacterListKeys() {
		return this.charList.keySet().toArray(new Integer[this.charList.keySet().size()]);
	}
	
	public StringProperty getCharacterImageProperty(Integer key) {
		return this.charList.get(key).representationImageProperty();
	}
	
	public char getLastChangeType(int cellId) {
		int[] location =  this.convertFromCellId(cellId);
		int row = location[0];
		int column = location[1];
		return this.cells[row][column].getChangeCategory();
	}
	
	public int[] convertFromCellId(int caseId) {
		int row = caseId / this.getMapWidth();
		int column = caseId % this.getMapWidth();
		int[] converted = {row,column};
		return converted;
	}
	
	public int convertToCellId(int row,int column) {
		return row * this.getMapWidth() + column;
	}
	
	public GameCharacter getCharacterByKey(Integer key) {
		return this.charList.get(key);
	}
	
}
