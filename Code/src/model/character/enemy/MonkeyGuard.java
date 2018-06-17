package model.character.enemy;

import model.PathFinder.BFS1;
import model.character.GameCharacter;
import model.character.attack.statics.hero.bomb.Bomb;
import model.character.enemy.normal.Bomber;
import model.character.enemy.normal.EnemyNormal;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Move;
import model.gameMap.move.Movement;
/*
 * Classe ennemi 
 * qui lorsqu'il spawn se dirige vers une position precise puis ensuite retourne a sa position initiale
 * tant que le joueur n'est pas a sa portee 
 * 
 * lorsque le joueur est a sa portee, vitesse accrue et attaque 
 */

public class MonkeyGuard extends EnemyNormal{

	private final static int DEFAULTATK = 50;
	private final static int DEFAULTHP = 150;
	private final static int DEFAULTDEF = 10;
	private final static int DEFAULTCYCLE = 40;
	private final static int DEFAULTIMAGE = 16;
	private final static double DEFAULTCOEFFICIENT = 1.7;
	private final static int SCORE = 100;
	
	/*
	 * Selon les maps de jeu, un ennemi aura ou plusieurs aura un pattern de deplacement predetermine 
	 * le int signifie l'idCell, 
	 * le couple de int signifie les positions sur lesquelles l'ennemi devra faire des aller-retours 
	 * l'indice du niveau supérieur donne l'indice de la map
	 *  
	 */
	
	private final static int[][][] POSITIONS = {
			{{3740,3750},{3047,2967}},	//Map1
			{{6,4},{4,9}},	//Map2
			{{7,6},{4,9}},	//Map3
			{{4,5},{4,9}},	//map3
	};

	private int choice;
	private int nextDestination;

	public MonkeyGuard(GameMap map, int startRow, int startColumn,int choice) {
		super(map, startRow, startColumn, DEFAULTCYCLE, DEFAULTCOEFFICIENT, DEFAULTIMAGE, DEFAULTHP, DEFAULTDEF, SCORE);
		this.choice = choice ;
		updateDestination();
		
	}

	@Override
	public Move act() {

		int row = GameCharacter.getHero().getRow() ;
		int column = GameCharacter.getHero().getColumn();
		int actualCell= Statics.convertToCellId(this.getRow(), this.getColumn());
		int mapId = this.getMyMap().getMapId();

		Move nextMove = null;

		//exmple pour attackmove attention a l'ordre des cases
		int[]tab= {
				Statics.convertToCellId(row+2,column),
				Statics.convertToCellId(row,column-2),
				Statics.convertToCellId(row-2,column),
				Statics.convertToCellId(row,column+2)
		};

		int[] tab2 = {
				POSITIONS[mapId][choice][nextDestination]
		};

		int inPlace= inPlace(tab, actualCell);
		if (inPlace != -1) {
			Movement currentMovement = Movement.values()[inPlace];
			this.setImage(currentMovement);
			this.launchAttack(currentMovement);
			setWait(200);
		}
		else {

			int nextCell= BFS1.simpleMove(this.getMyMap(),actualCell,tab, true,5);

			if (actualCell==nextCell) {
				nextCell = BFS1.simpleMove(this.getMyMap(),actualCell,tab2, true,0);
			}
			else {
				this.setCoefficient(3);
			}
			
			if(this.getMyMap().changeCell(this,this.getRow(),this.getColumn(),Statics.convertToRow(nextCell),Statics.convertToColomn(nextCell))){
				updateDestination();
				nextMove = new Move(nextCell,this.getMoveCycle());
			}
		}
		return nextMove;
	}

	
	private void updateDestination() {
		int mapId = this.getMyMap().getMapId();
		if(this.getCellId() == POSITIONS[mapId][choice][0]) {
			this.nextDestination = 1;
		}
		else if(this.getCellId() == POSITIONS[mapId][choice][1]) {
			this.nextDestination = 0;
		}
			
	}
	

	private int inPlace(int[] tab, int actualCell) {
		int ret=-1;
		for (int i = 0; i < tab.length; i++) {
			if(tab[i]==actualCell)
				ret=i;
		}
		return ret%4;
	}

	@Override
	public void launchAttack(Movement movement) {
		new Bomb(getMyMap(),this.getRow(),this.getColumn(),movement,DEFAULTATK);
		int row = this.getRow() + movement.getVerticalIncrement();
		int column = this.getColumn() + movement.getHorizontalIncrement();
		try{
			new Bomber(getMyMap(), row, column);
		}catch(Exception e) {
			
		}
		
	}


	@Override
	public String getName() {
		return "MonkeyGuard";
	}



}
