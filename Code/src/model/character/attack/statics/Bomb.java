package model.character.attack.statics;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class Bomb extends Attack {

	private int count;
	private boolean start;
	public final static int DEFAULTIMAGE = 1620;
	
	public Bomb(GameMap map, int row, int column, Movement direction) {
		super(map, 40, row/*+direction.getVerticalIncrement()*/, column/*+direction.getHorizontalIncrement()*/, direction, 100, 1, 2, DEFAULTIMAGE, 7);
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
