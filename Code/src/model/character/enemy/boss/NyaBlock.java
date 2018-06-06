package model.character.enemy.boss;

import java.awt.Image;
import java.util.Random;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class NyaBlock  extends GameCharacter{
	private boolean att;
	private int img;
	private Random ran = new Random();
	public NyaBlock(GameMap map, int startRow, int startColumn, int cycle, double coefficient, int defaultImage,Movement mov) {
		super(map, startRow, startColumn, cycle, coefficient, defaultImage+mov.getIndex());
		img=mov.getIndex();
		att=false;
		// TODO Auto-generated constructor stub
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
			att = false;
		}
		
		else if (ran.nextInt(100)==2) {
			setWait(100);
			setImage(img-1);
			att=true;
		}
			
		return null;
	}

	
}
