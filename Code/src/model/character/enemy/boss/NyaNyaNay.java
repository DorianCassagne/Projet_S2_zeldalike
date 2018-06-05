package model.character.enemy.boss;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;


public class NyaNyaNay  extends NyaSlave {

 
	private final static double COEF=2.0;
	private final static int DEFALTIMG=40;
	private final static int DEFAULTCYCLE=30;
	//private final static int COEF=2.0;
	private static BooleanProperty dead=new SimpleBooleanProperty(false);
	private static IntegerProperty def =new SimpleIntegerProperty(10);
	private static IntegerProperty hp=new SimpleIntegerProperty(200);
	
	
	boolean test;
	private NyaSlave slave1;
	public NyaNyaNay(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn, DEFAULTCYCLE, COEF, DEFALTIMG, hp, def, dead, DEFAULTCYCLE);
		this.slave1= new NyaSlave(map, startRow, startColumn+1, 1, COEF,DEFALTIMG+1, hp, def, dead, DEFAULTCYCLE);
		test=true;
	}
	@Override
	protected Move act() {
		if (hp.get()<150&& test) {
			test=false;
			this.setMov(Movement.RIGHT);
			slave1.setMov(Movement.RIGHT);
			
		}
		return super.act();
	}

	

	
	

}
