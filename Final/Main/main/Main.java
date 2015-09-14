package main;

import java.awt.Dimension;
import javax.swing.JFrame;

public class Main extends GreenfootUtil {
	
	

	public static void main(String[] args) {
		
		StartMenu menu = new StartMenu(args);
		
		JFrame init = new JFrame("Start Menu");
		Dimension dime = new Dimension(450,750);
		init.setLocation(600, 200);
		init.setPreferredSize(dime);
		init.pack();
		init.getContentPane().add(menu);
		init.setVisible(true);
		
	}
	
	}

