package model.character.enemy.boss;

import java.util.Random;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import model.character.GameCharacter;
import model.character.attack.statics.boss.NyanAttHori;
import model.character.attack.statics.hero.arrow.SimpleArrow;
import model.character.attack.statics.hero.bomb.Bomb;
import model.character.attack.statics.hero.doomAttack.DoomAttack;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
/*
 * Classe de boss Nyanyan
 * 		
 * 		se deplace de fa�on aleatoirement seulement horizontalement
 * 		attaque de facon aleatoire devant lui selon 3 attaques : SimpleArrow, Bomb ou DoomAttack
 * 		tout en attaquant constamment de fa�on horizontale avec NyanAttHori		
 * 		est compose de 2 nyaslave qui partages des points de vie en commun
 * 		qui bouge ensemble 
 */
import model.scenario.action.Action;


public class NyaNyaNay  extends NyaSlave {

 
	private final static double COEF=2.0;
	private final static int DEFALTIMG=64;
	private final static int DEFAULTCYCLE=4;
	private final static int DEFAULTSCORE = 350;
	private static BooleanProperty dead = new SimpleBooleanProperty(false);
	private static IntegerProperty def = new SimpleIntegerProperty(100);
	private static IntegerProperty hp = new SimpleIntegerProperty(1200);
	
	
	boolean attCote;
	private int randomInt;
	private Random ran;
	private NyaSlave slave1;
	private int maxPos;
	private boolean att;
	private int positionLeft;
	
	//boss nyan cat l'ancant 3 attaque differentes mais ne se deplace que de coté il est compose de 2 block
	public NyaNyaNay(GameMap map, int startRow, int startColumn, int sliderSize) {
		
		super(map, startRow, startColumn, DEFAULTCYCLE, COEF, DEFALTIMG, hp, def, dead, DEFAULTCYCLE,DEFAULTSCORE);
		this.slave1= new NyaSlave(map, startRow, startColumn+1, 1, COEF,DEFALTIMG+1, hp, def, dead, DEFAULTCYCLE,DEFAULTSCORE);
		setWait(200);
		this.maxPos=(sliderSize*2)+1;
		positionLeft= startColumn-sliderSize;
		ran=new Random();
		att=true;
		randomInt= ran.nextInt(5);
	}
	
	/*
	 * Lance l'attaque horizontale 
	 * et choisi une attaque aleatoirement
	 * et choisi de se deplacement aleatoirement
	 * @see model.character.enemy.boss.NyaSlave#act()
	 */
	@Override
	protected Move act() {
		if (attCote&&super.getMov()==Movement.STAY|| GameCharacter.getHero().getRow()==this.getRow()) {
			
			new NyanAttHori(this.getMyMap(), this.getRow(), this.getColumn());
			attCote=false;
		}
			
		else {
			attCote=true;
			if (att) {
				switch (ran.nextInt(3)) {
				case 0:
					new SimpleArrow(getMyMap(), this.getRow(), this.getColumn(),Movement.BOTTOM, 1);
					setWait(20);
					break;
				case 1:
					new Bomb(getMyMap(), this.getRow(), this.getColumn()+1, Movement.BOTTOM,50);
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
				if (randomInt == this.getColumn()) {
					setWait(30);

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

	@Override
	public void removeCharacter(Action action) {
		super.removeCharacter(action);
		this.slave1.removeCharacter(action);
	}

}
