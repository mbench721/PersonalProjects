package PlayersandDisplay;
import java.awt.Color;

public class Score extends Actor{
	public static int score = 0;
	
	public void act(){
		this.setImage(new GreenfootImage("Score: " + score, 30, Color.RED, Color.BLACK));
	}
	
	public static void add(){
		score++;
	}
}