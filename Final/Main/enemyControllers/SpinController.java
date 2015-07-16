package enemyControllers;

import interfaces.Move;
import EnemyShips.Spinner;

public class SpinController {
	private Spinner spin;
	private Move move;
	
	public SpinController(Spinner s){
		this.spin = s;
		
	}
	public void control(){
		spin.turn(Move.SPINNER_TURN);
	}
}
