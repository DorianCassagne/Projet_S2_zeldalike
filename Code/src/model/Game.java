package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import model.character.hero.CopyOfHeroStats;
import model.character.hero.Hero;
import model.gameMap.GameMap;
import model.gameMap.MapEnum;
import model.gameMap.additional.NewMovable;
import model.gameMap.move.Move;
import model.scenario.Scenario;
/*
 * Classe Jeu
 *  
 */
public class Game {
	
	public final static int HEROKEY = 0;
	private static boolean isABigMap ;
	private static Scenario scenario;
	
	
	
	private BooleanProperty mapChangeProperty;
	private GameMap myMap;
	private Hero hero;
	private StringProperty messageProperty;
	private GameStatus gameStat;
	
	//Initialise le jeu avec une map
	
	
	private void setBigMap(boolean isBig) {
		isABigMap = isBig;
	}
	
	public static boolean isABigMap() {
		return isABigMap;
	}
	
	
	
	public static String saveScenario() {
		return scenario.saveScenario();
	}
	
	public Game(StringProperty messageText,GameStatus gameStat ) {
		
		this.mapChangeProperty = new SimpleBooleanProperty(true);
		this.messageProperty = messageText;
		this.gameStat = gameStat;
		
		if(gameStat == null)
			this.changeMap(0,null);
		
		else
			this.changeMap(gameStat.getMapId(),this.gameStat.getScenarioPath());
			
			
	}

	/*
	 * Methode qui recoit en parametre un index de map (valide dans l'enumeration) correspondant a la prochaine map
	 *  change la map de la game
	 * 	charge le scenario correspondant
	 * 	place le hero a la case de spawn de la map 
	 */
	
	private void changeMap(int mapIndex,String scenarioSave) {
		if(mapIndex >= 0 && mapIndex < MapEnum.values().length) {
			
			MapEnum mapHash = MapEnum.values()[mapIndex];
			setBigMap(mapHash.getIsBig());
			
			if(this.gameStat != null) {
				this.myMap = new GameMap(this.gameStat.getScore(),mapIndex,mapHash.getLayers());
				createHero(this.gameStat.getRow(), this.gameStat.getColumn());
			}
			
			else {
				
				this.myMap = new GameMap(mapIndex,mapHash.getLayers());
				createHero(mapHash.getPosY(),mapHash.getPosX());
			
			}

			scenario = new Scenario(mapHash.getScenario(),scenarioSave,this.messageProperty,this.myMap);
			this.mapChangeProperty.set(!this.mapChangeProperty.get());
			
			
		}
	}
	
	/*
	 * Methode qui place le hero sur la map 
	 */
	private void createHero(int startRow,int startColumn) {
		
		if(this.hero == null) {
			this.hero = new Hero(this.myMap,startRow,startColumn,this.gameStat);
			this.hero.mapChangerProperty().addListener(
					(obs,oldValue,newValue)->changeMap(newValue.intValue(),null)
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
		Move[] listMove = this.myMap.turn();
		scenario.run();
		return listMove;
	}
	
	//renvoie vrai si le jeu est arrivé à sa fin
	public boolean end() {
		return !this.hero.isAlive();
	}
	
	public void communiquerMovement(char moveChar) {
		this.hero.setNextMove(moveChar);
	}
	
	public int[] getRemovedMovable() {
		return this.myMap.getRemovedCharacter();
	}
	
	
	public IntegerProperty getMapChangeProperty() {
		return this.myMap.changeProperty();
	}
	
	public CopyOfHeroStats getHeroStats() {
		return this.hero.getHeroStats();
	}
		
	
}
