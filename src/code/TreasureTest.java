package code;
import java.awt.Point;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TreasureTest {

	@Test
	public void treasureCheckTest() {
		Treasure treasure = new Treasure(new Point(0, 0));
		assertTrue(treasure.getTreasureLocation().x == 0 && treasure.getTreasureLocation().y == 0);
	}

}
