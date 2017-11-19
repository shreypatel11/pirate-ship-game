package code;
//Final Assignment
import java.util.Observable;
import java.util.Observer;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.awt.Point;

public class PirateShip implements Observer { //Observer pattern
	Point pirateLoc;
	Point shipLoc;
	OceanMap oceanMap;
	ChaseStrategy chaseStrategy;
	
	public PirateShip(ChaseStrategy strategy, int dimensions, int islandCount) {
		pirateLoc = OceanMap.getInstance(dimensions, islandCount).placeShip();
		oceanMap = OceanMap.getInstance(dimensions, islandCount);
		chaseStrategy = strategy;
	}
	
	public void setStrategy(ChaseStrategy strategy) {
		chaseStrategy = strategy;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Ship) {
			Point shipLoc = (Point) arg1;
			pirateLoc = chaseStrategy.chase(this.getPirateLocation(), shipLoc); //the chase strat, original implementation before ghost/patrol ships
			}
		}
	
	public Point getPirateLocation() {
		return pirateLoc;
	}
	
	//check, lose notif
	public void checkFound() {
		if(pirateLoc.x == shipLoc.x && pirateLoc.y == shipLoc.y) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Caught by pirate ship!");
			alert.setContentText("You Lose!");
			alert.showAndWait();
			Platform.exit();
		}
	}
	
}
