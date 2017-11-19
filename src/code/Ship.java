package code;
//Final Assignment
import java.awt.Point;
import java.util.Observable;

public class Ship extends Observable {
	Point currentLocation;
	int dimensions;
	int islandCount;
	OceanMap oceanMap;
	public Ship(int dimensions, int islandCount, PirateShip testShipG, PirateShip testShipP, Monster monster, Treasure treasure) {
		oceanMap = OceanMap.getInstance(dimensions, islandCount);
		currentLocation = oceanMap.getShipLoc();
		super.addObserver(testShipG);
		super.addObserver(testShipP);
		super.addObserver(monster);
		super.addObserver(treasure); //add all Observables
	}
	
	public Point getShipLocation() { //return location
		return currentLocation;
	}
	
	public void goRight() {
		if(currentLocation.x<oceanMap.getDimensions()-1 && oceanMap.isOcean(currentLocation.x+1, currentLocation.y)){
    		currentLocation.x++;
    		super.setChanged();
    		super.notifyObservers(currentLocation); //notify on all moves
		}	
	}
	
	public void goLeft() {
		if(currentLocation.x >0 && oceanMap.isOcean(currentLocation.x-1, currentLocation.y)){
    		currentLocation.x--;
    		super.setChanged();
    		super.notifyObservers(currentLocation);
		}
	}
	
	public void goUp() {
		if(currentLocation.y>0 && oceanMap.isOcean(currentLocation.x, currentLocation.y-1)){
    		currentLocation.y--;
    		super.setChanged();
    		super.notifyObservers(currentLocation);
		}
	}
	
	public void goDown() {
		if(currentLocation.y<oceanMap.getDimensions()-1 && oceanMap.isOcean(currentLocation.x, currentLocation.y+1)){
    		currentLocation.y++;
    		super.setChanged();
    		super.notifyObservers(currentLocation);
		}
	}
}
