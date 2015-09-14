package PlayersandDisplay;
import java.awt.Color;

public class Lives2 extends Actor{
	public static int player2Lives = 3;
	public static int coolDown = 100;
	
	public void act(){
		this.setImage(new GreenfootImage("Player 2 Lives: " + player2Lives, 30, Color.CYAN, Color.BLACK));
		Lives.coolDown--;
		
		
	}
}