package cell;

import additionalMethods.ErrorCodes;
import item.Item;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import personnage.GameCharacter;

public class Cell {
	public final static char CHARCHANGE = 'c';
	public final static char ITEMCHANGE = 'i';
	public final static char DECORATIONCHANGE = 'd';
	public final static char INSIGNIFICANTCHAR = ' ';
	
	private Background background;
	private GameCharacter currentChar;
	private Item item;
	private Decoration decoration;
	private BooleanProperty changeProperty;
	private final BooleanProperty safeProperty;
	private char lastChange;
	
	/*
	 * Une case ne peut contenir qu'un item ou une decoration, pas les deux en même temps
	 */
	
	public Cell(int backgroundCode) {
		this.safeProperty = new SimpleBooleanProperty();
		this.background = new Background(backgroundCode);
		this.changeProperty = new SimpleBooleanProperty();
		this.lastChange = INSIGNIFICANTCHAR;
		
	}
	
	public Cell(Integer backgroundCode,Integer decorationCode,Integer itemCode) {
		this.safeProperty = new SimpleBooleanProperty();
	}	
	
	
	public boolean isEmpty() {
		return this.currentChar == null && this.background.estTraversable();
	}
	
	public void addCharacter(GameCharacter character) throws IllegalArgumentException{
		if(!this.isEmpty() )
			throw new IllegalArgumentException(ErrorCodes.IMPOSSIBLEMOVE);
		else {
			this.currentChar = character;
			this.triggerChange(CHARCHANGE);
		}
	}
	
	private void triggerChange(char lastChange) {
		this.lastChange = lastChange;
		this.changeProperty.set(!this.changeProperty.get());
	}
	
	
	public BooleanProperty changeProperty() {
		this.safeProperty.unbind();
		this.safeProperty.bind(this.changeProperty);
		return this.changeProperty;
	}
	
	
	public void emptyCell() {
		this.currentChar = null;
		this.triggerChange(CHARCHANGE);
	}
	
	public Background getBackground() {
		return this.background;
	}
	
	public char getChangeCategory() {
		return this.lastChange;
	}
	
	public boolean containsCharacter(GameCharacter gameCharacter) {
		return this.currentChar == gameCharacter;
	}
}
