package model.character.attack.statics;

import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class DoomAttack extends Attack {

	private int inc;
	private boolean reproduce;
	private int lifeTime;
	public boolean entire=false;
	public DoomAttack(GameMap map, int row, int column, Movement direction, int dmg) {
		this(map, row, column, direction, 3,dmg , true, -1, false);

	}
	public DoomAttack(GameMap map, int row, int column, Movement direction, int dmg, int size) {
		this(map, row, column, direction,size,dmg , true, -1, false);

	}
	public DoomAttack(GameMap map, int row, int column, Movement direction, int dmg, int size, boolean entire) {
		this(map, row, column, direction,size,dmg , true, -1, entire);
		

	}
	
	protected DoomAttack(GameMap map, int row, int column, Movement direction, int maxdistance, int dmg, boolean reproduce, int inc, boolean entire) {
		super(map,30, row, column, direction, dmg, 1, 2, 1608, 0);
		this.inc=inc;
		this.reproduce=reproduce;
		lifeTime=maxdistance;
		this.entire=entire;
		this.duplicate();
	}

	
	public boolean duplicate() {

		lifeTime--;
		inc++;
		System.out.println(inc);
		if (lifeTime>=0) {
			int x = this.getDirection().getHorizontalIncrement();
			int y = this.getDirection().getVerticalIncrement();
			if(lifeTime>0)
				new DoomAttack(this.getMyMap(),this.getRow()+y,this.getColumn()+x,this.getDirection(),lifeTime,this.getDamage(), this.reproduce, inc, this.entire);
		

			if(this.reproduce) {
				for( int i =1; i<=inc; i++) {
					new DoomAttack(this.getMyMap(),this.getRow()+x*i,this.getColumn()+y*i,this.getDirection(),0,this.getDamage(), false, 0, false);
					new DoomAttack(this.getMyMap(),this.getRow()-x*i,this.getColumn()-y*i,this.getDirection(),0,this.getDamage(), false, 0, false);
				}
				if (entire&&lifeTime>0)
					new DoomAttack(this.getMyMap(),this.getRow()+x*(inc+1),this.getColumn()-y*(inc+1),this.getDirection(),0,this.getDamage(), false, 0, false);
			}
		}
	
		return true;
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		gameCharacter.getDmg(this);
		
	}

}
