package elements;

public class Powershot extends Actor{
	GreenfootSound sound = new GreenfootSound("sounds/powerup.wav");
	public static boolean collected = false;
	
	public Powershot(){
		this.setImage("images/rapidFire.png");
		this.getImage().scale(15, 6);		
	}

	public void act(){
		
		setRotation(90);
		this.move(2);
	}
}