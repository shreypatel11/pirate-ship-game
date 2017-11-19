package code;
//Final Assignment
import java.util.Observable;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.awt.Point;
public class PatrolShip extends PirateShip {
	Point shipLoc;
	public PatrolShip(ChaseStrategy strategy, int dimensions, int islandCount) {
		super(strategy, dimensions, islandCount);
		pirateLoc = OceanMap.getInstance(dimensions, islandCount).placeShip();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Ship) {
			shipLoc = (Point) arg;
			chaseStrategy.chase(pirateLoc, shipLoc);
		}
	}
	
	//return Patrol ship loc
	@Override
	public Point getPirateLocation() {
		return pirateLoc;
	}
	
	//if found lose notif
	@Override
	public void checkFound() {
		if(pirateLoc.x == shipLoc.x && pirateLoc.y == shipLoc.y) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Caught by Patrol Ship!");
			alert.setContentText("You Lose!");
			alert.showAndWait();
			Platform.exit();
		}
	}
}
