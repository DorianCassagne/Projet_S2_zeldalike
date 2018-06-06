package model.character.attack.statics;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.cell.Cell;
import model.gameMap.move.Movement;

public class Bomrang extends Attack {

	private int count;
	private boolean start;
	public Bomrang(GameMap map, int row, int column, Movement direction) {
		super(map, 40, row/*+direction.getVerticalIncrement()*/, column/*+direction.getHorizontalIncrement()*/, direction, 100, 1, 2, 1620, 10);
		count = 6;
	}

	@Override
	public boolean handlePlay(byte attackResult) {
		if(count!=0||!start) {
			count--;
			start=true;
		}
		else {
			count=7;
			new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.BOTTOM,2,2, true);
			new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.TOP,2,2, true);
			new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.LEFT,2,2, true);
			new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),Movement.RIGHT,2,2, true);
			this.removeCharacter();
		}
		return attackResult % Cell.CHARACTERISPRESENT != 0 && attackResult % Cell.NOTWALKABLE != 0;
	}

	@Override
	public void attack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
	}
	
	

	

	
}
