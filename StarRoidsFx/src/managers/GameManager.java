package managers;

import javafx.scene.Scene;
import menu.StartMenu;

public class GameManager {
	private Scene scene;
	private StartMenu start;
	public GameManager(Scene s){
		
		this.scene = s;
		createStartMenu();
	}
	
	public void createStartMenu(){
		start = new StartMenu(scene);
	}

}
