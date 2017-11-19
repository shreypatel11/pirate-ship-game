package code;
//Final Assignment
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.awt.Point;


public class OceanExplorer extends Application {
	Pane root;
	boolean[][] islandMap;
	final int dimensions = 20;
	final int islandCount = 20;
	final int scalingFactor = 25;
	ImageView shipImageView;
	ImageView pirateShipImageView;
	ImageView pirateShipImageView2;
	ImageView treasureImageView;
	Ship ship;
	//PirateShip pShip;
	//PirateShip pShip2;
	GhostShipFactory testGhost;
	PirateShip testShipG;
	PatrolShipFactory testPatrol;
	PirateShip testShipP;
	Scene scene;
	Treasure treasure;
	Monster monster;
	Thread monstersThread;
	
	public void start(Stage mapStage) throws Exception {
		islandMap = OceanMap.getInstance(dimensions, islandCount).getMap(); //the boolean[][] map itself, gotten via Singleton
		
		root = new AnchorPane();
		drawMap();
		
		testGhost = new GhostShipFactory();
		testShipG = testGhost.createPirateShip(new FollowStrategy(dimensions, islandCount), dimensions, islandCount); //make ghost ship
		pirateShipImageView = loadImage("ghostPirate.png", testShipG.getPirateLocation()); //loadImage
		
		testPatrol = new PatrolShipFactory();
		testShipP = testPatrol.createPirateShip(new RandomChaseStrategy(dimensions, islandCount), dimensions, islandCount); //make patrol ship
		pirateShipImageView2 = loadImage("pirateShip.png", testShipP.getPirateLocation()); //loadImage
		
		
		
		monster = new Monster(scalingFactor, dimensions, islandCount);
		monster.addToPane(root.getChildren()); //add sprites
		
		treasure = new Treasure(OceanMap.getInstance(dimensions, islandCount).placeShip());
		treasureImageView = loadImage("treasure.jpg", treasure.getTreasureLocation());
		ship = new Ship(dimensions, islandCount, testShipG, testShipP, monster, treasure);
		shipImageView = loadImage("ship.png", OceanMap.getInstance(dimensions, islandCount).getShipLoc());
		
		scene = new Scene(root, 500, 500);
		mapStage.setTitle("Columbus Game");
		mapStage.setScene(scene);
		mapStage.show();
		monstersThread = new Thread(monster); //separate thread for monster
	    monstersThread.start();
		startSailing();		
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void stop(){
		monstersThread.stop();
	}
	
	public ImageView loadImage(String filename, Point location) { //generalized image loader
		Image tempImage = new Image(filename, 25,25, true, true);
		ImageView tempImageView = new ImageView(tempImage);
		tempImageView.setX(location.x*scalingFactor);
		tempImageView.setY(location.y*scalingFactor);
		root.getChildren().add(tempImageView);
		return tempImageView;
	}
	
	public void drawMap() { //standard draw map
		for(int x = 0; x < dimensions; x++){
			for(int y = 0; y < dimensions; y++){
				Rectangle rect = new Rectangle(x*scalingFactor,y*scalingFactor,scalingFactor,scalingFactor);
				rect.setStroke(Color.BLACK);
				if(islandMap[x][y])
				    rect.setFill(Color.GREEN);
				else
					rect.setFill(Color.PALETURQUOISE);
				root.getChildren().add(rect);
			}
		}
	}
	
	public void updateImageViews() { //update views at the end of each "move"
		shipImageView.setX(ship.getShipLocation().x*scalingFactor);
		shipImageView.setY(ship.getShipLocation().y*scalingFactor);
		pirateShipImageView.setX(testShipG.getPirateLocation().x*scalingFactor);
		pirateShipImageView.setY(testShipG.getPirateLocation().y*scalingFactor);
		pirateShipImageView2.setX(testShipP.getPirateLocation().x*scalingFactor);
		pirateShipImageView2.setY(testShipP.getPirateLocation().y*scalingFactor); 
	}
	
	public void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent ke) {
				switch(ke.getCode()){
				case RIGHT:
					ship.goRight();
					break;
				case LEFT:
					ship.goLeft();
					break;
				case UP:
					ship.goUp();
					break;
				case DOWN:
					ship.goDown();
					break;
				default:
					break;
				}
				updateImageViews();
				testShipG.checkFound(); //if ship finds CC, lose notif
				testShipP.checkFound(); //lose notif
				treasure.checkFound(); //win notif!
			}
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
