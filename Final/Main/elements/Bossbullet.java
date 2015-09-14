package elements;

public class Bossbullet extends Actor {
	private GreenfootImage bossPic = new GreenfootImage("Images/bossBullet.png");
	private int size = 25;
	private int width = 65;
	int x;
	int y;
	

	public Bossbullet() {
		setImage(bossPic);
		bossPic.scale(width, size);

	}
	public void act(){
		
		move(6);
		
	}
}




