package classes;

import java.util.*;

public class Farm {

	private String name;
	private String type;
	private double balance;
	private Farmer farmer;
	private Hashtable<String, Double> bonuses;
	private List<Crop> crops;
	private List<Animal> animals;

	public void setName(String newName) {
		// Setter for the farms name
		name = newName;
	}

	public void setType(String newType) {
		// Setter for the farms type
		type = newType;
		if (newType == "Plain") {
			bonuses.put(newType, 2.0);
		} else if (newType == "Cold") {
			bonuses.put(newType, 1.5);
		}

	}

	public Boolean setFarmer(Farmer newFarmer) {
		// Setter for the farms farmer
		farmer = newFarmer;
		return true;
	}

	public String getName() {
		// Getter for the farms name
		return name;
	}
	
	public String getType() {
		// Getter for the farms type
		return type;
	}
	
	public double getBalance() {
		// Getter for the farms balance
		return balance;
	}
	
	public Hashtable<String, Double> getBonuses() {
		// Getter for the farms bonuses
		return bonuses;
	}
	
	public List<Crop> getCrops() {
		// Getter for crops
		return crops;
	}
	
	public List<Animal> getAnimals() {
		// Getter for animals
		return animals;
	}
	
	public Boolean modifyBalance(Double amount) {
		// Modifies the farms balance by the given amount
		Boolean returnValue = false;
		if ((balance + amount) >= 0); {
			balance = balance + amount;
			returnValue = true;
		} 
		return returnValue;
		
	}
}

