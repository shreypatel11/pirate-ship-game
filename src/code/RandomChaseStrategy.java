package code;
import java.util.Random;
import java.awt.Point;
public class RandomChaseStrategy implements ChaseStrategy{
	Random rand = new Random();
	OceanMap oceanMap;	
	public RandomChaseStrategy(int dimensions, int islandCount) {
		oceanMap = OceanMap.getInstance(dimensions, islandCount);
	}
	
	//just random directions
	public Point chase(Point pShipLoc, Point shipLoc) {
		int num = rand.nextInt(4); 
		if(num==0) {
			if(pShipLoc.y>0 && oceanMap.isOcean(pShipLoc.x, pShipLoc.y-1)){
	    		pShipLoc.y--;
			}
		}
		if(num==1) {
			if(pShipLoc.y<oceanMap.getDimensions()-1 && oceanMap.isOcean(pShipLoc.x, pShipLoc.y+1)){
	    		pShipLoc.y++;
			}
		}
		if(num==2) {
			if(pShipLoc.x<oceanMap.getDimensions()-1 && oceanMap.isOcean(pShipLoc.x+1, pShipLoc.y)){
	    		pShipLoc.x++;
			}
		}
		if(num==3) {
			if(pShipLoc.x >0 && oceanMap.isOcean(pShipLoc.x-1, pShipLoc.y)){
	    		pShipLoc.x--;
			}
		}
		return pShipLoc;
	}
}
