package code;
//Final Assignment
import java.util.Random;
import java.util.Observable;
import java.util.Observer;
import java.awt.Point;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class MonsterSprite{ //serves as leaf node. had no need to implement individual running/freezing for the game but it's doable
	int x;
	int y;
	Circle circle;
	int scalingFactor;
	int radius = 10;
	MonsterSprite(int x, int y, int scalingFactor){
		this.x = x;
		this.y = y;
		circle= new Circle();
		setPositionX(x);
		setPositionY(y);
		circle.setRadius(radius);
		this.scalingFactor = scalingFactor;
	}
	
	Circle getCircle(){
		return circle;
	}
	
	void setX(int x){
		this.x = x;
		setPositionX(x);
	}
	
	void setY(int y){
		this.y = y;
		setPositionY(y);
	}
	
	int getX(){
		return x;
	}
	
	int getY(){
		return y;
	}
	
	public void setLineColor(Circle circle, Color color){
		circle.setStroke(color);
		circle.setFill(color);
	}
	
	public void setPositionX(int x){
		circle.setCenterX(x*scalingFactor + (scalingFactor/2));
	}
	
	public void setPositionY(int y){
		circle.setCenterY(y*scalingFactor + (scalingFactor/2));
	}
}

public class Monster implements Runnable, Observer {
	
	Boolean running = true;
	int radius;
	Random random = new Random();
	int scalingFactor;
	MonsterSprite[] monsterSprites = new MonsterSprite[5];
	int dimensions;
	int islandCount;
	Point shipLoc;
	
	public Monster(int scalingFactor, int dimensions, int islandCount){ //constructor, makes new monsterSprites and puts them in aggregate structure (array)
		this.dimensions = dimensions;
		this.islandCount = islandCount;
		shipLoc = OceanMap.getInstance(dimensions, islandCount).getShipLoc();
		for(int j = 0; j < 5; j++){
			int x = random.nextInt(5);
			int y = random.nextInt(5);	
			monsterSprites[j] = new MonsterSprite(x,y,scalingFactor);
		}
		//this.radius = 10;
		//this.scalingFactor = scalingFactor;
	}
	public void addToPane(ObservableList<Node> sceneGraph){
		for(MonsterSprite monsterSprite: monsterSprites){ //for each child add to sceneGraph
			
			Circle circle = monsterSprite.getCircle();
			System.out.println("Adding circle to pane: " + circle.getCenterX() + " " + circle.getCenterY() + " " + radius);
			sceneGraph.add(circle);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) { //is notified of Ship location to see whether it gets caught
		if(arg0 instanceof Ship) {
			shipLoc = (Point) arg1;
		}
		
	}	
			
	@Override //Composite, each MonsterSprite serves as the "leaf node"
    public void run() {
	if(Thread.interrupted()) {
		
	}
	boolean caught = false;
      while (!(caught)) {
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	for(MonsterSprite monsterSprite: monsterSprites){ //for each child (leaf) move
    		// Move X
    		int xMove = monsterSprite.getX() + random.nextInt(3)-1;
    		if (xMove >=0 && xMove <= 5) { //container where it can swim
    			if(OceanMap.getInstance(dimensions, islandCount).isOcean(xMove, monsterSprite.getY())) {
    			monsterSprite.setX(xMove);
    			}
    		}
    		// Move Y
    		int yMove = monsterSprite.getY() + random.nextInt(3)-1;
    		if (yMove >=0 && yMove <=5) { //container where it can swim
    			if(OceanMap.getInstance(dimensions, islandCount).isOcean(monsterSprite.getX(), yMove)) {
    			monsterSprite.setY(yMove);
    			}
    		}
    		if(xMove == shipLoc.x && yMove == shipLoc.y) {
    			caught = true;
    			Thread.currentThread().interrupt();
    			Platform.runLater(new Runnable() { //had to google around to figure this one out!
    			@Override
    			public void run() {
    			Alert alert = new Alert(AlertType.INFORMATION);
    			alert.setTitle("Information");
    			alert.setHeaderText("Caught by Monster!");
    			alert.setContentText("You Lose!");
    			alert.showAndWait();
    			Platform.exit();
    			}
    			});
    		}
    	}
      }
      
    }
}
