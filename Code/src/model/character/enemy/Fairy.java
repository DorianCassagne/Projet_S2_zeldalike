package model.character.enemy;

import java.util.Random;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.character.attack.statics.Bomb;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Fairy extends Enemy {
	private final static int CYCLE=30;
	private final static int IMG=32;
	private final static int DEF=30;
	private final static int DMG=30;
	private final static int HP=30;
	private boolean state;
	private boolean att;
	private int valImg;
	private int[] joinTab;
	private Movement attDir;
	private final Random ran = new Random();
	
	public Fairy(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn, CYCLE, IMG, HP, DEF, DMG);
		state=ran.nextBoolean();
		if (state)
			valImg=0;
		else
			valImg=8;
	}

	private int inPlace() {
		int ret=-1;
		for (int i = 0; i < joinTab.length; i++) {
			if(joinTab[i]==this.getCellId())
				ret=i;
		}

		return ret%4;
	}
	
	@Override
	protected Move act() {
		if(att) {
			
			
			att=false;
		}
		else {
			getAround((state)?1:5);
			if(inPlace()!=-1) {
				att=true;
				this.attDir= Movement.values()[inPlace()];
				this.setImage(attDir.getIndex()+valImg);
			}
			else {
				int nextCell= BFS1.simpleMove(this.getMyMap(), 
						this.getCellId(),
						joinTab, true, 0);
				if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),Statics.convertToRow(nextCell),Statics.convertToColomn(nextCell))){
					if (!state)
						setWait(cycle*2);
					return new Move(nextCell,this.getMoveCycle());
				}
			}
		}
		
		return null;
	
	}
	
	private void getAround(int decal) {
		if(!att) {
			int row = GameCharacter.getHero().getRow() ;
			int column = GameCharacter.getHero().getColumn();
			joinTab=new int[] {
			Statics.convertToCellId(row+decal,column),
			Statics.convertToCellId(row,column-decal),
			Statics.convertToCellId(row-decal,column),
			Statics.convertToCellId(row,column+decal)
			};
		}
			
		
		return;
	}
	
	
	
//	@Override
//	public Move act() {
//		
//		
//		int actualCell= Statics.convertToCellId(this.getRow(), this.getColumn());
//		//exmple pour attackmove attention a l'ordre des cases
//		int[]tab= {
//				Statics.convertToCellId(row+5,column),
//				Statics.convertToCellId(row,column-5),
//				Statics.convertToCellId(row-5,column),
//				Statics.convertToCellId(row,column+5),
////				Statics.convertToCellId(row+2,column),
////				Statics.convertToCellId(row,column-2),
////				Statics.convertToCellId(row-2,column),
////				Statics.convertToCellId(row,column+2),
//		};
//		
//		int inPlace= inPlace(tab, actualCell);
//		if (inPlace != -1) {
//			Movement currentMovement = Movement.values()[inPlace];
//			this.setImage(currentMovement);
//			//new DoomAttack(getMyMap(),this.getRow(),this.getColumn(),currentMovement, 10);
//			new Bomb(getMyMap(),this.getRow(),this.getColumn(),currentMovement);
//			//new NyanAttHori(getMyMap(), this.getRow(), this.getColumn());
//			setWait(200);
//			return null;
//		}
//		else {
//	
//			int nextCell= BFS1.simpleMove(this.getMyMap(), 
//					actualCell,
//					tab, true, 0);
//			
//			if (actualCell==nextCell) {
//				return null;
//			}
//			if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),Statics.convertToRow(nextCell),Statics.convertToColomn(nextCell))){
//				return new Move(nextCell,this.getMoveCycle());
//			}
//			
//			return null;
//		}
//	}

	

	


}
