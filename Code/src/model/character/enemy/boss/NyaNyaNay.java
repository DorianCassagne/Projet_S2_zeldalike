package model.character.enemy.boss;

import java.util.Random;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.attack.statics.Bomrang;
import model.character.attack.statics.DoomAttack;
import model.character.attack.statics.SimpleArrow;
import model.character.attack.statics.boss.NyanAttHori;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;


public class NyaNyaNay  extends NyaSlave {

 
	private final static double COEF=2.0;
	private final static int DEFALTIMG=40;
	private final static int DEFAULTCYCLE=4;
	//private final static int COEF=2.0;
	private static BooleanProperty dead=new SimpleBooleanProperty(false);
	private static IntegerProperty def =new SimpleIntegerProperty(10);
	private static IntegerProperty hp=new SimpleIntegerProperty(200);
	
	
	boolean attCote;
	private int randomInt;
	private Random ran;
	private NyaSlave slave1;
	private int maxPos;
	private boolean att;
	private int positionLeft;
	public NyaNyaNay(GameMap map, int startRow, int startColumn, int sliderSize) {
		super(map, startRow, startColumn, DEFAULTCYCLE, COEF, DEFALTIMG, hp, def, dead, DEFAULTCYCLE);
		this.slave1= new NyaSlave(map, startRow, startColumn+1, 1, COEF,DEFALTIMG+1, hp, def, dead, DEFAULTCYCLE);
		setWait(200);
		this.maxPos=(sliderSize*2)+1;
		positionLeft= startColumn-sliderSize;
		ran=new Random();
		att=true;
		randomInt= ran.nextInt(5);
	}
	@Override
	protected Move act() {
		if (attCote&&super.getMov()==Movement.STAY) {
			
			new NyanAttHori(this.getMyMap(), this.getRow(), this.getColumn());
			attCote=false;
		}
			
		else {
			attCote=true;
			if (att) {
				switch (ran.nextInt(3)) {
				case 0:
					new SimpleArrow(getMyMap(), this.getRow(), this.getColumn(),Movement.BOTTOM, 20, 1 );
					setWait(20);
					break;
				case 1:
					new Bomrang(getMyMap(), this.getRow(), this.getColumn()+1, Movement.BOTTOM);
					setWait(50);
					break;
				case 2:
					new DoomAttack(getMyMap(), this.getRow(), this.getColumn()+1, Movement.BOTTOM, 50);
					setWait(30);
					break;
				}
				if (randomInt == 0) {
					att=false;
					randomInt = ran.nextInt(maxPos);
					randomInt += positionLeft;
					setWait(50);//wait apres suite dattaque
				}
				else 
					randomInt--;
			}
			else {
				//System.out.println("mv"+randomInt+"  "+this.getColumn());
				if (randomInt == this.getColumn()) {
					setWait(30);
					//randomInt=2;
					randomInt= ran.nextInt(5)+1;//nb dattaque max
					att=true;
				}
				if (randomInt<this.getColumn()) {
					this.setMov(Movement.LEFT);
				}
				if (randomInt>this.getColumn()) {
					this.setMov(Movement.RIGHT);
				}
					
				
			}
		}
		return super.act();
	}

	protected void setMov(Movement mov) {
		super.setMov(mov);
		slave1.setMov(mov);
	}

	
	

}
