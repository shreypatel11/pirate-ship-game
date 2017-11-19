package code;
import java.awt.Point;

public class FollowStrategy implements ChaseStrategy { //follows CC ship around
	OceanMap oceanMap;
	public FollowStrategy(int dimensions, int islandCount) {
		oceanMap = OceanMap.getInstance(dimensions, islandCount);
	}
	
	public Point chase(Point pirateLoc, Point shipLoc) {
		if(pirateLoc.x > shipLoc.x) { //changed goEast() and goWest() so the ship would move around islands a little easier
			if(pirateLoc.x >0 && oceanMap.isOcean(pirateLoc.x-1, pirateLoc.y)){
	    		pirateLoc.x--;
			}
			else {
				if(pirateLoc.y > shipLoc.y) {
					this.goNorth(pirateLoc);
				}
				else if(pirateLoc.y < shipLoc.y) {
					this.goSouth(pirateLoc);
				}
			}
		}
		else if(pirateLoc.x < shipLoc.x) {
			if(pirateLoc.x<oceanMap.getDimensions()-1 && oceanMap.isOcean(pirateLoc.x+1, pirateLoc.y)){
	    		pirateLoc.x++;
			}
			else {
				if(pirateLoc.y > shipLoc.y) {
					this.goNorth(pirateLoc);
				}
				else if(pirateLoc.y < shipLoc.y) {
					this.goSouth(pirateLoc);
				}
			}
		}
		else if(pirateLoc.y > shipLoc.y) {
			this.goNorth(pirateLoc);
		}
		else if(pirateLoc.y < shipLoc.y) {
			this.goSouth(pirateLoc);
		}
		
		if(pirateLoc.x == shipLoc.x && pirateLoc.y == shipLoc.y) {
			System.out.println("Your ship has been caught!"); //used for test, alert pops up if caught now
		}
		return pirateLoc;
	}
	
	public void goNorth(Point pirateLoc) {
		if(pirateLoc.y>0 && oceanMap.isOcean(pirateLoc.x, pirateLoc.y-1)){
		pirateLoc.y--;
		}
	}
	
	public void goSouth(Point pirateLoc) {
		if(pirateLoc.y<oceanMap.getDimensions()-1 && oceanMap.isOcean(pirateLoc.x, pirateLoc.y+1)){
    		pirateLoc.y++;
		}
	}
}
