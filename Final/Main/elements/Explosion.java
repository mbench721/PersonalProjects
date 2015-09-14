package elements;
import javax.swing.JOptionPane;

import main.Final;
import EnemyShips.Boss;
import PlayersandDisplay.*;

public class Explosion extends Actor {
	private GreenfootImage image;
	private int w;
	GreenfootSound splode = new GreenfootSound("Sounds/explosion_x.wav");

	public Explosion() {
		
		image = new GreenfootImage("Images/explosion.png");
		image.scale(image.getWidth() - 125, image.getHeight() - 125);
		w = 0;

	}

	public void act(){
		
		if( w < 3){
			try {
				Thread.sleep(10);
				image.scale(image.getWidth() +50, image.getHeight() + 50);
				setImage(image);	
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} 
		splode.setVolume(75);
		splode.play();

		if(w == 3){
			getWorld().removeObject(this);	
		}
		if (Boss.bossLife ==0){
			Boss.bossLife = 10;
			Score.score = 0;
			JOptionPane.showMessageDialog(null, "You Win!!");
			//Greenfoot.setWorld(new Final());

		}
		if(Lives.playerLives == 0 && Lives2.player2Lives == 0){
			JOptionPane.showMessageDialog(null, "Game Over, You got roided");
			Score.score = 0;
			Lives.playerLives = 3;
			Lives2.player2Lives = 3;
			
			Greenfoot.setWorld(new Final());
		}
		
		w++;
	}
}
