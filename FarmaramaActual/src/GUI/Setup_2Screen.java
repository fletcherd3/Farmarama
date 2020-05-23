package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import classes.EnviromentGUI;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Setup_2Screen {

	private JFrame setupScreen2Window;
	private EnviromentGUI game;

	

	/**
	 * Initialize the application.
	 */
	public Setup_2Screen() {
		initialize();
		
	}
	
	/**
	 * Constructor for the setup_2Screen that assigns that incGame to the local game 
	 * and initializes the window
	 * @param incGame - EnviromentGUI
	 */
	public Setup_2Screen(EnviromentGUI incGame) {
		game = incGame;
		initialize();
		setupScreen2Window.setVisible(true);
	}
	
	
	/**
	 * Disposes of the setupScreen2Window
	 */
	public void closeWindow() {
		setupScreen2Window.dispose();
	}
	
	
	/**
	 * Calls the closeSetup_2Screen for this game
	 */
	public void finishedWindow() {
		game.closeSetup_2Screen(this);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		
		setupScreen2Window = new JFrame();
		setupScreen2Window.setResizable(false);
		setupScreen2Window.setTitle("Farmarama");
		setupScreen2Window.setBounds(100, 100, 698, 478);
		setupScreen2Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupScreen2Window.getContentPane().setLayout(null);
		setupScreen2Window.setLocationRelativeTo(null);
		
		JLabel lblSelectInstruction = new JLabel("Select a Farm type to Begin!");
		lblSelectInstruction.setBounds(209, 30, 261, 35);
		lblSelectInstruction.setFont(new Font("Tahoma", Font.PLAIN, 20));
		setupScreen2Window.getContentPane().add(lblSelectInstruction);
		
		JPanel panelPlain = new JPanel();
		panelPlain.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPlain.setBounds(76, 98, 226, 135);
		panelPlain.setLayout(new BorderLayout());
		setupScreen2Window.getContentPane().add(panelPlain);
		
		JButton btnPlains = new JButton("Select Plains");
		btnPlains.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setupFarmType("Plain");
				finishedWindow();
			}
		});
		panelPlain.add(btnPlains, BorderLayout.SOUTH);
		
		JLabel lblPlainsLabel = new JLabel("Plains");
		lblPlainsLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPlainsLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panelPlain.add(lblPlainsLabel, BorderLayout.NORTH);
		
		JLabel lblPlainsDesc = new JLabel("<html> -Growth rate: <font color='orange'>Average</font> <br/> -Animal Happiness: <font color='orange'>Average</font> <br/>"
				+ " -Starting Balance: <font color='orange'>Average</font> </html>");
		lblPlainsDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlainsDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelPlain.add(lblPlainsDesc, BorderLayout.CENTER);
		
		JPanel panelHot = new JPanel();
		panelHot.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelHot.setBounds(378, 98, 226, 135);
		setupScreen2Window.getContentPane().add(panelHot);
		panelHot.setLayout(new BorderLayout());
		
		JButton btnSelectHot = new JButton("Select Hot");
		btnSelectHot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setupFarmType("Hot");
				finishedWindow();
			}
		});
		panelHot.add(btnSelectHot, BorderLayout.SOUTH);
		
		JLabel lblHotLabel = new JLabel("Hot");
		lblHotLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblHotLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelHot.add(lblHotLabel, BorderLayout.NORTH);
		
		JLabel lblHotDesc = new JLabel("<html> -Growth rate: <font color='green'>High</font> <br/> -Animal Happiness: <font color='green'>High</font> <br/> "
				+ "-Starting Balance: <font color='red'>Low</font> </html>");
		lblHotDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHotDesc.setHorizontalAlignment(SwingConstants.CENTER);
		panelHot.add(lblHotDesc, BorderLayout.CENTER);
		
		JPanel panelCold = new JPanel();
		panelCold.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCold.setBounds(76, 260, 226, 135);
		setupScreen2Window.getContentPane().add(panelCold);
		panelCold.setLayout(new BorderLayout());
		
		JButton btnSelectCold = new JButton("Select Cold");
		btnSelectCold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setupFarmType("Cold");
				finishedWindow();
			}
		});
		panelCold.add(btnSelectCold, BorderLayout.SOUTH);
		
		JLabel lblColdLabel = new JLabel("Cold");
		lblColdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblColdLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelCold.add(lblColdLabel, BorderLayout.NORTH);
		
		JLabel lblColdDesc = new JLabel("<html> -Growth rate: <font color='orange'>Average </font><br/> -Animal Happiness: <font color='red'>Low </font><br/> "
				+ "-Starting Balance: <font color='orange'>Average </font></html>");
		lblColdDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblColdDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelCold.add(lblColdDesc, BorderLayout.CENTER);
		
		JPanel panelAlpine = new JPanel();
		panelAlpine.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAlpine.setBounds(378, 260, 226, 135);
		setupScreen2Window.getContentPane().add(panelAlpine);
		panelAlpine.setLayout(new BorderLayout());
		
		JButton btnSelectAlpine = new JButton("Select Alpine");
		btnSelectAlpine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setupFarmType("Alpine");
				finishedWindow();
			}
		});
		panelAlpine.add(btnSelectAlpine, BorderLayout.SOUTH);
		
		JLabel lblAlpineLabel = new JLabel("Alpine");
		lblAlpineLabel.setSize(226, 22);
		lblAlpineLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlpineLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelAlpine.add(lblAlpineLabel, BorderLayout.NORTH);
		
		JLabel lblAlpineDesc = new JLabel("<html> -Growth rate: <font color='green'>High </font><br/> -Animal Happiness: <font color='red'>Low </font><br/> "
				+ "-Starting Balance: <font color='green'>High </font></html>");
		lblAlpineDesc.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlpineDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panelAlpine.add(lblAlpineDesc, BorderLayout.CENTER);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Setup_2Screen window = new Setup_2Screen();
					window.setupScreen2Window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
