package model.character.enemy;

/*
 * classe facilitant la cr�ation d'ennemi
 */

import model.character.enemy.boss.NyaBlock;
import model.character.enemy.boss.NyaNyaNay;
import model.character.enemy.normal.BadMonkey;
import model.character.enemy.normal.Bomber;
import model.character.enemy.normal.Fairy;
import model.character.enemy.normal.IntelligentTower;
import model.character.enemy.normal.MonkeyGuard;
import model.character.enemy.normal.StaticBadMonkey;
import model.character.enemy.normal.Tower;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;


public class EnemyFactory {
	
	public final static Enemy MonsterFactory(String monsterName,GameMap map,int row,int column) {
		Enemy monster;
		
		if(monsterName.equalsIgnoreCase("BADMONKEY")) {
			monster = new BadMonkey(map,row,column);
		}
		
		else if(monsterName.equalsIgnoreCase("Fairy")) {
			monster = new Fairy(map, row, column);
		}
		
		else if(monsterName.startsWith("MonkeyGuard")) {
			String choice = monsterName.substring(monsterName.length() - 1 , monsterName.length());
			monster = new MonkeyGuard(map, row, column, Integer.parseInt(choice));
		}

		else if(monsterName.equalsIgnoreCase("Bomber")) {
			monster = new Bomber(map, row, column);
		}

		else if(monsterName.equalsIgnoreCase("IntelligentTower")) {
			monster = new IntelligentTower(map,row,column);
		}
		
		else if(monsterName.equalsIgnoreCase("Tower")) {
			monster = new Tower(map,row,column);
		}
		
		else if(monsterName.equalsIgnoreCase("NyaNyaNay"))
			monster = new NyaNyaNay(map, row, column, 6);
		
		else if(monsterName.equalsIgnoreCase("NyaBlock")) {
			monster = new NyaBlock(map, row, column, Movement.RIGHT );
		}
		
		else if(monsterName.equalsIgnoreCase("StaticBadMonkey")) {
			monster = new StaticBadMonkey(map, row, column);
		}
		
		else {
			throw new IllegalArgumentException("MONSTER NOT FOUND");
		}
		
		return monster;
	}
}
