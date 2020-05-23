package GUI;

import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import classes.Crop;
import classes.EnviromentGUI;
import classes.Item;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Panel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainScreen {

	private JFrame mainWindow;
	private EnviromentGUI game;
	private JLabel lblDaysLeft;
	private JLabel lblActions;
	private JLabel lblBalance;	
	private JButton btnActHarvest;

	

	/**
	 * Constructor for the mainScreen that assigns game to the incGame and initializes the window
	 * @param incGame - EnviromentGUI
	 */
	public MainScreen(EnviromentGUI incGame) {
		game = incGame;
		initialize();
		updateDynamic();
		mainWindow.setVisible(true);
	}
	
	/**
	 * Create the application.
	 */
	public MainScreen() {
		initialize();
		updateDynamic();
	}
	
	/**
	 * Disposes of the mainWindow
	 */
	public void closeWindow() {
		mainWindow.dispose();
	}
	
	/**
	 * Calls the closeMainScreen on the game with a given destination
	 * @param destination - String
	 */
	public void finishedWindow(String destination) {
		game.closeMainScreen(this, destination);
	}
	

	
	/**
	 * Updates the dynamic contents of the mainWindow.
	 */
	private void updateDynamic() {
		lblDaysLeft.setText(game.getDaysLeft().toString() + " Days Left");
		lblActions.setText(game.getActionsLeft().toString() + " Actions Remaining");
		lblBalance.setText("$" + 
		String.format("%,.2f", game.getGameFarm().getBalance()));
		String harvestCountLbl = "";
		int harvestAmount = game.getGameFarm().getCrops().harvestAmount();
		if (harvestAmount > 0) {
			harvestCountLbl = " (" + String.valueOf(harvestAmount) + ")";
			btnActHarvest.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
			btnActHarvest.setText("Harvest Grown Crops" + harvestCountLbl);
		} else {
			btnActHarvest.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
			btnActHarvest.setText("Harvest Grown Crops");
		}
		
	}
	

	/**
	 * Initialize the contents of the mainWindow.
	 */
	private void initialize() {
		mainWindow = new JFrame();
		mainWindow.setResizable(false);
		mainWindow.setTitle("Farmarama");
		mainWindow.setBounds(100, 100, 698, 478);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(null);
		mainWindow.setLocationRelativeTo(null);
		
		JButton btnMainView = new JButton("View Crops and Animals");
		btnMainView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow("ViewCropAnimalScreen");
			}
		});
		btnMainView.setBounds(24, 82, 480, 53);
		mainWindow.getContentPane().add(btnMainView);
		
		JButton btnMainStore = new JButton("Go to the General Store");
		btnMainStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow("StoreScreen");
			}
		});
		btnMainStore.setBounds(24, 210, 480, 53);
		mainWindow.getContentPane().add(btnMainStore);
		
		JButton btnMainNxtDay = new JButton("Move on to the next day");
		btnMainNxtDay.addActionListener(new ActionListener() { // The 'game loop' of the game that calls the required methods when moving onto the next day
			public void actionPerformed(ActionEvent e) {
				game.setDaysLeft(game.getDaysLeft() - 1);
				game.getGameFarm().growCrops();
				game.getGameFarm().getDailyBonus();
				game.getGameFarm().decreaseAnimalWellbeing();
				if (game.getDaysLeft() >= 0) {
					game.setActionsLeft(2);
					updateDynamic();
				} else {
					finishedWindow("EndScreen");
				}
			}
		});
		btnMainNxtDay.setBounds(24, 330, 480, 53);
		mainWindow.getContentPane().add(btnMainNxtDay);
		
		lblDaysLeft = new JLabel(game.getDaysLeft().toString() + " Days Left");
		lblDaysLeft.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblDaysLeft.setBounds(6, 6, 115, 16);
		mainWindow.getContentPane().add(lblDaysLeft);
		lblDaysLeft.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysLeft.setForeground(Color.WHITE);
		
		lblActions = new JLabel(game.getActionsLeft().toString() + " Actions Remaining");
		lblActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblActions.setForeground(Color.WHITE);
		lblActions.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblActions.setBounds(264, 6, 169, 23);
		mainWindow.getContentPane().add(lblActions);
		
		lblBalance = new JLabel("$" + 
		String.format("%,.2f", game.getGameFarm().getBalance()));
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalance.setForeground(new Color(255, 215, 0));
		lblBalance.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblBalance.setBounds(575, 6, 105, 16);
		mainWindow.getContentPane().add(lblBalance);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 698, 29);
		mainWindow.getContentPane().add(panel);
		
		JLabel lblCostAction = new JLabel("Each Cost 1 Action");
		lblCostAction.setHorizontalAlignment(SwingConstants.LEFT);
		lblCostAction.setForeground(new Color(0, 0, 0));
		lblCostAction.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblCostAction.setBounds(548, 43, 124, 16);
		mainWindow.getContentPane().add(lblCostAction);
		
		JButton btnActTendCrop = new JButton("Tend to Crops");
		btnActTendCrop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Code takes input from user through the GUI and based on that input, responds with new prompts. If all inputs and preconditions are met
				if (game.getActionsLeft() > 0) {		// the selected crops will be tended to with the selected item.
					ArrayList<String> possibleCrops = game.getGameFarm().getCrops().getUniqueCropStrings();
					ArrayList<String> possibleItems = game.getAvalibleItems("Crop");
					Object[] crops = possibleCrops.toArray();
					Object[] items = possibleItems.toArray();
					if (!(possibleCrops.size() == 0 || possibleItems.size() == 0)) {
						Object selectedCropObject = JOptionPane.showInputDialog(mainWindow, "Select Crop", "Tend to Crops", JOptionPane.INFORMATION_MESSAGE, null, crops, crops[0]);
						if (selectedCropObject != null) {
							Object selectedItemObject = JOptionPane.showInputDialog(mainWindow, "Select Item", "Tend to Crops", JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);
							if (selectedItemObject != null) {
								String selectedCropString = selectedCropObject.toString();
								String selectedItemString = selectedItemObject.toString();
								Item selectedItem;
								if (selectedItemString == "Water" ) {
									selectedItem = game.getGameFarm().createItem("Water");
								} else {
									selectedItem = game.getGameFarm().getItems().getItemFromString(selectedItemString);
								}
								game.tendToCrops(selectedItem, selectedCropString);
								game.setActionsLeft(game.getActionsLeft() - 1);
								JOptionPane.showMessageDialog(mainWindow, "Sucessfully tended to your " + selectedCropString + " with " + selectedItemString);
								updateDynamic();	
							}
						}
						
					} else {
						JOptionPane.showMessageDialog(
								mainWindow,
								"You need to have some crops and some items to tend to your crops!",
								"Tend to Crops",
								JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(mainWindow, "No actions remaining");
				}
				
				
				
			}
		});
		btnActTendCrop.setBounds(530, 71, 156, 74);
		mainWindow.getContentPane().add(btnActTendCrop);
		
		btnActHarvest = new JButton("Harvest Grown Crops");
		btnActHarvest.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnActHarvest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Harvest Grown Crops or responds with appropriate prompts to the user.
				if (game.getActionsLeft() > 0) {
					if (game.getGameFarm().getCrops().harvestAmount() == 0) {
						JOptionPane.showMessageDialog(mainWindow, "Oops, you have no crops ready to harvest");
					} else {
						game.getGameFarm().setBalance(
								game.getGameFarm().getBalance() + 
								game.getGameFarm().getCrops().harvestGrownCrops()
								);
						game.setActionsLeft(game.getActionsLeft() - 1);
						JOptionPane.showMessageDialog(mainWindow, "Successfully harvested grown crops");
						updateDynamic();
					}
				} else {
					JOptionPane.showMessageDialog(mainWindow, "No actions remainaing");
				}
				
			}
		});
		btnActHarvest.setBounds(530, 143, 156, 74);
		mainWindow.getContentPane().add(btnActHarvest);
		
		JButton btnActFeed = new JButton("Feed Animals");
		btnActFeed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // Feeds the animals if the preconditions for feeding animals are met, otherwise prompting user on why they weren't fed.
				if (game.getActionsLeft() > 0) {
					ArrayList<String> possibleAnimals = game.getGameFarm().getAnimals().getUniqueAnimalStrings();
					ArrayList<String> possibleItems = game.getAvalibleItems("Food");
					Object[] animals = possibleAnimals.toArray();
					Object[] items = possibleItems.toArray();
					if (!(possibleAnimals.size() == 0 || possibleItems.size() == 0)) {
						Object selectedAnimalObject = JOptionPane.showInputDialog(mainWindow, "Select Animal", "Feed Animals", JOptionPane.INFORMATION_MESSAGE, null, animals, animals[0]);
						if (selectedAnimalObject != null) {
							Object selectedItemObject = JOptionPane.showInputDialog(mainWindow, "Select Item", "Tend to Crops", JOptionPane.INFORMATION_MESSAGE, null, items, items[0]);
							if (selectedItemObject != null) {
								String selectedAnimalString = selectedAnimalObject.toString();
								String selectedItemString = selectedItemObject.toString();
								Item selectedItem;
								if (selectedItemString == "Water" ) {
									selectedItem = game.getGameFarm().createItem("Water");
								} else {
									selectedItem = game.getGameFarm().getItems().getItemFromString(selectedItemString);
								}
								game.feedAnimals(selectedItem, selectedAnimalString);
								game.setActionsLeft(game.getActionsLeft() - 1);
								JOptionPane.showMessageDialog(mainWindow, "Sucessfully fed your " + selectedAnimalString + "s with " + selectedItemString);
								updateDynamic();	
							}
						}
						
					} else {
						JOptionPane.showMessageDialog(
								mainWindow,
								"You need to have some animals and some items in order to feed some animals!",
								"Feed Animals",
								JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(mainWindow, "No actions remainaing");
				}
				
				
				
			}
		});
		btnActFeed.setBounds(531, 215, 156, 74);
		mainWindow.getContentPane().add(btnActFeed);
		
		JButton btnActPlay = new JButton("Play with Animals");
		btnActPlay.addActionListener(new ActionListener() { // Plays with animals if the user has animals and correctly makes a selection, otherwise prompting them with why they weren't played with
			public void actionPerformed(ActionEvent e) {
				if (game.getActionsLeft() > 0) {
					ArrayList<String> possibleAnimals = game.getGameFarm().getAnimals().getUniqueAnimalStrings();
					if (possibleAnimals.size() != 0) {
						Object[] animals = possibleAnimals.toArray();
						Object selectedAnimalObject = JOptionPane.showInputDialog(mainWindow,  "Select Animal:", "Play with Animals", JOptionPane.INFORMATION_MESSAGE, null, animals, animals[0]);
						if (selectedAnimalObject != null) {
							String selectedAnimalString = selectedAnimalObject.toString();
							game.playWithAnimals(selectedAnimalString);
							game.setActionsLeft(game.getActionsLeft() - 1);
							JOptionPane.showMessageDialog(mainWindow, "Sucessfully played with your " + selectedAnimalString + "s.");
							updateDynamic();
						}
					} else {
						JOptionPane.showMessageDialog(mainWindow,  "No animals to play with");
					}
				} else {
					JOptionPane.showMessageDialog(mainWindow, "No actions remainaing");
				}
					
					
			}
			
		});
		btnActPlay.setBounds(531, 287, 156, 74);
		mainWindow.getContentPane().add(btnActPlay);
		
		JButton btnActTendFarm = new JButton("Tend to Farm Land");
		btnActTendFarm.addActionListener(new ActionListener() { // Tends to farm land if the preconditions to do so are there, and appropriately prompts the user with the result
			public void actionPerformed(ActionEvent e) {
				if (game.getActionsLeft() > 0) {
					game.getGameFarm().tendToFarm();
					game.setActionsLeft(game.getActionsLeft() - 1);
					JOptionPane.showMessageDialog(mainWindow, "Successfully tended to the farmland");
					updateDynamic();
				} else {
					JOptionPane.showMessageDialog(mainWindow, "No actions remainaing");
				}
			}
		});
		btnActTendFarm.setBounds(531, 359, 156, 74);
		mainWindow.getContentPane().add(btnActTendFarm);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(222, 184, 135));
		panel_1.setBounds(531, 29, 167, 427);
		mainWindow.getContentPane().add(panel_1);
		

	}
	

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen window = new MainScreen();
					window.mainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
