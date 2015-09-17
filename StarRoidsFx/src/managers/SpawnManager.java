package managers;

import java.util.ArrayList;
import java.util.List;


import controllers.PlayerController;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import mobile.Deployable;
import mobile.Player;
import settings.Settings;

public class SpawnManager {

	private Scene scene;
	private GameManager manage;
	private List<Player> players = new ArrayList<>();
	Image playerImage;

	public SpawnManager(Scene s,GameManager m){
		this.manage = m;
		this.scene = s;
		//this.loadGame();
		createPlayers(manage);
		//loadGame();
	}
	 private void loadGame() {
		 AnimationTimer gameLoop = new AnimationTimer() {

	         @Override
	         public void handle(long now) {

	             // player input
	             players.forEach(player -> player.processInput());
	          //  spawnStars(true);
	             // add random enemies
	            // spawnEnemies( true);

	             // movement
	             players.forEach(player -> player.move());
	          //   enemies.forEach(enemy -> enemy.move());
	           //  scroll.forEach(enemy -> enemy.move());

	             // check collisions
	            // checkCollisions();

	             // update ships in scene
	             players.forEach(player -> player.updateUI());
	            // enemies.forEach(enemy -> enemy.updateUI());
	            // scroll.forEach(enemy -> enemy.updateUI());

	             // check if ship can be removed
	            // enemies.forEach(enemy -> enemy.checkRemovability());
	             //scroll.forEach(enemy -> enemy.checkRemovability());

	             // remove removables from list, layer, etc
	            // removeShips( enemies);

	             // update score, health, etc
	            // updateScore();
	         }

	     };
	     gameLoop.start();

	 }
	    
	private void createPlayers(GameManager g) {

		// player input
		PlayerController pControl = new PlayerController( scene);

		// register input listeners
		pControl.addListeners(); // TODO: remove listeners on game over

		//Image image = playerImage;
		 playerImage = new Image("player1.png");
		
		// center horizontally, position at 70% vertically
		double x = (Settings.SCENE_WIDTH - playerImage.getWidth()) / 2.0;
		double y = Settings.SCENE_HEIGHT * 0.7;

		// create player
		Player player = new Player(g.playfieldLayer, playerImage, x, y, 0, 0, 0, 0, Settings.PLAYER_SHIP_HEALTH, 0, Settings.PLAYER_SHIP_SPEED, pControl);
		//System.out.println(player.getImage());
		// register player
		players.add( player);

	}

}
