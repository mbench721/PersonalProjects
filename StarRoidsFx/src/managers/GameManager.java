package managers;

import java.util.ArrayList;
import java.util.List;


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import menu.StartMenu;
import mobile.Deployable;

public class GameManager {
	private Scene scene;
	private SpawnManager spawn;
	Pane playfieldLayer;
    Pane scoreLayer;
    Pane backSpace;
   
   
	private StartMenu start;
	public GameManager(Scene s){
		
		this.scene = s;
		//createStartMenu();
		setLayers();
		createSpawnManage();
		
	}
	
	public void createStartMenu(){
		start = new StartMenu(scene);
	}
	public void setLayers(){
		
		Group root = new Group();
		backSpace = new Pane();
        playfieldLayer = new Pane();
        scoreLayer = new Pane();
        root.getChildren().add(backSpace);
        root.getChildren().add( playfieldLayer);
        root.getChildren().add( scoreLayer);
       
	}
	public void createSpawnManage(){
		spawn = new SpawnManager(scene,this);
	}

}
