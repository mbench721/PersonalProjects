package main;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import javax.swing.JOptionPane;

import managers.Manager;
import PlayersandDisplay.*;
import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

public class Final extends World  {
	
	public static final int HEIGHT = 900, WIDTH = 1300, CELL = 1;
	public Random rgen = new Random();
	GreenfootImage bgImg = null;
	public int bgX;
	int counter = 0;
	final int starCount = 100;
	public Player1 ship;
	public Player2 ship2;
	Manager manage;
	GreenfootSound menu = new GreenfootSound("Sounds/Menu.mp3");
	
	public Final() {
		
		super(WIDTH,HEIGHT,CELL);
		initSetup();
		
	}

	private void initSetup() {
		createBackgroundImage();
		ship = new Player1();
		ship2 = new Player2();
		Score scoreGUI = new Score();
		addObject(scoreGUI, 30 + scoreGUI.getImage().getWidth(), 0 + scoreGUI.getImage().getHeight());
		playerSelect();
		//menu.playLoop();
		manage = new Manager(this,ship,ship2);
	}

	private void playerSelect() {
		int playerNumber= 0;
		int difficulty = 0;
		FileReader freader= null;
		try {
			
			freader=new FileReader("Setup.txt");
			BufferedReader inputFile=new BufferedReader(freader);
			playerNumber = Integer.valueOf(inputFile.readLine());
		    difficulty = Integer.valueOf(inputFile.readLine());

		} catch (IOException e) {

			e.printStackTrace();
		}
		finally{
			if(freader != null){
				try {

					freader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}
		if (playerNumber ==  1){
			Lives2.player2Lives = 0;
			addObject((Actor) ship,WIDTH / 3,HEIGHT - 25);
			Lives livesGUI = new Lives();
			addObject(livesGUI, (WIDTH -325) - livesGUI.getImage().getWidth(), 0 + livesGUI.getImage().getHeight());
			
		}
		else if(playerNumber == 2){
			Lives livesGUI = new Lives();
			addObject(livesGUI, (WIDTH -650) - livesGUI.getImage().getWidth(), 0 + livesGUI.getImage().getHeight());
			Lives2 lives2GUI = new Lives2();
			
			addObject(lives2GUI, (WIDTH -325) - lives2GUI.getImage().getWidth(), 0 + lives2GUI.getImage().getHeight());
			addObject(ship,WIDTH / 3,HEIGHT - 25);
			addObject(ship2,WIDTH / 3 * 2,HEIGHT - 25);
			
		}
		 
	}
	
	public void act(){
		manage.act();
		//menu.stop();
		scrollBackground();
		//Manager.level1.playLoop();
		checkPlayerDeath();	
	}

	private void checkPlayerDeath() {
		if(Lives.playerLives == 0 && Lives2.player2Lives == 0){
			Manager.level1.stop();
		}
		
		if(Lives.playerLives == 0){
			removeObject((Actor) ship);
		}
		else if(Lives2.player2Lives == 0){
			removeObject(ship2);
		}
	}

	
	
	private void createBackgroundImage()  
	{  
		bgImg = new GreenfootImage(getWidth(), getHeight());  
		bgImg.setColor(Color.BLACK); 
		
		bgImg.fill();  
		bgImg.setColor(Color.WHITE);  
		for (int i = 0; i < starCount; i++)  
		{  
			int x = Greenfoot.getRandomNumber(getWidth());  
			int y = Greenfoot.getRandomNumber(getHeight());  
			bgImg.fillOval(x, y, rgen.nextInt(5) + 1, rgen.nextInt(5) + 1);  
		}  
		for(int i = 0; i < starCount; ++i){
			GreenfootImage img = new GreenfootImage(getWidth(), getHeight());  
			img.drawImage(bgImg, 0, bgX);  
			img.drawImage(bgImg, 0, bgX - getHeight());  
			setBackground(img);  

		}

	}
	private void scrollBackground(){ 
		
		bgX = (bgX + 2) % getHeight();  
		GreenfootImage img = new GreenfootImage(getWidth(), getHeight());  
		img.drawImage(bgImg, 0, bgX);  
		img.drawImage(bgImg, 0, bgX - getHeight());  
		setBackground(img);
			  
	}
	
}




