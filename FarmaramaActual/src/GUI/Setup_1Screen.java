package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSlider;
import javax.swing.JTextField;

import classes.EnviromentGUI;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Setup_1Screen {

	private JFrame setupScreen1Window;
	private JTextField farmerNameField;
	private JTextField farmerAgeField;
	private JTextField farmNameField;
	private EnviromentGUI game;

	
	/**
	 * Constructor for the Setup_1Screen that assigns incGame to the local game and
	 * initializes the Window
	 * @param incGame - EnviromentGUI
	 */
	public Setup_1Screen(EnviromentGUI incGame) {
		game = incGame;
		initialize();
		setupScreen1Window.setVisible(true);
	}
	
	
	/**
	 * Disposes of the current setupScreen window
	 */
	public void closeWindow() {
		setupScreen1Window.dispose();
	}
	
	/**
	 * Calls the closeSetup_1Screen method on the game
	 */
	public void finishedWindow() {
		game.closeSetup_1Screen(this);
	}
	
	
	
	/**
	 * Create the application.
	 */
	public Setup_1Screen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setupScreen1Window = new JFrame();
		setupScreen1Window.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		setupScreen1Window.setResizable(false);
		setupScreen1Window.setTitle("Farmarama");
		setupScreen1Window.setBounds(100, 100, 698, 478);
		setupScreen1Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupScreen1Window.getContentPane().setLayout(null);
		setupScreen1Window.setLocationRelativeTo(null);
		
		JLabel lblDaysQuestion = new JLabel("How many days would you like to play?");
		lblDaysQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDaysQuestion.setBounds(182, 29, 315, 29);
		setupScreen1Window.getContentPane().add(lblDaysQuestion);
		
		final int MIN_DAYS = 5;
		final int MAX_DAYS = 10;
		final int INT_DAYS = 5;
		JSlider daysSlider = new JSlider(JSlider.HORIZONTAL, MIN_DAYS, MAX_DAYS, INT_DAYS);
		daysSlider.setBounds(185, 69, 310, 60);
		daysSlider.setSnapToTicks(true);
		daysSlider.setMajorTickSpacing(5);
		daysSlider.setMinorTickSpacing(1);
		daysSlider.setPaintTicks(true);
		daysSlider.setPaintLabels(true);
		setupScreen1Window.getContentPane().add(daysSlider);
		
		JLabel lblFarmerName = new JLabel("Farmer Name:");
		lblFarmerName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFarmerName.setBounds(121, 152, 113, 14);
		setupScreen1Window.getContentPane().add(lblFarmerName);
		
		JLabel lblFarmersAge = new JLabel("Farmers age:");
		lblFarmersAge.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFarmersAge.setBounds(450, 146, 117, 27);
		setupScreen1Window.getContentPane().add(lblFarmersAge);
		
		JLabel lblFarmName = new JLabel("Farm name:");
		lblFarmName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFarmName.setBounds(293, 233, 94, 20);
		setupScreen1Window.getContentPane().add(lblFarmName);
		
		farmerNameField = new JTextField();
		farmerNameField.setBounds(52, 175, 246, 38);
		setupScreen1Window.getContentPane().add(farmerNameField);
		farmerNameField.setColumns(10);
		
		JLabel farmerNameErrorField = new JLabel("");
		farmerNameErrorField.setForeground(Color.RED);
		farmerNameErrorField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		farmerNameErrorField.setBounds(52, 217, 222, 47);
		setupScreen1Window.getContentPane().add(farmerNameErrorField);
		
		JLabel farmerAgeErrorField = new JLabel("");
		farmerAgeErrorField.setForeground(Color.RED);
		farmerAgeErrorField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		farmerAgeErrorField.setBounds(450, 217, 212, 23);
		setupScreen1Window.getContentPane().add(farmerAgeErrorField);
		
		JLabel farmNameErrorField = new JLabel("");
		farmNameErrorField.setForeground(Color.RED);
		farmNameErrorField.setFont(new Font("Tahoma", Font.PLAIN, 10));
		farmNameErrorField.setBounds(217, 311, 246, 45);
		setupScreen1Window.getContentPane().add(farmNameErrorField);
		
		JButton btnEnterFarm = new JButton("Enter Farm");
		btnEnterFarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // The code below determines whether or not to accept the users input and pass it onto the environment
				Boolean canProgress = true;
				if (!game.setupFarmName(farmNameField.getText())) {
					canProgress = false;
					farmNameErrorField.setText("Name must be between 3 and 15 characters");
				} else {
					farmNameErrorField.setText("");
				}
				if (!game.setupFarmerName(farmerNameField.getText())) {
					canProgress = false;
					farmerNameErrorField.setText("<html>Oops, that name was invalid! </br>Try again without numbers, special characters and keep it between 3-15 characters.</html>");
				} else {
					farmerNameErrorField.setText("");
				}
				if (!game.setupFarmerAge(farmerAgeField.getText())) {
					canProgress = false;
					farmerAgeErrorField.setText("This is an invalid age");
				} else {
					farmerAgeErrorField.setText("");
				}
				Integer days = daysSlider.getValue();
				game.setDaysLeft(days);
				game.setTotalDays(days);
				if (canProgress) {
					finishedWindow();
				}
				
			}
		});
		btnEnterFarm.setBounds(277, 360, 126, 52);
		setupScreen1Window.getContentPane().add(btnEnterFarm);
		
		farmerAgeField = new JTextField();
		farmerAgeField.setColumns(10);
		farmerAgeField.setBounds(380, 175, 246, 38);
		setupScreen1Window.getContentPane().add(farmerAgeField);
		
		farmNameField = new JTextField();
		farmNameField.setColumns(10);
		farmNameField.setBounds(217, 266, 246, 38);
		setupScreen1Window.getContentPane().add(farmNameField);
		
		
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Setup_1Screen window = new Setup_1Screen();
					window.setupScreen1Window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
