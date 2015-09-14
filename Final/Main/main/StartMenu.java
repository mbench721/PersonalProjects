package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartMenu extends JPanel {

	JLabel[] startText = {new JLabel("Welcome to StarRoids"),new JLabel("Difficulty?"),new JLabel("How Many players?"),new JLabel("Start Options")};
	JLabel[] playerControl = {new JLabel("Player 1 Controls \n Up = w " )};
	private float[] startTextSize = {40f,80f,42f,65f};
	JButton[] numPlayers = {new JButton("1 Player"), new JButton("2 Player")}; 
	JButton[] difficultySelect = {new JButton("Easy"),new JButton("Regular"),new JButton("Hard"),new JButton("Insanity")};
	JButton[] startOptions = {new JButton("Start"),new JButton("Player 1 Controls"), new JButton("Player 2 Controls")};
	Dimension testSize = new Dimension(200,100);
	Dimension size2 = new Dimension(135, 50);
	ButtonListener listen = new ButtonListener();
	public String players = "0";
	public String difficulty = "0";
	public String[] start;
	public boolean started;

	public StartMenu(String[] args){
		start = args;


		menuSetUp();
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension (500,500));
	}

	private void menuSetUp() {
		add(playerControl[0]);
		add(startText[0]);
		add(startText[1]);
		for(JButton p : numPlayers){
			add(p);
			p.addActionListener(listen);
			p.setPreferredSize(testSize);
			p.setForeground(Color.GREEN);
			p.setBackground(Color.DARK_GRAY);
		}
		add(startText[2]);
		for(JButton d : difficultySelect){
			add(d);
			d.addActionListener(listen);
			d.setPreferredSize(testSize);
			d.setForeground(Color.GREEN);
			d.setBackground(Color.DARK_GRAY);
		}
		add(startText[3]);
		for(JButton s : startOptions){
			add(s);
			s.addActionListener(listen);
			s.setPreferredSize(size2);
			s.setForeground(Color.GREEN);
			s.setBackground(Color.DARK_GRAY);

		}
		for(int i = 0; i < startText.length; ++ i){
			startText[i].setForeground(Color.GREEN);
			startText[i].setFont(startText[i].getFont().deriveFont(startTextSize[i]));
		}
	}
	public void start(String[] args){
		
	}

	private class ButtonListener implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {

			FileWriter outputStream = null;
			try {
				if(e.getSource() == numPlayers[0]){

					players = "1";
					numPlayers[0].setBackground(Color.RED);

				}
				else if(e.getSource() == numPlayers[1]){

					players = "2";
					numPlayers[1].setBackground(Color.RED);

				}
				 if(e.getSource() == difficultySelect[0]){

					difficulty = "Easy";

				}
				else if(e.getSource() == difficultySelect[1]){

					difficulty = "Regular";

				}
				else if(e.getSource() == difficultySelect[2]){

					difficulty = "Hard";

				}
				else if(e.getSource() == difficultySelect[3]){

					difficulty = "Insanity";

				}
				 if(e.getSource() == startOptions[0]){
					 GreenfootScenarioMain.initProperties();
						GreenfootScenarioMain.main(start);
					started = true;
				 }


				outputStream = new FileWriter("Setup.txt");




			} catch (IOException i) {

				i.printStackTrace();
			}

			finally{
				if(outputStream != null){
					try {
						outputStream.write(players + "\015");
						outputStream.write(difficulty + "\015");
						
						outputStream.close();
						
					} catch (IOException i) {

						i.printStackTrace();
					}
				}
			}
		}
	}
}
