package spawning;

import java.awt.Color;
import java.util.ArrayList;

public class SpawnPoints extends Actor {
	
	private int width;
	private int height;
	private GreenfootImage image;
	
	public SpawnPoints(int width,int height){
		
		image = new GreenfootImage(width,height);
		//image.setColor(Color.WHITE);
		image.fill();
		setImage(image);
		
	}
	public ArrayList<SpawnPoints> addTopBotSpawn(ArrayList<SpawnPoints> s,World world, int y){
		
		for (int i = 200; i < world.getWidth(); i += 300){
			SpawnPoints points = new SpawnPoints(100,25);
			world.addObject(points,  i, y);
			s.add(points);
		}
		
		return s;
	}
	public ArrayList<SpawnPoints> addLeftRightSpawn(ArrayList<SpawnPoints> s,World world, int y){
		
		for (int i = 150; i < world.getHeight(); i += 200){
			SpawnPoints points = new SpawnPoints(25,100);
			points.width = 25;
			points.height = 100;
			world.addObject(points,  y, i);
			s.add(points);
		}
		
		return s;
	}

}
