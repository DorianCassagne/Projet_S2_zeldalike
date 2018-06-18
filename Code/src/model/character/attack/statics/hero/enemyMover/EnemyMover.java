package model.character.attack.statics.hero.enemyMover;

/*
 * Classe d'attack EnnemyMover, permet de deplacer/pousser des ennemis dans une direction de la meme maniere que le deplacement d'une caisse
 *  
 */
import model.character.GameCharacter;
import model.character.attack.Attack;
import model.gameMap.GameMap;
import model.gameMap.cell.Cell;
import model.gameMap.move.ExternalMover;
import model.gameMap.move.Movement;

public class EnemyMover extends Attack implements ExternalMover{

	private static final int DEFAULTCYCLE = 20;
	static final int DEFAULTDAMAGE = 0;
	private static final double COEFFICIENT = 1.3;
	public static final int DEFAULTIMAGE = 1628 ;
	private final static int DEFAULTCELLPERTURN = 1;
	private final static int DEFAULTMAXDISTANCE = 4;
	
	
	public EnemyMover(GameMap map,  int row, int column, Movement direction) {
		super(map, DEFAULTCYCLE, row, column, direction, DEFAULTDAMAGE, DEFAULTCELLPERTURN, COEFFICIENT, DEFAULTIMAGE, DEFAULTMAXDISTANCE);
	}

	public boolean handlePlay(byte attackResult) {
		return attackResult % Cell.NOEFFECT != 0;
	}
	

	@Override
	public int getSpeed() {
		return 20;
	}

	@Override
	protected void establishAttack(GameCharacter gameCharacter) {
		
		Movement direction = this.getDirection();
		
		int newRow = gameCharacter.getRow() + direction.getVerticalIncrement() ;
		int newColumn = gameCharacter.getColumn() + direction.getHorizontalIncrement();
		
		this.getMyMap().changeCell(gameCharacter, gameCharacter.getRow(), gameCharacter.getColumn(), newRow, newColumn,this);
		gameCharacter.setCellId(newRow, newColumn);
	}

}
