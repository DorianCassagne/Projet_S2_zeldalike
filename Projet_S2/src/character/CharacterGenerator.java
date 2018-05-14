package character;

import javafx.beans.property.StringProperty;
import map.GameMap;
import texture.TexturePack;

public class CharacterGenerator {
	public static GameCharacter genChar(int val, GameMap map, TexturePack textu, Hero hero, int x, int y) {
		GameCharacter gc=null;
		switch(val) {
		case 100:
			gc =hero;
			gc.relocate(x, y);
			break;
		}
		return gc;
		}
}
