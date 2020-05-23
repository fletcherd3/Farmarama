package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import classes.Animal;
import classes.Crop;
import classes.EnviromentGUI;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewCropAnimalScreen {

	
	private EnviromentGUI game;
	private JFrame viewCropAnimalWindow;

	
	/**
	 * Constructor for the ViewCropAnimalCropScreen that assigns the given incGame to the game and
	 * initializes the viewCropAnimalWindow
	 * @param incGame - EnviromentGUI
	 */
	public ViewCropAnimalScreen(EnviromentGUI incGame) {
		game = incGame;
		initialize();
		viewCropAnimalWindow.setVisible(true);
	}
	
	
	/**
	 * Disposes of the viewCropAnimalWindow
	 */
	public void closeWindow() {
		viewCropAnimalWindow.dispose();
	}
	
	
	/**
	 * Calls the closeViewCropAnimalScreen on game 
	 */
	public void finishedWindow() {
		game.closeViewCropAnimalScreen(this);
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		viewCropAnimalWindow = new JFrame();
		viewCropAnimalWindow.setResizable(false);
		viewCropAnimalWindow.setTitle("Farmarama");
		viewCropAnimalWindow.setBounds(100, 100, 698, 478);
		viewCropAnimalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		viewCropAnimalWindow.getContentPane().setLayout(null);
		viewCropAnimalWindow.setLocationRelativeTo(null);
		
		JLabel lblAnimals = new JLabel("Crops");
		lblAnimals.setBounds(40, 238, 61, 16);
		viewCropAnimalWindow.getContentPane().add(lblAnimals);
		
		// Data to be displayed in the JTable 
		Object[] columnNames = { "Name", "Harvest Time", "Sell Price"};
		DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);
        for (Crop crop : game.getGameFarm().getCrops().getCropList()) {
            Object[] o = new Object[3];
            o[0] = crop.getName();
            o[1] = crop.getHarvestTime();
            o[2] = crop.getSellPrice();
            model.addRow(o);
        }
        JTable CropTable = new JTable( model );
        CropTable.setBounds(51, 223, 1, 1);
        CropTable.setRowSelectionAllowed(true);
        CropTable.setDefaultEditor(Object.class, null);
		JScrollPane scrollPaneCrop = new JScrollPane(CropTable);
		scrollPaneCrop.setBounds(39, 262, 454, 148);
		viewCropAnimalWindow.getContentPane().add(scrollPaneCrop);
		
		JLabel lblCrops = new JLabel("Animals");
		lblCrops.setBounds(39, 46, 61, 16);
		viewCropAnimalWindow.getContentPane().add(lblCrops);
		
		// Data to be displayed in the JTable 
		Object[] colNamesAni = { "Name", "Health", "Happiness", "Tend Return"};
		DefaultTableModel modelAnimal = new DefaultTableModel(new Object[0][0], colNamesAni);
        for (Animal animal : game.getGameFarm().getAnimals().getAnimalList()) {
            Object[] o = new Object[4];
            o[0] = animal.getName();
            o[1] = animal.getHealth();
            o[2] = animal.getHappiness();
            o[3] = animal.getTendReturn();
            modelAnimal.addRow(o);
        }
        JTable AnimalTable = new JTable( modelAnimal );
        AnimalTable.setBounds(51, 223, 1, 1);
        AnimalTable.setRowSelectionAllowed(true);
        AnimalTable.setDefaultEditor(Object.class, null);
		JScrollPane scrollPaneAnimal = new JScrollPane(AnimalTable);
		scrollPaneAnimal.setLocation(39, 74);
		scrollPaneAnimal.setSize(454, 148);
		scrollPaneCrop.setBounds(39, 262, 454, 148);
		viewCropAnimalWindow.getContentPane().add(scrollPaneAnimal);
		
		JButton btnBack = new JButton("Go Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finishedWindow();
			}
		});
		btnBack.setBounds(524, 336, 156, 74);
		viewCropAnimalWindow.getContentPane().add(btnBack);

		JLabel lblDaysLeft = new JLabel(game.getDaysLeft().toString() + " Days Left");
		lblDaysLeft.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblDaysLeft.setBounds(6, 6, 115, 16);
		viewCropAnimalWindow.getContentPane().add(lblDaysLeft);
		lblDaysLeft.setHorizontalAlignment(SwingConstants.LEFT);
		lblDaysLeft.setForeground(Color.WHITE);
		
		JLabel lblActions = new JLabel(game.getActionsLeft().toString() + " Actions Remaining");
		lblActions.setHorizontalAlignment(SwingConstants.LEFT);
		lblActions.setForeground(Color.WHITE);
		lblActions.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblActions.setBounds(264, 6, 169, 23);
		viewCropAnimalWindow.getContentPane().add(lblActions);
		
		JLabel lblBalance = new JLabel("$" + 
		String.format("%,.2f", game.getGameFarm().getBalance()));
		lblBalance.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBalance.setForeground(new Color(255, 215, 0));
		lblBalance.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblBalance.setBounds(575, 6, 105, 16);
		viewCropAnimalWindow.getContentPane().add(lblBalance);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		panel.setBounds(0, 0, 698, 29);
		viewCropAnimalWindow.getContentPane().add(panel);
		

		
	}
}
