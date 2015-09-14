package managers;

import java.util.ArrayList;
import java.util.List;


import controllers.PlayerController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import mobile.Deployable;
import mobile.Player;
import settings.Settings;

public class SpawnManager {

	private Scene scene;
	private GameManager manage;
	private List<Deployable> players = new ArrayList<>();
	Image playerImage;

	public SpawnManager(Scene s,GameManager m){
		this.manage = m;
		this.scene = s;
		//this.loadGame();
		createPlayers(manage);
	}
	 private void loadGame() {
	        playerImage = new Image( getClass().getResource("src/application/player1.png").toExternalForm());
	        //enemyImage = new Image( getClass().getResource("enemy1.png").toExternalForm());
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
