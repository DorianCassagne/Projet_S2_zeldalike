package model.character.enemy.normal;

/*
 * Ennemi qui possede deux etats differents associes a des couleurs
 * avant de lancer une attaque, elle s'immobilise change de couleur et lance une attaque dependante de son etat
 * change d etat aleatoirement  
 */

import java.util.Random;


import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.character.attack.statics.Bommerang;
import model.character.attack.statics.boss.Missile;
import model.gameMap.GameMap;
import model.gameMap.additional.MapReader;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;

public class Fairy extends EnemyNormal {
	private final static int CYCLE = 30;
	private final static int IMG = 32;
	private final static int DEF = 30;
	private final static int HP = 300;
	private final static int SCORE = 1000;
	private final static double DEFAULTCOEFFICIENT = 1.7;
	private final static Random RAND = new Random();

	private boolean state;
	private boolean att;
	private int valImg;
	private int[] joinTab;
	private Movement attDir;
	
	
	public Fairy(GameMap map, int startRow, int startColumn) {
		super(map, startRow, startColumn, CYCLE, DEFAULTCOEFFICIENT, IMG, HP, DEF,SCORE);
		
		state = RAND.nextBoolean();
		
		if (!state)
			valImg = 0;
			
		this.setImage(valImg);
	}
	/*
	 * check if the character is in joinTab
	 * return -1 if not or the index of the cell
	 */
	private int inPlace() {
		int ret=-1;
		for (int i = 0; i < joinTab.length; i++) {
			if(joinTab[i]==this.getCellId())
				ret=i;
		}

		return ret%4;
	}
	
	private void updateState() {
		att=false;
		this.setWait(200);
		state=RAND.nextBoolean();
		
		if (!state)
			valImg=8;
		
		else
			valImg=0;

	}
	
	/*
	 * @see model.character.Movable#act()
	 */
	@Override
	protected Move act() {
		
		Move move  = null;
		
		if(att) {
			
			this.launchAttack(attDir);
			this.updateState();
			
		}
		else {
			
			getAround((state)?2:5);
			
			if(inPlace() != -1) {
				att = true;
				
				setWait((state)?(CYCLE*3):(CYCLE*4));
				
				this.attDir = Movement.values()[inPlace()];
				this.setImage(attDir.getIndex() + 4);
			
			}
			else {
				move = this.runBFS();
			}
		}
		
		return move;
	
	}
	
	
	private Move runBFS() {
		int nextCell= BFS1.simpleMove(this.getMyMap(), this.getCellId(), joinTab, true, 0);
		int actualCell= this.getCellId();
		Move BFSMove = null;
		
		if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),Statics.convertToRow(nextCell),Statics.convertToColomn(nextCell))){
			
			if (!state)
				setWait(CYCLE*2);
			
			if(actualCell + MapReader.MAPLENGTH == nextCell) {
				this.setImage(valImg+2);
			}
			
			else if(actualCell - MapReader.MAPLENGTH == nextCell) {
				this.setImage(valImg);
			}
			
			else if(actualCell + 1 == nextCell) {
				this.setImage(valImg+1);
			}
			
			else if(actualCell - 1 == nextCell) {
				this.setImage(valImg+3);
			}
			
			BFSMove = new Move(nextCell,this.getMoveCycle());
		}

		return BFSMove;
	}
	
	/*
	 * put in jointtab all the cells around the hero
	 * @param decal
	 */
	
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
	
	@Override
	public String getName() {
		return "Fairy";
	}
	
	@Override
	public void launchAttack(Movement move) {

		if (state) 
			new Missile(this.getMyMap(), this.getRow(), this.getColumn(), attDir);
		else
			new Bommerang(getMyMap(), getRow(), getColumn(), attDir);	
	}
}
