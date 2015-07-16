package PlayersandDisplay;
import elements.Asteroid;
import elements.Bossbullet;
import elements.Enemylaser;
import elements.Explosion;
import elements.Laser;
import elements.Lifeup;
import elements.Missile;
import elements.Powershot;
import main.Final;
import managers.Manager;
import EnemyShips.Enemyship;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

public class Player2 extends Actor implements Players {
	public int counter;
	public int missileCounter;
	private int missileDelay = 35;
	public int delay = 10;
	public Laser laser;
	public Missile missile;
	public Explosion explosion = new Explosion();
	GreenfootImage ship2 = new GreenfootImage("Images/player2.png");
	final  int SPEED = 5;
	public Player2() {
		ship2.scale(50, 50);
		setImage(ship2);
		setRotation(270);
	}
	public void act(){
		checkCollision();
		checkPowerup();
		move();  
	    
		}
	@Override
	public void move() {
		if(Greenfoot.isKeyDown("up")){
			this.move(SPEED);
		}
		if(Greenfoot.isKeyDown("down")){
			this.move(-SPEED);
		}
		if(Greenfoot.isKeyDown("left")){
			this.turn(-SPEED);	
		}
		if(Greenfoot.isKeyDown("right")){
			this.turn(SPEED);	
		}
		 if (Greenfoot.isKeyDown("enter"))
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
	laser.setRotation(getRotation());
	laser.setLocation(getX(), getY());
	counter = 0;
		} 
		missileCounter++;
		if(Missile.canUse2  && Missile.missileNumber > 0 && missileCounter >= missileDelay){
			missile = new Missile();
			Greenfoot.playSound("sounds/misslelaunch.mp3");
			getWorld().addObject(missile, getX(), getY());
			missile.setRotation(getRotation());
			missile.setLocation(getX(), getY());
			--Missile.missileNumber;
			missileCounter = 0;
			delay = 5;
			
		}
		else if (Missile.missileNumber == 0){
			Missile.canUse2 = false;
			delay = 10;
		}
	}  

	public void checkPowerup(){
		Actor lifeup = getOneIntersectingObject(Lifeup.class);
		Actor powershot = getOneIntersectingObject(Powershot.class);
		GreenfootSound sound = new GreenfootSound("Sounds/powerup.wav");
		if(lifeup != null){
			sound.play();
			--Manager.maxlifeup;
			Lives2.player2Lives += 1;
			getWorld().removeObject(lifeup);
		}
		else if(powershot != null){
			sound.play();
			--Manager.maxpowershot;
			Missile.canUse2 = true;
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
			--Lives2.player2Lives;
			getWorld().removeObject(roid);	
		}
		else if(enemy != null ){
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			--Lives2.player2Lives;
			getWorld().removeObject(enemy);	
		}
		else if(laser != null){
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			--Lives2.player2Lives;
			getWorld().removeObject(laser);	
			
		}
		else if(bossmissile != null){
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			--Lives2.player2Lives;
			getWorld().removeObject(bossmissile);	
		}		
	}	
}