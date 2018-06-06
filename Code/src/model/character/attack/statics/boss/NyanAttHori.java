package model.character.attack.statics.boss;


import model.gameMap.GameMap;
import model.gameMap.move.Movement;

public class NyanAttHori extends NyanHorRainbow{
	private final static double DEFAULTCOEF = 2.0;
	private final static int DEFAULTCYCLE = 1;
	private final static int DEFAULDAMAGE = 3;
	
	public NyanAttHori(GameMap map, int row, int column) {
		super(map, DEFAULTCYCLE, row, column+1, Movement.RIGHT,DEFAULDAMAGE, DEFAULTCOEF, 1624);
		new NyanHorRainbow(map, DEFAULTCYCLE, row, column, Movement.LEFT,DEFAULDAMAGE, DEFAULTCOEF, 1627);
	}

	public NyanAttHori(GameMap map, int row, int column, boolean left) {
			super(map, DEFAULTCYCLE, row, column+1, (left)?Movement.LEFT:Movement.RIGHT,DEFAULDAMAGE, DEFAULTCOEF, (left)?1026:1624);
		//new NyanHorRainbow(map, DEFAULTCYCLE, row, column, Movement.LEFT,DEFAULDAMAGE, DEFAULTCOEF, 1625);
	}
	

}






/*
public class DoomAttack extends Attack {

private int inc;
private boolean reproduce;
private int lifeTime;
public boolean done;
public DoomAttack(GameMap map, int row, int column, Movement direction, int dmg) {
	this(map, row, column, direction, 3, 30, true, 0);

}

protected DoomAttack(GameMap map, int row, int column, Movement direction, int maxdistance, int dmg, boolean reproduce, int inc) {
	super(map,30, row, column, direction, dmg, 1, 2, 1608, 0);
	this.inc=inc;
	this.reproduce=reproduce;
	lifeTime=maxdistance;
//	byte i = 0;
//	handlePlay(i);
	byte i =0;
	this.handlePlay(i);
}

@Override
public boolean handlePlay(byte attackResult) {

	//Movement direction1 = Movement.values()[(this.getDirection().getIndex() + 1)%4 ];
	//Movement direction2 = Movement.values()[(this.getDirection().getIndex() + 3)%4];
	lifeTime--;
	inc++;
	System.out.println(inc);
	if (lifeTime>=0) {
		new DoomAttack(this.getMyMap(),this.getRow(),this.getColumn(),this.getDirection(),lifeTime,this.getDamage(), this.reproduce, inc);
	

		int x = this.getDirection().getHorizontalIncrement();
		int y = this.getDirection().getVerticalIncrement();
		if(this.reproduce) {
			for( int i =1; i<=inc; i++) {
				new DoomAttack(this.getMyMap(),this.getRow()+x*i,this.getColumn()+y*i,this.getDirection(),0,this.getDamage(), false, 0);
				new DoomAttack(this.getMyMap(),this.getRow()-x*i,this.getColumn()-y*i,this.getDirection(),0,this.getDamage(), false, 0);
			}
		}
	}

	return true;
}

@Override
public void attack(GameCharacter gameCharacter) {
	gameCharacter.getDmg(this);

}

}
*/