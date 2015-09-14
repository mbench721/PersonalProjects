package EnemyShips;

import interfaces.Move;

import java.util.Random;

import elements.Enemylaser;
import elements.Explosion;
import elements.Laser;
import elements.Missile;
import managers.Manager;
import PlayersandDisplay.Player1;
import PlayersandDisplay.Score;

public class Enemyship extends Actor implements Move {
	public Random rgen = new Random();
	private GreenfootImage enemy1 = new GreenfootImage("Images/enemy1.png");
	private GreenfootImage enemy2 = new GreenfootImage("Images/enemy2.png");
	private GreenfootImage enemy3 = new GreenfootImage("Images/enemy3.png");
	private GreenfootImage spinner = new GreenfootImage("Images/spinner.png");
	private GreenfootImage suicide  =new GreenfootImage("Images/Suicide.png");
	private GreenfootImage[] shipPics = {enemy1,enemy2,enemy3,spinner,suicide};
	private boolean left,up,down;
	private boolean topTurn = false,leftTurn = false,rightTurn = false,diagonalLeft = false,diagonalRight = false,spin = false ;
	private int regTurn;
	private int negTurn ;
	private int fullTurn = 360;
	private int coolDown = 100;
	private Player1 one;
	private Move move;
	Explosion explosion;
	
	public Enemyship(Player1 o){
			this.one = o;
			int shipPic = rgen.nextInt(3);
			regTurn =  rgen.nextInt(4) + 1;
			negTurn =  rgen.nextInt(4) - 4;
			setImage(shipPics[shipPic]);
			shipPics[0].scale(50, 50);
			shipPics[1].scale(50, 50);
			shipPics[2].scale(50, 50);
			shipPics[4].scale(50, 50);
	}
	
	public void act(){
		shoot();
		checkDirection();
		move(move);
		detectDeath();
		

	}
	private void checkDirection() {
		if(getRotation() == TOP){
			topTurn = true;
		}
		else if(getRotation() == RIGHT){
			rightTurn = true;
		}
		else if(getRotation() == LEFT){
			leftTurn = true;
		}
		else if(getRotation() == DIAGONAL_LEFT){
			diagonalLeft = true;
		}
		else if(getRotation() == DIAGONAL_RIGHT){
			diagonalRight = true;
		}
		else if(getRotation() == SPINNER){
			spin = true;
		}
	}

	

	
	@Override
	public void move(Move s) {

		if( topTurn && getRotation() <= fullTurn - 30 && getRotation() >= fullTurn - 150){
			move(REG_ENEMY_MOVE);
			if(left == true){
				
				turn(regTurn);
			}
			else if ( left == false){
				turn(negTurn);
			}
			if(getRotation() == fullTurn - 30){
				left = false;
			}
			if(getRotation() == fullTurn - 150){
				left = true;
			}
		}

		else if( rightTurn && getRotation() <= fullTurn - 120 && getRotation()>= 120){
			move(REG_ENEMY_MOVE);
			if(down == true){
				turn(regTurn);
			}
			else if ( down == false){
				turn(negTurn);
			}
			if(getRotation() == fullTurn - 120){
				down = false;
			}
			if(getRotation() == fullTurn - 240){
				down = true;
			}
		}

		else if(leftTurn && getRotation() <= fullTurn - 300 || leftTurn && getRotation() >= fullTurn - 60){
			
			move(REG_ENEMY_MOVE);
			if(up == true){
				turn(regTurn);
			}
			else if ( up == false){
				turn(negTurn);
			}
			if(getRotation() == fullTurn - 300){
				up = false;
			}
			if(getRotation() == fullTurn - 60){
				up = true;
			}
		}
		
		else if (diagonalLeft){
			
			setImage(shipPics[4]);
			move(SUICIDE_MOVE);
			//turnTowards(one.getX(),one.getY());
			
		}
		else if (diagonalRight){
			
			setImage(shipPics[4]);
			move(SUICIDE_MOVE);
			//turnTowards(one.getX(),one.getY());
			
		}
		else if(spin){
			setImage(shipPics[3]);
			turn(2);
		}
	}




	@Override
	public void detectDeath() {
		
		Actor laser = getOneIntersectingObject(Laser.class);
		Actor missile = getOneIntersectingObject(Missile.class);
		
		
		
		if(laser != null){
			Score.add();
			++Manager.maxenemy;
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			getWorld().removeObject(laser);
			getWorld().removeObject(this);

		}

		else if( missile != null){
			Score.add();
			++Manager.maxenemy;
			explosion = new Explosion();
			getWorld().addObject(explosion, this.getX(), this.getY());
			getWorld().removeObject(missile);
			getWorld().removeObject(this);
		}
		
		

	}
	@Override
	public void shoot() {
		int shoot = 0;
		Actor laser = new Enemylaser();;
		if(coolDown == shoot){

			getWorld().addObject(laser, getX(), getY());
			laser.setRotation(getRotation() - 180);
			coolDown = 100;
		}
		else{
			--coolDown;
		}

	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	
}
