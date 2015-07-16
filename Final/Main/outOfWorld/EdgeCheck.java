package outOfWorld;

import elements.Asteroid;
import elements.Bossbullet;
import elements.Enemylaser;
import elements.Laser;
import elements.Lifeup;
import elements.Missile;
import elements.Powershot;
import managers.Manager;
import EnemyShips.Enemyship;
import greenfoot.Actor;
import greenfoot.GreenfootImage;

public class EdgeCheck extends Actor {
	int w;
	int h;
	GreenfootImage image = new GreenfootImage("Images/Clear.png");
	private Actor[] leave;
	public EdgeCheck(int w,int h){
		this.w = w;
		this.h = h;
		setImage(image);
		image.scale(w, h);
		image.fill();
		
	}
	public void act(){
		
		edgeCheck();
		
	}
	private void edgeCheck() {
		Actor pLaser = getOneIntersectingObject(Laser.class);
		Actor pMissile = getOneIntersectingObject(Missile.class);
		Actor eLaser = getOneIntersectingObject(Enemylaser.class);
		Actor bMissile = getOneIntersectingObject(Bossbullet.class);
		Actor enemy = getOneIntersectingObject(Enemyship.class);
		Actor lifeUp = getOneIntersectingObject(Lifeup.class);
		Actor rapidFire = getOneIntersectingObject(Powershot.class);
		Actor roid = getOneIntersectingObject(Asteroid.class);
		if(pLaser != null){
			getWorld().removeObject(pLaser);
		}
		else if(pMissile != null){
			getWorld().removeObject(pMissile);
		}
		else if(eLaser != null){
			getWorld().removeObject(eLaser);
		}
		else if(bMissile != null){
			getWorld().removeObject(bMissile);
		}
		else if(enemy != null){
			++Manager.maxenemy;
			getWorld().removeObject(enemy);
		}
		else if(lifeUp != null){
			--Manager.maxlifeup;
			getWorld().removeObject(lifeUp);
		}
		else if(rapidFire != null){
			--Manager.maxpowershot;
			getWorld().removeObject(rapidFire);
		}
		else if(roid != null){
			getWorld().removeObject(roid);
		}
	}

}
