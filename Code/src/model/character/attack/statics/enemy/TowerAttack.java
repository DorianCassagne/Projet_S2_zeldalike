package model.character.attack.statics.enemy;
/*
 * Classe d'attaque de type missile, attaque rectiligne horizontalement verticalement en diaognale  
 * applique un malus de deplacement au heros 
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;



public class TowerAttack extends Attack{

	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTDMG = 80;
	private final static int CELLPERTURN = 1;
	private final static int DEFAULTIMAGE = 1704;
	private final static int MAXDISTANCE = 7;
	private final static double DEFAULTCOEFFICIENT = 1.4;
	private final static int DURATION = 10;
	

	
	public TowerAttack(GameMap map,int row, int column, Movement direction) {
		super(map, DEFAULTCYCLE, row, column, direction, DEFAULTDMG, CELLPERTURN, DEFAULTCOEFFICIENT, DEFAULTIMAGE, MAXDISTANCE);
	}
	/*
	 * Applique des degats a applique un malus de deplacement pendant un lapse de temps
	 * 
	 */
	
	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		if(gameCharacter == GameCharacter.getHero()) {
		
			gameCharacter.setCoefficient(0.3);
			gameCharacter.getDmg(this);
			
			//Nombre de thread autorisé est 1 .
			ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
			
	       scheduler.schedule(new Runnable() {
	            @Override
	            public void run() {
	                gameCharacter.setCoefficient(GameCharacter.DEFAULTCOEFFICENT);
	        }}, DURATION, TimeUnit.SECONDS);
		}
	}

}
