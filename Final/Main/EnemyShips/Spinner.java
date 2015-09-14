package EnemyShips;

import interfaces.Move;
import enemyControllers.SpinController;

public class Spinner extends Enemys {
	
	private GreenfootImage spinner = new GreenfootImage("Images/spinner.png");
	private SpinController control;
	private int enemySize = 50;
	
	public Spinner(SpinController spin){
		this.control = spin;
		spinner.scale(enemySize, enemySize);
		setImage(spinner);
		
	}
	public void act(){
		control.control();
		
	}
	@Override
	public void detectDeath() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shoot() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Move s) {
		// TODO Auto-generated method stub
		
	}
}
