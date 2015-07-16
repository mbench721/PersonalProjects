package elements;
import greenfoot.Actor;
import greenfoot.GreenfootSound;

public class Laser extends Actor{
	private int size = 20;
	GreenfootSound lasers = new GreenfootSound("Sounds/phasers3.wav");
	public Laser(){
		this.setImage("Images/bullet.png");
		getImage().scale(size, size);
		lasers.setVolume(75);
		lasers.play();
		
	}

	public void act(){
		this.move(8);
		
	}
}