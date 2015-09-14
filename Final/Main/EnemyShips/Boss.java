package EnemyShips;
import elements.Bossbullet;
import elements.Enemylaser;
import elements.Explosion;
import elements.Laser;
import elements.Missile;
import java.util.Random;

import PlayersandDisplay.Player1;
import PlayersandDisplay.Player2;
public class Boss extends Actor{
	
	private GreenfootImage boss = new GreenfootImage("Images/pboss2.png");
	GreenfootSound bossbattle = new GreenfootSound("Sounds/bossbattle.mp3");
	public Random rgen = new Random();
	public static int bossLife = 50;
	public Explosion explosion;
	private Bossbullet bossBullet;
	private Enemylaser bossLaser;
	private int randShot;
	private Player1 one;
	private Player2 two;
	private int randMove;
	private final int  MOVE = 1, STOP = 0, BOOST = 3;
	private Movement move;
	public Boss(Player1 o,Player2 t) {
		
		this.one = o;
		this.two = t;
		setImage(boss);
		setRotation(270);
		move = Movement.STOP;
		
	}
	
	public void act(){
		
		//bossbattle.playLoop();
		randShot = rgen.nextInt(200) + 1;
		randMove = rgen.nextInt(400) + 1;
		move();
		
		checkDeath();
	}

	private void move() {
		if(randMove == 1){
			move = Movement.MOVE;
		}
		if(randMove == 2){
			move = Movement.STOP;
		}
//		if(randMove == 1){
//			move = Movement.BOOST;
//		}
		switch(move){
		case MOVE:
			move(MOVE);
			break;
		case STOP:
			move(STOP);
			break;
		case BOOST:
			move(BOOST);
			break;
		
		}
	}

	private void checkDeath() {
		Actor bullet = getOneIntersectingObject(Missile.class);
		Actor laser = getOneIntersectingObject(Laser.class);
		if (bullet != null ){
			--bossLife;
			getWorld().removeObject(bullet);
			
		}
		else if(laser != null){
			--bossLife;
			getWorld().removeObject(laser);
		}
		
		if(bossLife == 0){
			bossbattle.stop();
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX() +100, this.getY() + 100);
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX() -100, this.getY() - 100);
		}
	}
	
	public void bossBattle(){

		//move(1);
		turnTowards(one.getX(),one.getY());
		
				addBulletUp();
				addBullet();
				if (bossLife <= 25){
					
					halfLife();
				}
					
			}
			
	public void addBullet(){
		if(randShot == 1){
			bossBullet = new Bossbullet();
			getWorld().addObject(bossBullet,getX() - 50,getY() - 50);
			bossBullet.setRotation(this.getRotation());
			bossBullet = new Bossbullet();
			getWorld().addObject(bossBullet,getX() + 50,getY() + 50);
			bossBullet.setRotation(this.getRotation());
			
			}

		
	}
	public void halfLife(){
		if(rgen.nextInt(300) + 1 == 4){
		for(int i = 0; i < 360; i += 15){
			bossLaser = new Enemylaser();
			getWorld().addObject(bossLaser, getX(), getY());
			bossLaser.setRotation(getRotation() + i);
		}
		}
			
		if(randShot == 3){
			bossBullet = new Bossbullet();
			getWorld().addObject(bossBullet,getX() - 50,getY() - 50);
			bossBullet.setRotation(this.getRotation());
			bossBullet = new Bossbullet();
			getWorld().addObject(bossBullet,getX()+ 50,getY() + 50);
			bossBullet.setRotation(this.getRotation());
			}
			
	}
	
	public void addBulletUp(){
		if(randShot == 2){
			bossBullet = new Bossbullet();
			getWorld().addObject(bossBullet,getX(),getY());
			bossBullet.setRotation(this.getRotation() + 45);
			}
		if(randShot == 2){
			bossBullet = new Bossbullet();
			getWorld().addObject(bossBullet,getX(),getY());
			bossBullet.setRotation(this.getRotation()  - 45);
			}
		
	}
}	


