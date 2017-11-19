package code;
import static org.junit.jupiter.api.Assertions.*;
import code.OceanExplorer;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.awt.Point;

class OceanExplorerTest { //ended up testing various other classes
	@Test
	public void testFactories() {
		GhostShipFactory testGhost = new GhostShipFactory();
		PatrolShipFactory testPatrol = new PatrolShipFactory();
		ArrayList<PirateShipFactory> list = new ArrayList<PirateShipFactory>();
		list.add(testGhost);
		list.add(testPatrol);
		assertTrue(list.size()==2);		
	}
	
	@Test
	public void testShips() {
		GhostShipFactory testGhost = new GhostShipFactory();
		PatrolShipFactory testPatrol = new PatrolShipFactory();
		PirateShip testShipG = testGhost.createPirateShip(new FollowStrategy(20, 20), 20, 20);
		PirateShip testShipP = testPatrol.createPirateShip(new RandomChaseStrategy(20, 20), 20, 20);
		assertFalse(testShipG == testShipP);
	}
	
	@Test
	public void testTreasure() {
		Point test = OceanMap.getInstance(20, 20).placeShip();
		Treasure testTreasure = new Treasure(test);
		assertTrue(testTreasure.getTreasureLocation() == test);
	}
	
	@Test
	public void testTreasureAgainstShip() {
		GhostShipFactory testGhost = new GhostShipFactory();
		PirateShip testShipG = testGhost.createPirateShip(new FollowStrategy(20, 20), 20, 20);
		Point test = OceanMap.getInstance(20, 20).placeShip();
		Treasure testTreasure = new Treasure(test);
		assertFalse(testTreasure.getTreasureLocation() == testShipG.getPirateLocation());
	}

}
