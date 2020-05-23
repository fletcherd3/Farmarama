package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;

import classes.EnviromentGUI;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IntroScreen {


	private EnviromentGUI game;
	private JFrame introWindow;


	
	/**
	 * IntroScren constructor that initializes the introWindow
	 * @param incGame - EnviromentGUI
	 */
	public IntroScreen(EnviromentGUI incGame) {
		game = incGame;
		initialize();
		introWindow.setVisible(true);
	}
	
	
	/**
	 * Closes disposes of the into window
	 */
	public void closeWindow() {
		introWindow.dispose();
	}
	
	/**
	 * Calls the closeIntroWindow method of the current Game
	 */
	public void finishedWindow() {
		game.closeIntroWindow(this);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		introWindow = new JFrame();
		introWindow.setResizable(false);
		introWindow.setTitle("Farmarama");
		introWindow.setBounds(100, 100, 698, 478);
		introWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		introWindow.getContentPane().setLayout(null);
		introWindow.setLocationRelativeTo(null);
		
		JLabel Title = new JLabel("Farmarama");
		Title.setFont(new Font("Tahoma", Font.PLAIN, 60));
		Title.setBounds(188, 77, 303, 106);
		introWindow.getContentPane().add(Title);
		
		JLabel Description = new JLabel("<html>A SENG201 Project created by:<br/>Fletcher Dick and Jack Ryan</html>");
		Description.setFont(new Font("Tahoma", Font.PLAIN, 20));
		Description.setBounds(202, 160, 275, 100);
		introWindow.getContentPane().add(Description);
		
		JButton StartButton = new JButton("Start");
		StartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		StartButton.setBounds(277, 300, 125, 39);
		introWindow.getContentPane().add(StartButton);
	}

}
