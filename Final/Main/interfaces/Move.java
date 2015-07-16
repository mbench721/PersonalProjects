package interfaces;

import java.util.Random;

public interface Move {
	public Random rgen = new Random();
	public final int TOP = 270,RIGHT = 180, LEFT = 0,DIAGONAL_LEFT = 146, DIAGONAL_RIGHT = 34,SPINNER = 5;
	public final int REG_ENEMY_MOVE = rgen.nextInt(2)-4,SUICIDE_MOVE = -8,SPINNER_MOVE = 0,SPINNER_TURN = 2;
	public void move(Move s);
	public void detectDeath();
	public void shoot();
	public void turn();

}
