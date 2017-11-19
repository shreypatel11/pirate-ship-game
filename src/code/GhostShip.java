package code;
//Final Assignment
import java.util.Observable;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.Point;
public class GhostShip extends PirateShip {
	Point shipLoc;
	public GhostShip(ChaseStrategy strategy, int dimensions, int islandCount) {
		super(strategy, dimensions, islandCount);
		pirateLoc = OceanMap.getInstance(dimensions, islandCount).placeShip();
	}
	
	@Override
	public void update(Observable o, Object arg) { //updates when notified with Ship's location
		if(o instanceof Ship) {
			shipLoc = (Point) arg;
			chaseStrategy.chase(pirateLoc, shipLoc);
		}
	}
	
	@Override
	public Point getPirateLocation() {
		return pirateLoc;
	}
	
	@Override
	public void checkFound() { //checks if Ghost Ship finds CC ship
		if(pirateLoc.x == shipLoc.x && pirateLoc.y == shipLoc.y) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Caught by Ghost Ship!");
			alert.setContentText("You Lose!");
			alert.showAndWait();
			Platform.exit();
		}
	}

}
