package model.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.character.attack.Attack;
import model.character.attack.statics.Bommerang;
import model.gameMap.GameMap;
import model.gameMap.additional.Statics;
import model.gameMap.move.Movement;

class ModelTest {

	
	@Test
	void StaticsTest() {
		assertEquals(8, Statics.convertToCellId(0, 8));
		assertEquals(66, Statics.convertToCellId(1, 2));
		assertEquals(10, Statics.convertToRow(640));
		assertEquals(12, Statics.convertToColomn(652));
		assertFalse(Statics.isInMap(10, -1));
		assertTrue(Statics.isInMap(50, 13));
	}

}
