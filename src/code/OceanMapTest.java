package code;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import code.OceanExplorer;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Test;

import java.awt.Point;

class OceanMapTest {

	@Test
	public void testInit() {
		OceanMap test = OceanMap.getInstance(20, 20);
		Point shipLoc = test.getShipLoc();
		OceanMap test2 = OceanMap.getInstance(20, 20);
		Point shipLoc2 = test2.getShipLoc();
		assertTrue(shipLoc == shipLoc2);
	}
	
	@Test
	public void testDimensions() {
		int dimensions = OceanMap.getInstance(20, 20).getDimensions();
		assertTrue(dimensions == 20);
	}
	
	@Test
	public void testIslandCount() {
		int islandCount = OceanMap.getInstance(20, 10).getIslandCount(); //even if i set it to 10, theres only 1 instance (singleton) of oceanMap so should give me 20
		assertTrue(islandCount == 20);
	}
	
	@Test
	public void testMap() {
		boolean[][] islandMap = OceanMap.getInstance(20, 20).getMap();
		assertTrue(islandMap == OceanMap.getInstance(20, 20).islands);
	}
	
	@Test
	public void testIsOcean() {
		boolean[][] testMap = OceanMap.getInstance(20,20).getMap();
		boolean testOcean = testMap[0][0];
		assertFalse(testOcean == OceanMap.getInstance(20, 20).isOcean(0, 0));
		
	}

}
