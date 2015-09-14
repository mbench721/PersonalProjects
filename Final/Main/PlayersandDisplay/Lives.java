package PlayersandDisplay;
import java.awt.Color;

public class Lives extends Actor{
	public static int playerLives = 3;
	public static int player2Lives = 3;
	public static int coolDown = 100;
	
	public void act(){
		this.setImage(new GreenfootImage("Player 1 Lives: " + playerLives, 30, Color.GREEN, Color.BLACK));
		Lives.coolDown--;
			
	}
}