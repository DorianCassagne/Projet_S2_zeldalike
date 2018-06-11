package model.character.enemy;

import model.gameMap.GameMap;

public class EnemyFactory {
	
	public final static BlueFairy MonsterFactory(String monsterName,GameMap map,int row,int column) {
		BlueFairy monster;
		if(monsterName.equalsIgnoreCase("BADMONKEY")) {
			monster = new BadMonkey(map,row,column);
		}
		else {
			throw new IllegalArgumentException("MONSTER NOT FOUND");
		}
		return monster;
	}
}
