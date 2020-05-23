package classes;

import java.util.*;

public class Farm {

	private String name;
	private String type;
	private Double balance = 100000.00;
	private Farmer farmer;
	private Hashtable<String, Double> bonuses=new Hashtable<String, Double>();
	private CropList crops = new CropList();
	private AnimalList animals = new AnimalList();
	private InventoryList inventory = new InventoryList();
	private Integer cropLimit = 10;
	private Double startingBalance;

	/**
	 * Getter for startingBalance
	 * @return - Double
	 */
	public Double getStartingBalance() {
		return startingBalance;
	}

	
	/**
	 * Setter for starting balance
	 * @param startingBalance - Double
	 */
	public void setStartingBalance(Double startingBalance) {
		this.startingBalance = startingBalance;
	}

	
	/**
	 * Setter for the farms name
	 * 
	 * @param newName - name of the farm
	 */
	public void setName(String newName) {
		name = newName;
	}

	
	/**
	 * Setter for the farm type
	 * 
	 * @param newType - type of the farm
	 */
	public void setType(String newType) {
		type = newType;
		if (newType == "Plain") {
			bonuses.put("animalHealth", 8.0);
			bonuses.put("animalHappiness", 7.0);
			bonuses.put("harvestTime", 1.0);
			balance = 100.0;
		} else if (newType == "Cold") {
			bonuses.put("animalHealth", 6.0);
			bonuses.put("animalHappiness", 6.0);
			bonuses.put("harvestTime", 1.0);
			balance = 100.0;
		} else if (newType == "Hot") {
			bonuses.put("animalHealth", 8.0);
			bonuses.put("animalHappiness", 9.0);
			bonuses.put("harvestTime", 2.0);
			balance = 50.0;
		} else if (newType == "Alpine") {
			bonuses.put("animalHealth", 7.0);
			bonuses.put("animalHappiness", 6.0);
			bonuses.put("harvestTime", 2.0);
			balance = 200.0;
		}
		startingBalance = balance;
	}

	
	/**
	 * Setter for the farmer
	 * 
	 * @param newFarmer -- input farmer
	 */
	public void setFarmer(Farmer newFarmer) {
		farmer = newFarmer;
	}
	
	
	/**
	 * Getter for the farm's farmer
	 * @return - Farmer
	 */
	public Farmer getFarmer() {
		return farmer;
	}

	
	/**
	 * Getter for the farms name
	 * 
	 * @return - String, farms name
	 */
	public String getName() {
		return name;
	}

	
	/**
	 * Getter for the farms type
	 * 
	 * @return - String, farm type
	 */
	public String getType() {
		return type;
	}

	
	/**
	 * Setter for balance
	 * @param amount - amount the balance is to be set to
	 */
	public void setBalance(Double amount) {
		balance = amount;
	}
	
	
	/**
	 * Getter for the farms balance
	 * 
	 * @return - Double, farms balance
	 */
	public double getBalance() {
		return balance;
	}

	
	/**
	 * Getter for the farms bonuses
	 * 
	 * @return - Hashtable, of strings mapped to doubles
	 */
	public Hashtable<String, Double> getBonuses() {
		return bonuses;
	}


	/**
	 * Modifies the farm balance by the given amount if able to
	 * 
	 * @param amount - amount to edit balance by
	 * @return - true if able to edit by given amount, false otherwise
	 */
	public Boolean modifyBalance(Double amount) {

		Boolean returnValue = false;
		if ((balance + amount) >= 0) {
			balance = balance + amount;
			returnValue = true;
		}
		return returnValue;

	}

	
	/**
	 * Tends to the farm, increasing the crop limit and animalHappiness modifier in
	 * bonuses
	 */
	public void tendToFarm() {

		cropLimit = cropLimit + 2;
		bonuses.replace("animalHappiness", bonuses.get("animalHappiness") + 1);
	}
	
	
	/**
	 * Setter for the Farms crop limit
	 * @param newLimit - Desired value for the crop limit
	 * @return Boolean, true when crop limit changed, false otherwise
	 */
	public Boolean setCropLimit(Integer newLimit) {
		Boolean returnValue = false;
		if (newLimit >= 0) {
		cropLimit = newLimit;
		returnValue = true;
		}
		return returnValue;
	}
	
	
	/**
	 * Getter for the Farms crop limit
	 * @return - Integer, The current crop limit
	 */
	public Integer getCropLimit() {
		return cropLimit;
	}


	/**
	 * Setter for farms CropList
	 * @param newList CropList
	 */
	public void setCrops(CropList newList) {
		crops = newList;
	}

	
	/**
	 * Getter for farms crops
	 * @return - CropList
	 */
	public CropList getCrops() {
		return crops;
	}


	/**
	 *  Getter for farms animals
	 * @return the animals
	 */
	public AnimalList getAnimals() {
		return animals;
	}

	
	/**
	 *  Getter for farms items
	 * @return the items
	 */
	public InventoryList getItems() {
		return inventory;
	}
	
	
	/**
	 * Setter for inventory
	 * @param newInv
	 */
	public void setInventory(InventoryList newInv) {
		inventory = newInv;
	}

	
	/**
	 * Setter for farms Animals
	 * @param animals the animals to set
	 */
	public void setAnimals(AnimalList animals) {
		this.animals = animals;
	}

	
	/**
	 * Create a new crop instance according to the input crop_id.
	 * @param crop_id the crop identifier of the new crop
	 * @return Crop
	 */
	public Crop createCrop(String crop_id) {
		String name;
		Integer harvestTime;
		Double purchasePrice, sellPrice;
		switch(crop_id) {
			case "carrot":
				name = "Carrot";
				harvestTime =  2 * (bonuses.get("harvestTime").intValue());
				purchasePrice = 9.00;
				sellPrice = 4.50;
				break;
			case "tomato":
				name = "Tomato";
				harvestTime = 3 * (bonuses.get("harvestTime").intValue());
				purchasePrice = 16.00;
				sellPrice = 8.00;
				break;
			case "alpine":
				name = "Alpine Strawberry";
				harvestTime = 2 * (bonuses.get("harvestTime").intValue());
				purchasePrice = 4.00;
				sellPrice = 2.00;
				break;
			case "jack":
				name = "Jack Fruit";
				harvestTime = 4 * (bonuses.get("harvestTime").intValue());
				purchasePrice = 20.00;
				sellPrice = 10.00;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + crop_id);
		}

		// Initialise new crop according to input
		Crop newCrop = new Crop();
		newCrop.setName(name);
		newCrop.setHarvestTime(harvestTime);
		newCrop.setPurchasePrice(purchasePrice);
		newCrop.setSellPrice(sellPrice);

		return newCrop;
	}

	
	/**
	 * Create a new item instance according to the input item_id.
	 * @param item_id the item identifier of the new item
	 * @return Item
	 */
	public Item createItem(String item_id) {
		String name;
		Integer healthValue = null, harvestDecValue = null;
		Double purchasePrice;
		switch(item_id) {
			case "magic":
				name = "Magic Dust";
				harvestDecValue = 3;
				purchasePrice = 5.00;
				break;
			case "mineral":
				name = "Mineral Water";
				healthValue = 5;
				harvestDecValue = 2;
				purchasePrice = 3.50;
				break;
			case "feed":
				name = "Animal Feed";
				healthValue = 7;
				purchasePrice = 3.00;
				break;
			case "cake":
				name = "Frosted Cake";
				healthValue = 15;
				purchasePrice = 7.00;
				break;
			case "Water":
				name = "Water";
				healthValue = 2;
				harvestDecValue = 1;
				purchasePrice = 0.0;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + item_id);
		}

		// Initialise new item according to input
		Item newItem = new Item();
		newItem.setName(name);
		newItem.setHealthValue(healthValue);
		newItem.setHarvestDecValue(harvestDecValue);
		newItem.setPurchasePrice(purchasePrice);

		return newItem;
	}

	
	/**
	 * Create a new animal instance according to the input animal_id.
	 * @param animal_id the animal identifier of the new animal_id
	 * @return Animal
	 */
	public Animal createAnimal(String animal_id) {
		String name;
		Double purchasePrice, tendReturn;
		switch(animal_id) {
			case "cow":
				name = "Cow";
				purchasePrice = 20.00;
				tendReturn = 25.00;
				break;
			case "chicken":
				name = "Chicken";
				purchasePrice = 10.00;
				tendReturn = 15.00;
				break;
			case "pig":
				name = "Pig";
				purchasePrice = 16.00;
				tendReturn = 20.00;
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + animal_id);
		}

		int random_inc = new Random().nextInt(10);

		// Initialise new item according to input
		Animal newAnimal = new Animal();
		newAnimal.setName(name);
		newAnimal.setHappiness((10 * bonuses.get("animalHappiness").intValue()) + random_inc);
		newAnimal.setHealth((10 * bonuses.get("animalHealth").intValue()) + random_inc);
		newAnimal.setPurchasePrice(purchasePrice);
		newAnimal.setTendReturn(tendReturn);

		return newAnimal;
	}

	
	/**
	 * Grows all crops that aren't fully grown by one day
	 */
	public void growCrops() {
		for (Crop crop: crops.getCropList()) {
			if (crop.getHarvestTime() > 0) {
				crop.setHarvestTime(crop.getHarvestTime() - 1);
			}
		}
	}

	
	/**
	 * Changes the farm balance based on the animals happiness and health
	 */
	public void getDailyBonus() {
		for (Animal animal: animals.getAnimalList()) {
			double toAdd = animal.getTendReturn() * ((double)animal.getHappiness() / 100) * ((double)animal.getHealth() / 100);
			this.setBalance(this.getBalance() + toAdd);
		}
	}

	
	/**
	 * Daily decrease in animal health and happiness
	 */
	public void decreaseAnimalWellbeing() {
		for (Animal animal: animals.getAnimalList()) {
			animal.setHealth(animal.getHealth() - 5);
			animal.setHappiness(animal.getHappiness() - 5);
		}
	}


}
