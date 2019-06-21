package code;
import org.junit.Test;

import java.awt.Point;

import static org.junit.Assert.assertTrue;


class TreasureTest {

	@Test
	public void treasureCheckTest() {
		Treasure treasure = new Treasure(new Point(0, 0));
		assertTrue(treasure.getTreasureLocation().x == 0 && treasure.getTreasureLocation().y == 0);
	}

}
