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
		this(map, row, column, direction,dmg,3 );

	}
	public DoomAttack(GameMap map, int row, int column, Movement direction, int dmg, int size) {
		this(map, row, column, direction,dmg ,size, false);

	}
	public DoomAttack(GameMap map, int row, int column, Movement direction, int dmg, int size, boolean entire) {
		this(map, row, column, direction,size,dmg , true, 0, entire);
		

	}
	
	private DoomAttack(GameMap map, int row, int column, Movement direction, int maxdistance, int dmg, boolean reproduce, int inc, boolean entire) {
		super(map,30, row, column, direction, dmg, 1, 2, 1608, 0);
		this.inc=inc;
		this.reproduce=reproduce;
		lifeTime=maxdistance;
		this.entire=entire;
		this.duplicate();
		if (entire&&lifeTime>=0&&this.reproduce) {

			int x = this.getDirection().getHorizontalIncrement();
//			if (x==0)
//				x=1;
			int y = this.getDirection().getVerticalIncrement();
//			if (y==0)
//				y=-1;
			new DoomAttack(this.getMyMap(),this.getRow()+x*(inc+1)-y,this.getColumn()-y*(inc+1)-x,this.getDirection(),0,this.getDamage(), false, 0, false);
		}
	}

	
	public boolean duplicate() {

		lifeTime--;
		inc++;
		if (lifeTime>=0) {
			int x = this.getDirection().getHorizontalIncrement();
			int y = this.getDirection().getVerticalIncrement();
			//if(lifeTime>0)
				new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),this.getDirection(),lifeTime,this.getDamage(), this.reproduce, inc, this.entire);
//				if (entire&&lifeTime>=0) {
//					System.out.println("entireeee");
//					new DoomAttack(this.getMyMap(),this.getRow()+x*(inc+2),this.getColumn()-y*(inc+2),this.getDirection(),0,this.getDamage(), false, 0, false);
//				}

			if(this.reproduce) {
				for( int i =1; i<=inc; i++) {
					new DoomAttack(this.getMyMap(),this.getRow()+x*i,this.getColumn()+y*i,this.getDirection(),0,this.getDamage(), false, 0, false);
					new DoomAttack(this.getMyMap(),this.getRow()-x*i,this.getColumn()-y*i,this.getDirection(),0,this.getDamage(), false, 0, false);
				}
//				if (entire&&lifeTime>0) {
//					System.out.println("entireeee");
//					new DoomAttack(this.getMyMap(),this.getRow()+x*(inc+1),this.getColumn()-y*(inc+1),this.getDirection(),0,this.getDamage(), false, 0, false);
//				}
			}
		}
	
		return true;
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		//System.out.println("Damage is " + this.getDamage());
		gameCharacter.getDmg(this);
		
	}

}
