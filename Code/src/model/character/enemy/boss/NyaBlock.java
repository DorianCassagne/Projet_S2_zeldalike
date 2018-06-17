package model.character.enemy.boss;
/*
 * Classe d'ennemi NyaBlock 
 * Ennemi intuable ( se desactive / meurt lorsque le boss meurt ) 
 * ne se déplace pas 
 * Attaque horizontalement de type NyanAttHori
 * animation: 2 images, la banane s'épluche lorsqu'elle est sur le point d'attaque 
 *
 */

import java.util.Random;
import model.character.attack.Attack;
import model.character.attack.statics.boss.Missile;
import model.character.attack.statics.boss.NyanAttHori;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class NyaBlock  extends Enemy{
	private final static int DEFAULTCYCLE = 3;
	private final static double DEFAULTCOEF = 1;
	private final static int DEFAULTIMG = 48;
	private boolean att;
	private int img;
	private Random ran = new Random();
	private boolean mov;
	
	public NyaBlock(GameMap map, int startRow, int startColumn,Movement mov,int score) {
		super(map, startRow, startColumn, DEFAULTCYCLE, DEFAULTCOEF, DEFAULTIMG+mov.getIndex(),score);
		img=mov.getIndex()+1;
		att=false;
		this.mov=mov==Movement.LEFT;
	}
//
//	public NyaBlock(GameMap map, int cycle, int row, int column, int damage, int cellPerTurn,
//			double coefficient, int defaultImage, int maxDistance) {
//		super(map, cycle, row, column, Movement.STAY, damage, cellPerTurn, coefficient, defaultImage, maxDistance);
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	protected void establishAttack(GameCharacter gameCharacter) {
//		// TODO Auto-generated method stub
//		
//	}
//	
//	@Override
//	protected boolean handleMove(byte i) {
//		
//		return true;
//		
//	}

	@Override
	public void launchAttack(Movement move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDmg(Attack attack) {
		
	}

	@Override
	public boolean isAlive() {
		return true;
	}

	@Override
	protected Move act() {
		if (att) {
			setImage(img);
			new NyanAttHori(getMyMap(), getRow(), getColumn(), mov);
			//new Missile(getMyMap(), getRow(), getColumn(), Movement.RIGHT);
			att = false;
		}
		
		else if (ran.nextInt(150)==0) {
			setWait(150);
			setImage(img-1);
			att=true;
		}
			
		return null;
	}

	@Override
	public int getHP() {
		return Integer.MAX_VALUE;
	}

	@Override
	public String getName() {
		
		return "NYABLOCK";
		
	}

	
}
