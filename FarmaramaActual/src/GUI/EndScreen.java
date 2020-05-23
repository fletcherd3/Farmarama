package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import classes.EnviromentGUI;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class EndScreen {

	private JFrame endScreenWindow;
	private EnviromentGUI game;
	

	
	/** 
	 * Constructor for the endScreen that assigns the incGame to the local game and
	 * initializes the endScreenWindow
	 * @param incGame - EnviromentGUI
	 */
	public EndScreen(EnviromentGUI incGame) {
		game = incGame;
		initialize();
		endScreenWindow.setVisible(true);
	}
	
	
	/**
	 * Disposes of the endScreenWindow
	 */
	public void closeWindow() {
		endScreenWindow.dispose();
	}
	
	
	/**
	 * Calls the closeEndScreen method of the game with this screen as a parameter
	 */
	public void finishedWindow() {
		game.closeEndScreen(this);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		endScreenWindow = new JFrame();
		endScreenWindow.setResizable(false);
		endScreenWindow.setTitle("Farmarama");
		endScreenWindow.setBounds(100, 100, 698, 478);
		endScreenWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		endScreenWindow.getContentPane().setLayout(null);
		endScreenWindow.setLocationRelativeTo(null);
		
		JLabel lblTimesUp = new JLabel("Times Up!");
		lblTimesUp.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblTimesUp.setBounds(202, 66, 276, 87);
		endScreenWindow.getContentPane().add(lblTimesUp);
		
		JPanel scorePanel = new JPanel();
		scorePanel.setBackground(Color.WHITE);
		scorePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		scorePanel.setBounds(145, 216, 390, 128);
		endScreenWindow.getContentPane().add(scorePanel);
		scorePanel.setLayout(null);
		
		Double totalProfit = game.getGameFarm().getBalance() - game.getGameFarm().getStartingBalance();
		DecimalFormat df = new DecimalFormat("#####.##");
		JLabel lblFarmDescription = new JLabel(game.getGameFarm().getName() +" made $ " + df.format(totalProfit) + " in " + game.getTotalDays() + " days");
		lblFarmDescription.setBounds(57, 27, 285, 43);
		lblFarmDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		scorePanel.add(lblFarmDescription);
		
		Integer totalDays = game.getTotalDays();
		JLabel lblScore = new JLabel("Score: " + Math.round(totalProfit/totalDays)); // Determining total score
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblScore.setBounds(146, 74, 98, 43);
		scorePanel.add(lblScore);
		
		JButton btnExitGame = new JButton("Exit Game");
		btnExitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnExitGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExitGame.setBounds(538, 374, 119, 40);
		endScreenWindow.getContentPane().add(btnExitGame);
	}
	
	
	/**
	 * Create the application.
	 */
	public EndScreen() {
		initialize();
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndScreen window = new EndScreen();
					window.endScreenWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
