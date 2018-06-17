package model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.character.attack.Attack;
import model.character.attack.statics.Bommerang;
import model.gameMap.GameMap;
import model.gameMap.move.Movement;

class ModelTest {

	@Test
	void AttackTest() {
		GameMap map= new GameMap(0, "test");
		Attack a = new Bommerang(map, 0, 0, Movement.STAY);
	}

}
