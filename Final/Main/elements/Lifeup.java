package elements;

import greenfoot.Actor;
import greenfoot.GreenfootSound;

public class Lifeup extends Actor{
	GreenfootSound sound = new GreenfootSound("Sounds/powerup.wav");
	public static boolean collected = false;

	public Lifeup(){
		this.setImage("Images/lifeUp.png");
		this.getImage().scale(15, 6);
	}

	public void act(){
		
		setRotation(90);
		
		this.move(2);
	}
	
		
}
