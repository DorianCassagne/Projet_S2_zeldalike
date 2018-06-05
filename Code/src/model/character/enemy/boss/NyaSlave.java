package model.character.enemy.boss;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class NyaSlave extends GameCharacter{

	private Movement mov;
	private BooleanProperty dead;
	private IntegerProperty hp;
	private IntegerProperty def;
	private int realcycle;
	protected NyaSlave(GameMap map, int startRow, int startColumn, int cycle, double coefficient, int defaultImage, IntegerProperty hp, IntegerProperty def, BooleanProperty dead, int realCycle) {
		super(map, startRow, startColumn, cycle, coefficient, defaultImage);
		this.dead=dead;		
		this.hp=hp;
		this.def=def;
		this.realcycle=realCycle;
		mov= Movement.STAY;
	}//

	

	@Override
	public void launchAttack(Movement move) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getDmg(Attack attack) {
		System.out.println(hp.get());
		System.out.println(attack.getDamage());
		this.hp.set(this.hp.get() - GameCharacter.calculateDamage(attack.getDamage(), def.get()));
		if (hp.get()<=0)
			this.kill();
	}
	
	public void setMov(Movement mov) {
		if (mov==null)
			this.mov=Movement.STAY;
		else
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
			return new Move(Statics.convertToCellId(r, c), realcycle);
		}
		this.setWait(1);
		return null;
	}
	@Override
	protected void setImage(Movement movement) {
		 
	}

	private void kill() {
		dead.set(true);
	}
}
