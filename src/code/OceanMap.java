package code;
//Final Assignment 

import java.awt.Point;
import java.util.Random;

public class OceanMap {
	private static OceanMap uniqueInstance; //Singleton
	boolean[][] islands;
	int dimensions;
	int islandCount;
	Point shipLocation;
	Random rand = new Random();
	
	private OceanMap(int dimensions, int islandCount) {
		this.dimensions = dimensions;
		this.islandCount = islandCount;
		createGrid();
		placeIslands();
		shipLocation = placeShip();
	}
	
	//Singleton getInstance
	public static OceanMap getInstance(int dims, int islandNum) {
		if(uniqueInstance == null) {
			uniqueInstance = new OceanMap(dims, islandNum);
		}
		return uniqueInstance;
	}
	
	// Create an empty map
	private void createGrid(){
		islands = new boolean[dimensions][dimensions];
		for(int x = 0; x < dimensions; x++)
			for(int y = 0; y < dimensions; y++)
				islands[x][y] = false;
	}
	
	// Place islands onto map
	private void placeIslands(){
		int islandsToPlace = islandCount;
		while(islandsToPlace >0){
			int x = rand.nextInt(dimensions);
			int y = rand.nextInt(dimensions);
			if(islands[x][y] == false){
				islands[x][y] = true;
				islandsToPlace--;
			}
		}
	}
	
	//return map
	public boolean[][] getMap(){
		return islands;
	}
	
	//what's ocean?
	public boolean isOcean(int x, int y){
		if (!islands[x][y])
			return true;
		else
			return false;
	}
	
	//return dims
	public int getDimensions() {
		return dimensions;
	}
	
	//return isleCount
	public int getIslandCount() {
		return islandCount;
	}
	
	//placeShip, used for treasure too
	public Point placeShip() {
		boolean placedShip = false;
		int x=0,y=0;
		while(!placedShip){
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if(islands[x][y] == false){
				placedShip = true;
			}
		}
		return new Point(x,y);
	}
	
	//return CC shipLoc initial
	public Point getShipLoc() {
		return shipLocation;
	}
	
}
