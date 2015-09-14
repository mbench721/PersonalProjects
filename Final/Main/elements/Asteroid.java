package elements;
import java.util.Random;

import managers.Manager;

public class Asteroid extends Actor{
	private World world;
	private GreenfootImage roid = new GreenfootImage("images/Asteroid.png");
	Random rgen = new Random();
	int moveSpeed = rgen.nextInt(3) + 1;
	int size;
	
	public Asteroid(World world,int width ){
		this.world = world;
		this.size = width;
		setImage(roid);
		roid.scale(size, size);
		setRotation(90);
	}
	public void act(){
		
		checkDeath(world,this);
        move(moveSpeed);
	}
	public void duplicate(World world,Asteroid a){
		int smallerSize = rgen.nextInt(25) + 25;
		int splitSize = 60;
		Asteroid duplicate = new Asteroid(world,smallerSize);
		Asteroid duplicate2 = new Asteroid(world,smallerSize);
		if(a.getSize() > splitSize){
			world.addObject(duplicate, a.getX() + a.getSize() , a.getY());
			world.addObject(duplicate2, a.getX() - a.getSize(), a.getY());
		}
	
}
	private void checkDeath(World world,Asteroid a) {
		Explosion explosion = new Explosion();
		Actor laser =  getOneIntersectingObject(Laser.class);
		Actor missile = getOneIntersectingObject(Missile.class);
		if(laser != null){
			++Manager.maxroid;
			
				getWorld().addObject(explosion, this.getX(), this.getY());
				duplicate(world,a);
				 getWorld().removeObject(laser);
				getWorld().removeObject(this); 
		}
		
		else if(missile != null){
			++Manager.maxroid;
			 explosion = new Explosion();
				this.getWorld().addObject(explosion, this.getX(), this.getY());
			getWorld().removeObject(missile);
			getWorld().removeObject(this);
		}
		
	}	
	public int getSize(){
		return size;
		
	}
}