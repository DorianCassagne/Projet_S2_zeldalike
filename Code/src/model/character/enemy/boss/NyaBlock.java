package model.character.enemy.boss;
/*
 * Classe d'ennemi NyaBlock 
 * Ennemi intuable ( se desactive / meurt lorsque le boss meurt ) 
 * ne se d�place pas 
 * Attaque horizontalement de type NyanAttHori
 * animation: 2 images, la banane s'�pluche lorsqu'elle est sur le point d'attaque 
 *
 */

import java.util.Random;
import model.character.attack.Attack;
import model.character.attack.statics.boss.NyanAttHori;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class NyaBlock  extends Enemy{
	private final static int DEFAULTCYCLE = 3;
	private final static double DEFAULTCOEF = 1;
	private final static int DEFAULTIMG = 48;
	private final static int SCORE = 600;
	private boolean att;
	private int img;
	private Random ran = new Random();
	private boolean mov;
	
	public NyaBlock(GameMap map, int startRow, int startColumn,Movement mov) {
		super(map, startRow, startColumn, DEFAULTCYCLE, DEFAULTCOEF, DEFAULTIMG+mov.getIndex(),SCORE);
		img=mov.getIndex()+1;
		att=false;
		this.mov=mov==Movement.LEFT;
	}

	@Override
	public void launchAttack(Movement move) {
		setImage(img);
		new NyanAttHori(getMyMap(), getRow(), getColumn(), mov);
		//new Missile(getMyMap(), getRow(), getColumn(), Movement.RIGHT);
		att = false;
		
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
			this.launchAttack(null);
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
