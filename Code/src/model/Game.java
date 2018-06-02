package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import model.character.Hero;
import model.gameMap.GameMap;
import model.gameMap.MapEnum;
import model.gameMap.additional.NewMovable;
import model.gameMap.move.Move;

public class Game {
	public final static int HEROKEY = 0;
	
	private BooleanProperty mapChangeProperty;
	private GameMap myMap;
	private Hero hero;
	
	//Initialise le jeu avec une map
	
	public Game() {
		this(0);
	}
	
	public Game(int mapIndex) {
		this.mapChangeProperty = new SimpleBooleanProperty(true);
		this.changeMap(mapIndex);
	}

	
	private void changeMap(int mapIndex) {
		if(mapIndex >= 0 && mapIndex < MapEnum.values().length) {
			MapEnum mapHash = MapEnum.values()[mapIndex];
			this.myMap = new GameMap(mapHash.getLayers());
			createHero(mapHash.getPosY(),mapHash.getPosX());
			this.mapChangeProperty.set(!this.mapChangeProperty.get());
		}
	}
	
	private void createHero(int startRow,int startColumn) {
		if(this.hero == null) {
			this.hero = new Hero(this.myMap,startRow,startColumn);
			this.hero.mapChangerProperty().addListener(
					(obs,oldValue,newValue)->changeMap(newValue.intValue())
			);
		}
		else {
			this.hero.changeMap(this.myMap,startRow, startColumn);
		}
	}
	
	public final BooleanProperty changeMapProperty() {
		return this.mapChangeProperty;
	}
		
	
	//renvoie l'identifiant du fond pour une cellude donnée.
	public Integer[] getLayerForCell(int cellId) {
		return this.myMap.getLayerForCell(cellId);
	}
	
	//renvoie la liste des caractères crées pendant un tour 
	public NewMovable[] getNewPlayers() {
		return this.myMap.getNewCharList();
	}
	
	//renvoie la liste des movements effectués pendant un tour
	public Move[] turn() {
		return this.myMap.turn();
	}
	
	//renvoie vrai si le jeu est arrivé à sa fin
	public boolean end() {
		return !this.hero.isAlive();
	}
	
	public void communiquerMovement(char moveChar) {
		hero.setNextMove(moveChar);
	}
	
	public int[] getRemovedMovable() {
		return this.myMap.getRemovedCharacter();
	}
	
	
	public IntegerProperty getMapChangeProperty() {
		return this.myMap.changeProperty();
	}
	
	
	
}
