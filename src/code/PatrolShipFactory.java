package code;
//Final Assignment
public class PatrolShipFactory extends PirateShipFactory {

	public PatrolShipFactory() {
		super();
	}
	//make patrol ship
	@Override
	PirateShip createPirateShip(ChaseStrategy strategy, int dimensions, int islandCount) {
		// TODO Auto-generated method stub
		PirateShip patrolShip = new PatrolShip(strategy, dimensions, islandCount);
		return patrolShip;
	}
	
}
