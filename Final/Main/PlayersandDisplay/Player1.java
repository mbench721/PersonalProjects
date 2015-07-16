package PlayersandDisplay;
import elements.Asteroid;
import elements.Bossbullet;
import elements.Enemylaser;
import elements.Explosion;
import elements.Laser;
import elements.Lifeup;
import elements.Missile;
import elements.Powershot;
import managers.Manager;
import EnemyShips.Enemyship;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class Player1 extends Actor implements Players {
	public int counter;
	public int missileCounter;
	private int missileDelay = 35;
	public int delay = 10;
	private Laser laser;
	public Missile bullet;
	public Explosion explosion = new Explosion();
	private GreenfootSound sound = new GreenfootSound("Sounds/powerup.wav");
	private GreenfootImage ship = new GreenfootImage("Images/player1.png");
	
	final  int SPEED = 5;
	
	public Player1() {
		setImage(ship);
		ship.scale(50, 50);
		setRotation(270);
	}
	public void act(){
		checkPowerup();
		checkCollision();
		
		move();  
		}
	@Override
	public void move() {
		if(Greenfoot.isKeyDown("w")){
			this.move(SPEED);
			
		}
		if(Greenfoot.isKeyDown("s")){
			this.move(-SPEED);
			
		}
		if(Greenfoot.isKeyDown("a")){
			this.turn(-SPEED);
			
		}
		if(Greenfoot.isKeyDown("d")){
			this.turn(SPEED);
			
		}
		if (Greenfoot.isKeyDown("space"))  
	     {  
	          shoot();  
	     }
	}
	public void shoot()  
	{  
		counter++;
		if(counter >= delay){
			laser = new Laser();
			
			getWorld().addObject(laser, getX(), getY());
			laser.setRotation(this.getRotation());
			laser.setLocation(getX(), getY());
			counter = 0;
		}
		missileCounter++;
		if(Missile.canUse == true && Missile.missileNumber > 0 && missileCounter >= missileDelay){
			Missile missile = new Missile();
			
			this.getWorld().addObject(missile, this.getX(), this.getY());
			missile.setRotation(this.getRotation());
			missile.setLocation(this.getX(), this.getY());
			--Missile.missileNumber;
			missileCounter = 0;
			delay = 5;
			
		}
		else if (Missile.missileNumber ==0){
			Missile.canUse = false;
			delay = 10;
		}
	}  

	public void checkPowerup(){
		Actor lifeup = getOneIntersectingObject(Lifeup.class);
		Actor powershot = getOneIntersectingObject(Powershot.class);
		
		if(lifeup != null){
			sound.play();
			--Manager.maxlifeup;
			Lives.playerLives += 1;
			getWorld().removeObject(lifeup);
		}
		else if(powershot != null){
			sound.play();
			--Manager.maxpowershot;
			Missile.canUse = true;
			Missile.missileNumber = 10;
			getWorld().removeObject(powershot);
		}
	}
	
	public void checkCollision(){
		Actor roid = getOneIntersectingObject(Asteroid.class);
		Actor enemy = getOneIntersectingObject(Enemyship.class);
		Actor laser = getOneIntersectingObject(Enemylaser.class);
		Actor bossmissile = getOneIntersectingObject(Bossbullet.class);
		if(roid != null){
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			--Lives.playerLives;
			getWorld().removeObject(roid);	
		}
		else if(enemy != null ){
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			--Lives.playerLives;
			getWorld().removeObject(enemy);	
		}
		
		else if(laser != null){
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			--Lives.playerLives;
			getWorld().removeObject(laser);	
			
		}
		else if(bossmissile != null){
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			--Lives.playerLives;
			getWorld().removeObject(bossmissile);	
		}
		
	}
	
}