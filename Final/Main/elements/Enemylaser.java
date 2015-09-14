package elements;


public class Enemylaser extends Actor{
	private int size = 20;
	public Enemylaser(){
		setImage("Images/bullet.png");
		getImage().scale(size, size);
	}

	public void act(){
		this.move(11);
		
		
	}
}
