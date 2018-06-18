package model.character.attack.statics.hero.bomb;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.attack.statics.hero.doomAttack.DoomAttack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Bomb extends Attack {

	private int count;
	private boolean start;
	public final static int DEFAULTIMAGE = 1620;
	private final static int CELLPERTURN = 1;
	private final static int MAXDISTANCE = 6;
	final static int DEFAULTDAMAGE = 50;
	private final static int DEFAULTCOEFFICIENT = 2; 
	
	
	public Bomb(GameMap map, int row, int column, Movement direction,int attackPT) {
		super(map, 40, row, column, direction, Attack.getAttaqueValue(attackPT, DEFAULTDAMAGE), CELLPERTURN, DEFAULTCOEFFICIENT, DEFAULTIMAGE, MAXDISTANCE);
		count = 6;
	}

	@Override
	public boolean handleMove(byte attackResult) {
		if(count!=0||!start) {
			count--;
			start=true;
		}
		else {
			count=7;
			this.setImage(-1620);
			this.explode();
		}
		return super.handleMove(attackResult);
	}
	
	public void establishAttack(GameCharacter gameCharacter) {

		gameCharacter.getDmg(this);
//		Movement direction1 = Movement.values()[(this.getDirection().getIndex() + 1)%4 ];
//		Movement direction2 = Movement.values()[(this.getDirection().getIndex() + 3)%4];
//		new AttackTest(this.getMyMap(),this.getRow(),this.getColumn(),direction1,80);
//		new AttackTest(this.getMyMap(),this.getRow(),this.getColumn(),direction2,80);
	}
	
	private void explode() {

		new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.BOTTOM,2,2, true);
		new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.TOP,2,2, true);
		new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.LEFT,2,2, true);
		new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.RIGHT,2,2, true);
	}
	

	

	
}
