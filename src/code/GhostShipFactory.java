package code;
//Final Assignment
public class GhostShipFactory extends PirateShipFactory {
	int scalingFactor;
	public GhostShipFactory() {
		super();
	}
	

	
	@Override
	PirateShip createPirateShip(ChaseStrategy strategy, int dimensions, int islandCount) { //create Ghost Ship
		// TODO Auto-generated method stub
			PirateShip ghostShip = new GhostShip(strategy, dimensions, islandCount);
			return ghostShip;
	}
}
