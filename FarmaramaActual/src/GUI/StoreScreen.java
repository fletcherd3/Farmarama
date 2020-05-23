package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import classes.Animal;
import classes.Crop;
import classes.EnviromentGUI;
import classes.Item;

import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JProgressBar;
import java.awt.Component;

public class StoreScreen {

	private JFrame storeWindow;
	private EnviromentGUI game;
	private JLabel lblDaysLeft;
	private JLabel lblActions;
	private JLabel lblBalance;
	private JTable CropTable;
	private JTable ItemTable;
	private JTable AnimalTable;	
	private DefaultTableModel cropModel;
	private DefaultTableModel itemModel;
	private DefaultTableModel animalModel;
	private ArrayList<Crop> CropList;
	private ArrayList<Animal> AnimalList;
	private ArrayList<Item> ItemList;
	private JProgressBar cropLimit;
	

	
	
	/**
	 * Constructor for the StoreScreen which assigns the incGame to the game and initializes the screen.
	 * @param incGame - EnviromentGUI
	 */
	public StoreScreen(EnviromentGUI incGame) {
		game = incGame;
		initialize();
		storeWindow.setVisible(true);
	}

	/**
	 * Disposes of the storeWindow
	 */
	public void closeWindow() {
		storeWindow.dispose();
	}
	
	
	/**
	 * Calls the closeStoreScreen method for the game
	 */
	public void finishedWindow() {
		game.closeStoreScreen(this);
	}
	
	/**
	 * Updates the dynamic contents of the mainWindow.
	 */
	private void updateDynamic() {
		lblDaysLeft.setText(game.getDaysLeft().toString() + " Days Left");
		lblActions.setText(game.getActionsLeft().toString() + " Actions Remaining");
		lblBalance.setText("$" + 
		String.format("%,.2f", game.getGameFarm().getBalance()));
		int cropAmount = game.getGameFarm().getCrops().getCropList().size();
		cropLimit.setValue(cropAmount);
		
		refreshTableModels();
		CropTable.setModel(cropModel);
		ItemTable.setModel(itemModel);
		AnimalTable.setModel(animalModel);
        CropTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        ItemTable.getColumnModel().getColumn(4).setPreferredWidth(10);
	}
	
	
	/**
	 * Refreshes the contents of the tables in the store screen
	 */
	private void refreshTableModels() {

        // Column Names for Crop table
        String[] cropColumnNames = {"Name", "Harvest Time", "Sell Price", "Quantity", "Price"};
        
        cropModel = new DefaultTableModel(new Object[0][0], cropColumnNames);
        for (Crop crop : CropList) {
            Object[] o = new Object[5];
            o[0] = crop.getName();
            o[1] = crop.getHarvestTime().toString() + " days";
            o[2] = "$" + String.format("%,.2f", crop.getSellPrice());
            
            // Get the index of the item in farmers inventory
            int indexValue = game.getGameFarm().getCrops().getUniqueCropStrings().indexOf(crop.getName());
            // If the farmer doesn't have any of the item
            if (indexValue == -1) {
            	o[3] = "0";
            } else {
            	o[3] = game.getGameFarm().getCrops().getEachCropCount().get(indexValue);
            }
            
            o[4] = "$" + String.format("%,.2f", crop.getPurchasePrice());
            cropModel.addRow(o);
        }
        

        // Column Names 
        String[] itemcolumnNames = { "Name", "Harvest Time Dec.", "Animal Health Inc", "Quantity", "Price"};
        
        itemModel = new DefaultTableModel(new Object[0][0], itemcolumnNames);
        for (Item item : ItemList) {
            Object[] o = new Object[5];
            o[0] = item.getName();
            if (item.getHarvestDecValue() == null) {
            	o[1] = "N/A";
            } else {
            	o[1] = item.getHarvestDecValue().toString() + " days";
            }
            
            if (item.getHealthValue() == null) {
            	o[2] = "N/A";
            } else {
            	o[2] = item.getHealthValue().toString() + " hp";
            }

            // Get the index of the item in farmers inventory
            int indexValue = game.getGameFarm().getItems().getUniqueItemStrings().indexOf(item.getName());
            // If the farmer doesn't have any of the item
            if (indexValue == -1) {
            	o[3] = "0";
            } else {
            	o[3] = game.getGameFarm().getItems().getEachItemCounts().get(indexValue);
            }
            
            o[4] = "$" + String.format("%,.2f", item.getPurchasePrice());
            itemModel.addRow(o);
        }
        
        
        // Column Names 
        String[] animalColumnNames = { "Name", "Tend Return", "Quantity", "Price"};
        
        animalModel = new DefaultTableModel(new Object[0][0], animalColumnNames);
        for (Animal animal : AnimalList) {
            Object[] o = new Object[4];
            o[0] = animal.getName();
            o[1] = "$" + String.format("%,.2f", animal.getTendReturn());
            
            // Get the index of the item in farmers inventory
            int indexValue = game.getGameFarm().getAnimals().getUniqueAnimalStrings().indexOf(animal.getName());
            // If the farmer doesn't have any of the item
            if (indexValue == -1) {
            	o[2] = "0";
            } else {
            	o[2] = game.getGameFarm().getAnimals().getEachAnimalCount().get(indexValue);
            }
            
            o[3] = "$" + String.format("%,.2f", animal.getPurchasePrice());

            animalModel.addRow(o);
        }
        
    	
	}
	

	/**
	 * Initialize the contents of the storeWindow.
	 */
	private void initialize() {
		storeWindow = new JFrame();
		storeWindow.setResizable(false);
		storeWindow.setTitle("Farmarama");
		storeWindow.setBounds(100, 100, 698, 478);
		storeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		storeWindow.getContentPane().setLayout(null);
		storeWindow.setLocationRelativeTo(null);
		
		lblDaysLeft = new JLabel(game.getDaysLeft().toString() + " Days Left");
		lblDaysLeft.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblDaysLeft.setBounds(6, 6, 115, 16);
		storeWindow.getContentPane().add(lblDaysLeft);
		lblDaysLeft.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysLeft.setForeground(Color.WHITE);
		
		lblActions = new JLabel(game.getActionsLeft().toString() + " Actions Remaining");
		lblActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblActions.setForeground(Color.WHITE);
		lblActions.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblActions.setBounds(264, 6, 169, 23);
		storeWindow.getContentPane().add(lblActions);
		
		lblBalance = new JLabel("$" + 
		String.format("%,.2f", game.getGameFarm().getBalance()));
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalance.setForeground(new Color(255, 215, 0));
		lblBalance.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblBalance.setBounds(575, 6, 105, 16);
		storeWindow.getContentPane().add(lblBalance);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 698, 29);
		storeWindow.getContentPane().add(panel);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnBack.setBounds(524, 348, 156, 74);
		storeWindow.getContentPane().add(btnBack);

		
		// Data to be displayed in the JTables
		CropList = new ArrayList<Crop>();
		CropList.add(game.getGameFarm().createCrop("alpine"));
		CropList.add(game.getGameFarm().createCrop("carrot"));
		CropList.add(game.getGameFarm().createCrop("tomato"));
		CropList.add(game.getGameFarm().createCrop("jack"));
		
		AnimalList = new ArrayList<Animal>();
		AnimalList.add(game.getGameFarm().createAnimal("chicken"));
		AnimalList.add(game.getGameFarm().createAnimal("pig"));
		AnimalList.add(game.getGameFarm().createAnimal("cow"));
		
		ItemList = new ArrayList<Item>();
		ItemList.add(game.getGameFarm().createItem("feed"));
		ItemList.add(game.getGameFarm().createItem("mineral"));
		ItemList.add(game.getGameFarm().createItem("magic"));
		ItemList.add(game.getGameFarm().createItem("cake"));
		
		
		refreshTableModels();

        CropTable = new JTable( cropModel );
        CropTable.setBounds(51, 223, 1, 1);
        CropTable.setRowSelectionAllowed(true);
        CropTable.setDefaultEditor(Object.class, null);
        CropTable.getColumnModel().getColumn(0).setPreferredWidth(100);
    	JScrollPane scrollPaneCrop = new JScrollPane(CropTable);
		scrollPaneCrop.setBounds(38, 68, 454, 85);
		storeWindow.getContentPane().add(scrollPaneCrop);
		
		ItemTable = new JTable( itemModel );
        ItemTable.setBounds(51, 223, 1, 1);
        ItemTable.setRowSelectionAllowed(true);
        ItemTable.setDefaultEditor(Object.class, null);
        ItemTable.getColumnModel().getColumn(4).setPreferredWidth(10);
		JScrollPane scrollPaneItem = new JScrollPane(ItemTable);
		scrollPaneItem.setBounds(38, 203, 454, 85);
		storeWindow.getContentPane().add(scrollPaneItem);
		
		AnimalTable = new JTable( animalModel );
        AnimalTable.setBounds(51, 223, 1, 1);
        AnimalTable.setRowSelectionAllowed(true);
        AnimalTable.setDefaultEditor(Object.class, null);
		JScrollPane scrollPaneAnimal = new JScrollPane(AnimalTable);
		scrollPaneAnimal.setBounds(38, 337, 454, 85);
		storeWindow.getContentPane().add(scrollPaneAnimal);
		
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() { // Based on the users actions, either makes purchase or prompts user with appropriate dialog
			public void actionPerformed(ActionEvent e) {
				int ItemOption = ItemTable.getSelectedRow();
				int CropOption = CropTable.getSelectedRow();
				int AnimalOption = AnimalTable.getSelectedRow();
				String choice;
				
				if (ItemOption != -1) {  // If user has selected an item
					 // If user has room for the crop
						switch (ItemOption) {
						case 0:
							choice = "feed";
							break;
						case 1:
							choice = "mineral";
							break;
						case 2:
							choice = "magic";
							break;
						case 3:
							choice = "cake";
							break;
						default:
							throw new IllegalStateException("Unexpected value: " + ItemOption);
						}
						if (!game.makePurchase("item", choice)) {

							JOptionPane.showMessageDialog(
									storeWindow,
									"Sorry, you don't have enough money for this item",
									"Insufficient Funds",
									JOptionPane.PLAIN_MESSAGE);

						}
					
				} else if (CropOption != -1) {  // If user has selected a crop
					if (game.getGameFarm().getCropLimit() > game.getGameFarm().getCrops().getCropList().size()) {
						switch (CropOption) {
							case 0:
								choice = "alpine";
								break;
							case 1:
								choice = "carrot";
								break;
							case 2:
								choice = "tomato";
								break;
							case 3:
								choice = "jack";
								break;
							default:
								throw new IllegalStateException("Unexpected value: " + CropOption);
						}
						if (!game.makePurchase("crop", choice)) {
	
							JOptionPane.showMessageDialog(
									storeWindow,
									"Sorry, you don't have enough money for this crop",
									"Insufficient Funds",
									JOptionPane.PLAIN_MESSAGE);
	
						}
					} else {
						JOptionPane.showMessageDialog(storeWindow, "You don't have room for any more crops!");
	
					}
					
				} else if (AnimalOption != -1) {  // If user has selected an animal
					
					switch (AnimalOption) {
					case 0:
						choice = "chicken";
						break;
					case 1:
						choice = "pig";
						break;
					case 2:
						choice = "cow";
						break;
					default:
						throw new IllegalStateException("Unexpected value: " + AnimalOption);
				}
				if (!game.makePurchase("animal", choice)) {

					JOptionPane.showMessageDialog(
							storeWindow,
							"Sorry, you don't have enough money for this item",
							"Insufficient Funds",
							JOptionPane.PLAIN_MESSAGE);

				}
				
				}
				// Update the balance label
				updateDynamic();
			}
		});
		btnPurchase.setBounds(524, 262, 156, 74);
		storeWindow.getContentPane().add(btnPurchase);
		
		JLabel lblNewLabel = new JLabel("Crops");
		lblNewLabel.setBounds(39, 46, 61, 16);
		storeWindow.getContentPane().add(lblNewLabel);
		
		JLabel lblItems = new JLabel("Items");
		lblItems.setBounds(39, 180, 61, 16);
		storeWindow.getContentPane().add(lblItems);
		
		JLabel lblAnimals = new JLabel("Animals");
		lblAnimals.setBounds(38, 312, 61, 16);
		storeWindow.getContentPane().add(lblAnimals);
		
		cropLimit = new JProgressBar();
		int cropAmount = game.getGameFarm().getCrops().getCropList().size();
		cropLimit.setMaximum(game.getGameFarm().getCropLimit());
		cropLimit.setValue(cropAmount);
		cropLimit.setBounds(524, 108, 156, 20);
		storeWindow.getContentPane().add(cropLimit);
		
		JLabel lblCropLimit = new JLabel("Crop Limit");
		lblCropLimit.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblCropLimit.setBounds(570, 92, 83, 16);
		storeWindow.getContentPane().add(lblCropLimit);
		
		AnimalTable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		ItemTable.removeRowSelectionInterval(0, 3);
        		CropTable.removeRowSelectionInterval(0, 3);
        	}
        });
		
		CropTable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		ItemTable.removeRowSelectionInterval(0, 3);
        		AnimalTable.removeRowSelectionInterval(0, 2);
        	}
        });
		
		ItemTable.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		CropTable.removeRowSelectionInterval(0, 3);
        		AnimalTable.removeRowSelectionInterval(0, 2);
        	}
        });

	}
	
	
	/**
	 * Create the application.
	 */
	public StoreScreen() {
		initialize();
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StoreScreen window = new StoreScreen();
					window.storeWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
