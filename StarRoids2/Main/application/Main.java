package application;
	
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;


public class Main extends Application {
	
	 Random rnd = new Random();

	    Pane playfieldLayer;
	    Pane scoreLayer;
	    Pane backSpace;
	    Circle test;
	    Image playerImage;
	    Image enemyImage;
	    int starSize = 2;
	    WritableImage star = new WritableImage(starSize,starSize);

	    List<Player> players = new ArrayList<>();
	    List<Enemy> enemies = new ArrayList<>();
	    List<ScrollStars> scroll = new ArrayList<>();

	    Text collisionText = new Text();
	    boolean collision = false;

	    Scene scene;
	@Override
	public void start(Stage primaryStage) {
		
		Group root = new Group();
		
        // create layers
		backSpace = new Pane();
        playfieldLayer = new Pane();
        scoreLayer = new Pane();
        
        root.getChildren().add(backSpace);
        root.getChildren().add( playfieldLayer);
        root.getChildren().add( scoreLayer);
       
        
        

        scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT,Color.BLACK);

        primaryStage.setScene( scene);
        primaryStage.show();

        loadGame();
        createScoreLayer();
        createPlayers();

        AnimationTimer gameLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {

                // player input
                players.forEach(player -> player.processInput());
               spawnStars(true);
                // add random enemies
                spawnEnemies( true);

                // movement
                players.forEach(player -> player.move());
                enemies.forEach(enemy -> enemy.move());
                scroll.forEach(enemy -> enemy.move());

                // check collisions
                checkCollisions();

                // update ships in scene
                players.forEach(player -> player.updateUI());
                enemies.forEach(enemy -> enemy.updateUI());
                scroll.forEach(enemy -> enemy.updateUI());

                // check if ship can be removed
                enemies.forEach(enemy -> enemy.checkRemovability());
                scroll.forEach(enemy -> enemy.checkRemovability());

                // remove removables from list, layer, etc
                removeShips( enemies);

                // update score, health, etc
                updateScore();
            }

        };
        gameLoop.start();

    }

    private void loadGame() {
        playerImage = new Image( getClass().getResource("player1.png").toExternalForm());
        enemyImage = new Image( getClass().getResource("enemy1.png").toExternalForm());
    }

    private void createScoreLayer() {


        collisionText.setFont( Font.font( null, FontWeight.BOLD, 64));
        collisionText.setStroke(Color.BLACK);
        collisionText.setFill(Color.RED);

        scoreLayer.getChildren().add( collisionText);

        // TODO: quick-hack to ensure the text is centered; usually you don't have that; instead you have a health bar on top
        collisionText.setText("Collision");
        double x = (Settings.SCENE_WIDTH - collisionText.getBoundsInLocal().getWidth()) / 2;
        double y = (Settings.SCENE_HEIGHT - collisionText.getBoundsInLocal().getHeight()) / 2;
        collisionText.relocate(x, y);
        collisionText.setText("");

        collisionText.setBoundsType(TextBoundsType.VISUAL);


    }
    private void createPlayers() {

        // player input
        PlayerController pControl = new PlayerController( scene);

        // register input listeners
        pControl.addListeners(); // TODO: remove listeners on game over

        Image image = playerImage;

        // center horizontally, position at 70% vertically
        double x = (Settings.SCENE_WIDTH - image.getWidth()) / 2.0;
        double y = Settings.SCENE_HEIGHT * 0.7;

        // create player
        Player player = new Player(playfieldLayer, image, x, y, 0, 0, 0, 0, Settings.PLAYER_SHIP_HEALTH, 0, Settings.PLAYER_SHIP_SPEED, pControl);

        // register player
        players.add( player);

    }
    private void spawnStars(boolean random){
    	if(random && rnd.nextInt(Settings.STAR_SPAWN_RANDOMNESS) !=0){
    		return;
    	}
    	starSize = rnd.nextInt(5) +1;
    	double speed = rnd.nextDouble() * 1.0 + 2.0;
    	 test = new Circle(starSize);
 		test.setFill(Color.WHITE);
 		test.snapshot(null, star);
 		double sx = rnd.nextDouble() * (Settings.SCENE_WIDTH - star.getWidth());
 		double sy = star.getHeight();
         ScrollStars scrollStars = new ScrollStars(backSpace, star, sx , sy, 0.0, 0.0, speed, 0.0,0.0,0.0);
         scroll.add(scrollStars);
    	
    }

    private void spawnEnemies( boolean random) {

        if( random && rnd.nextInt(Settings.ENEMY_SPAWN_RANDOMNESS) != 0) {
            return;
        }

        // image
        Image image = enemyImage;

        // random speed
        double speed = rnd.nextDouble() * 1.0 + 2.0;

        // x position range: enemy is always fully inside the screen, no part of it is outside
        // y position: right on top of the view, so that it becomes visible with the next game iteration
        double x = rnd.nextDouble() * (Settings.SCENE_WIDTH - image.getWidth());
        double y = -image.getHeight();
        
        // create a sprite
        Enemy enemy = new Enemy( playfieldLayer, image, x, y, 0, 0, speed, 0, 1,1);
       
        // manage sprite
        enemies.add(enemy);

    }

    private void removeShips(  List<? extends GeneralControls> shipList) {
        Iterator<? extends GeneralControls> iter = shipList.iterator();
        while( iter.hasNext()) {
            GeneralControls ship = iter.next();

            if( ship.isRemovable()) {

                // remove from layer
                ship.removeFromLayer();

                // remove from list
                iter.remove();
            }
        }
    }

    private void checkCollisions() {

        collision = false;

        for( Player player: players) {
            for( Enemy enemy: enemies) {
                if( player.collidesWith(enemy)) {
                    collision = true;
                }
            }
        }
    }

    private void updateScore() {
        if( collision) {
            collisionText.setText("Collision");
        } else {
            collisionText.setText("");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
