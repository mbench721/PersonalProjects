package managers;
import interfaces.Move;

import java.util.ArrayList;
import java.util.Random;

import outOfWorld.EdgeCheck;
import elements.Asteroid;
import elements.Lifeup;
import elements.Powershot;
import enums.Direction;
import spawning.SpawnPoints;
import EnemyShips.*;
import PlayersandDisplay.Player1;
import PlayersandDisplay.Player2;
import PlayersandDisplay.Score;
import greenfoot.Actor;
import greenfoot.GreenfootSound;
import greenfoot.World;


public class Manager extends Actor {
	public static GreenfootSound level1 = new GreenfootSound("Sounds/level1.mp3");
	private SpawnPoints spawnTop;
	private SpawnPoints spawnSides;
	private ArrayList<SpawnPoints> spawns = new ArrayList<SpawnPoints>();;
	private Random rgen = new Random();
	private World world;
	private  Boss boss;
	private Player1 one;
	private Player2 two;
	public ArrayList<Move> enemys = new ArrayList<Move>();
	public static int maxenemy = 12, maxroid = 6 ,maxlifeup = 0,maxpowershot = 0;
	private int randEnemyandRoid;
	
	public Manager(World world,Player1 o,Player2 t){
		spawnTop = new SpawnPoints(100,20);
		spawnSides = new SpawnPoints(20,100);
		spawns = spawnTop.addTopBotSpawn(spawns,world, 10);
		spawns = spawnSides.addLeftRightSpawn(spawns,world, 10);
		spawns = spawnSides.addLeftRightSpawn(spawns,world, 1290);
		System.out.println(spawns.size());
		this.world = world;
		this.one = o;
		this.two = t;
		addBorder();
		boss = new Boss(one,two);
		bossStart();
		
	}
	
	public void act(){
		
		createEnemy();
		addRoid();
		addPowerUp();
	}
	
	public Enemyship addEnemy(Direction direction,int x, int y){
		Enemyship enemy = new Enemyship(one);
		world.addObject(enemy, x, y);
		enemy.setRotation(direction.getRotation());
		return enemy;
	}

	private void createEnemy(){

		level1();
		level2();
		level3();
		bossStart();	
	}
	private void level2() {
		randEnemyandRoid = rgen.nextInt(200) + 1;
		if(Score.score >= 5){
			maxenemy = 15;
			if(randEnemyandRoid <= 1 && maxenemy > 0){
				enemys.add(addEnemy(Direction.SPINNER,rgen.nextInt(world.getWidth()),rgen.nextInt(world.getHeight())));
				
				--maxenemy;
			}
		}
	}
	private void level3() {
		randEnemyandRoid = rgen.nextInt(100) + 1;
		if(Score.score >= 10){
			maxenemy = 20;
			if(randEnemyandRoid == 40 && maxenemy > 0){
				enemys.add(addEnemy(Direction.DIAGONAL_LEFT,100,750));
				--maxenemy;
			}
			if(randEnemyandRoid == 50 && maxenemy > 0){
				enemys.add(addEnemy(Direction.DIAGONAL_RIGHT,1200,800));
				
			--maxenemy;
			}
		}
	}
	private void level1() {
		randEnemyandRoid = rgen.nextInt(300) + 1;
		if(randEnemyandRoid ==1 && maxenemy > 0){

			enemys.add(addEnemy(Direction.TOP,spawns.get(rgen.nextInt(3) + 1).getX() , 55));
			--maxenemy;
		} 
		if(randEnemyandRoid == 10 && maxenemy > 0){

			enemys.add(addEnemy(Direction.RIGHT, 55 ,spawns.get(rgen.nextInt(5) +  2).getY()));
			--maxenemy;
		}
		if(randEnemyandRoid == 20 && maxenemy > 0){

			enemys.add(addEnemy(Direction.LEFT, world.getWidth() - 55, spawns.get(rgen.nextInt(5) +  6).getY()));
			--maxenemy;
		}
	}

	private void bossStart() {

		if(Score.score >= 15){
			
			level1.stop();
			world.removeObjects(world.getObjects(Enemyship.class));
			world.addObject(boss,world.getWidth() / 2,100);
			boss.bossBattle();
		}
	}
	public void addRoid(){

		if(randEnemyandRoid <=1 && maxroid > 0){
			int roidSize = rgen.nextInt(35) + 35;
			Asteroid roid = new Asteroid(world,roidSize);
			world.addObject(roid, rgen.nextInt(spawns.get(rgen.nextInt(5) + 1).getX()), 50);
			--maxroid;
		}
	}
	private void addPowerUp() {
		int randPower = rgen.nextInt(1000)+ 1;
		if(randPower == 1 && maxlifeup < 1){
			Lifeup lifeup = new Lifeup();
			world.addObject(lifeup, rgen.nextInt(spawns.get(rgen.nextInt(5) + 1).getX()), 10);
			++maxlifeup;
		}
		if(randPower == 2 && maxpowershot < 1){
			Powershot tempmissile = new Powershot();
			world.addObject(tempmissile,rgen.nextInt(spawns.get(rgen.nextInt(5) + 1).getX()), 10);
			++maxpowershot;
		}
	}
	public void addEdge(int i,int x,int y,int w, int h){
		
		world.addObject(new EdgeCheck(w,h), x, y);	
	}
	public void addBorder(){
		addEdge(0, 0, world.getHeight() / 2,2,world.getHeight());
		addEdge(0,world.getWidth(), world.getHeight() / 2,2,world.getHeight());
		addEdge(0,world.getWidth() / 2,0, world.getWidth(),2);
		addEdge(0,world.getWidth() / 2, world.getHeight(),world.getWidth(),2);
	}
}
