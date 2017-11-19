package code;
import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

public class Treasure implements Observer {
	Point loc;
	Point shipLoc;
	public Treasure(Point location) {
		loc = location;
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Ship) {
			shipLoc = (Point) arg1; //treasure is updated on ships loc
		}
	}
	
	public void checkFound() {
		if(loc.x == shipLoc.x && loc.y == shipLoc.y) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information");
			alert.setHeaderText("Columbus found the treasure!");
			alert.setContentText("You Won!");
			alert.showAndWait();
			Platform.exit();
		}
	}
	
	public Point getTreasureLocation() { //treasure loc
		return loc;
	}
}
