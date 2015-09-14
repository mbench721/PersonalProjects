package elements;

public class Missile extends Actor {

	public static int missileNumber;
	public static boolean canUse = false;
	public static boolean canUse2 = false;

	public Missile() {
		setRotation(getRotation());
		setImage("Images/missile.png"); 
		Greenfoot.playSound("sounds/misslelaunch.mp3");
	}
	public void act(){

		move(6);
	}

}

