package model.character.enemy.boss;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.character.enemy.Enemy;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class NyaSlave  extends Enemy{
	private static boolean done;
	private Movement mov;
	private BooleanProperty dead;
	private IntegerProperty hp;
	private IntegerProperty def;
	private int realcycle;
	
	protected NyaSlave(GameMap map, int startRow, int startColumn, int cycle, double coefficient, int defaultImage, IntegerProperty hp, IntegerProperty def, BooleanProperty dead, int realCycle,int score) {
		
		super(map, startRow, startColumn, cycle, coefficient, defaultImage,score);
		this.dead=dead;		
		this.hp=hp;
		this.def=def;
		this.realcycle=realCycle;
		mov= Movement.STAY;
		done = true;
	
	}

	

	@Override
	public void launchAttack(Movement move) {
		
	}

	@Override
	public void getDmg(Attack attack) {

		this.hp.set(this.hp.get() - GameCharacter.calculateDamage(attack.getDamage(), def.get()));
		if (hp.get()<=0)
			this.kill();
		
	}
	
	protected void setMov(Movement mov) {
		if (mov==null)
			this.mov=Movement.STAY;
		else 
			if(done)
				this.mov=mov;
				
		
	}
	

	@Override
	public boolean isAlive() {
		return !dead.get();
	}

	@Override
	protected Move act() {
		if(mov==Movement.STAY)
			return null;
		
		int r = this.getRow() + mov.getVerticalIncrement();
		int c= this.getColumn() + mov.getHorizontalIncrement();
		if(this.getMyMap().changeCell(this, this.getRow(), this.getColumn(), r, c)) {
			mov=Movement.STAY;
			setWait(realcycle);
			done=!done;
			return new Move(Statics.convertToCellId(r, c), realcycle);
		}
		this.setWait(1);
		return null;
	}
	

	protected Movement getMov(){
		return this.mov;
	}
	private void kill() {
		dead.set(true);
	}



	@Override
	public int getHP() {
		return this.hp.get();
	}



	@Override
	public String getName() {
		return "NYASLAVE";
	}
}
