package code;
import javafx.scene.layout.Pane;
public abstract class PirateShipFactory {
	Pane root;
	public PirateShipFactory() {
	}
	//abstract factory method
	abstract PirateShip createPirateShip(ChaseStrategy strategy, int dimensions, int islandCount);
	public PirateShip buildPirateShip(ChaseStrategy strategy, int dimensions, int islandCount) {
		PirateShip newShip;
		newShip = createPirateShip(strategy, dimensions, islandCount);
		return newShip;
	}
}
