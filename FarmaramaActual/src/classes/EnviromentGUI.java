package classes;

import java.util.ArrayList;
import java.util.Scanner;

import GUI.EndScreen;
import GUI.IntroScreen;
import GUI.Setup_1Screen;
import GUI.Setup_2Screen;
import GUI.MainScreen;
import GUI.StoreScreen;
import GUI.ViewCropAnimalScreen;

public class EnviromentGUI {

	private Integer daysLeft = 0;
	private Integer totalDays = 0; 
	private Integer actionsLeft = 0;
	private Farmer gameFarmer;
	private Farm gameFarm;

	/**
	 * Getter for GameFarmer.
	 * @return Farmer
	 */
	public Farmer getGameFarmer() {
		return gameFarmer;
	}


	/**
	 * Setter for GameFarmer.
	 * @param newGameFarmer
	 */
	public void setGameFarmer(Farmer newGameFarmer) {
		gameFarmer = newGameFarmer;
	}


	/**
	 * Getter for DaysLeft.
	 * @return Integer
	 */
	public Integer getDaysLeft() {
		return daysLeft;
	}

	
	/**
	 * Sets the daysLeft to the given Integer i.
	 * @param i - Integer
	 */
	public void setDaysLeft(Integer i) {
		this.daysLeft = i;
	}


	/**
	 * Setter for Total Days.
	 * @param totalDays
	 */
	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}


	/**
	 * Setter for GameFarm.
	 * @param gameFarm
	 */
	public void setGameFarm(Farm gameFarm) {
		this.gameFarm = gameFarm;
	}


	/**
	 * Getter for total days.
	 * @return - Integer
	 */
	public Integer getTotalDays() {
		return totalDays;
	}


	/**
	 * Getter for actionsLeft.
	 * @return Integer
	 */
	public Integer getActionsLeft() {
		return actionsLeft;
	}


	/**
	 * Setter for actions left.
	 * @param actionsLeft
	 */
	public void setActionsLeft(Integer actionsLeft) {
		this.actionsLeft = actionsLeft;
	}


	/**
	 * Getter for the game Farm.
	 * @return Farm
	 */
	public Farm getGameFarm() {
		return gameFarm;
	}

	
	/**
	 * Initializes everything to required to start a game
	 */
	public void initializeGame() {
		gameFarmer = new Farmer();
		gameFarm = new Farm();
		actionsLeft = 2;
	}


	/**
	 * Given a string of an animal name, plays with all occurrences of that animal.
	 * in the games farm
	 * @param animalName
	 */
	public void playWithAnimals(String animalName) {
		for (Animal animal : gameFarm.getAnimals().getAnimalList()) {
			if (animal.getName() == animalName) {
				animal.play();
			}
		}
	}
	

	/**
	 * Given an item type as a string, will return all available items
	 * of that type.
	 * @param type - String
	 * @return ArrayList - of Strings
	 */
	public ArrayList<String> getAvalibleItems(String type) {
		ArrayList<String> uniqueItems = gameFarm.getItems()
				.getUniqueItemStrings();
		for (int i = 0; i < uniqueItems.size(); i++) {
			Item currItem = gameFarm.getItems().
					getItemFromString(uniqueItems.get(i));
			if (type == "Food") {
				if (currItem.getHealthValue() == null) {
					uniqueItems.remove(i);
				}
			} else if (type == "Crop") {
				if (currItem.getHarvestDecValue() == null) {
					uniqueItems.remove(i);
				}
			}
		}
		uniqueItems.add("Water");
		return uniqueItems;
	}

	/**
	 * Feeds all given animal with the given item and removes the item from the users
	 * inventory
	 * @param item - Item
	 * @param animalName - String
	 */
	public void feedAnimals(Item item, String animalName) {
		for (Animal animal: gameFarm.getAnimals().getAnimalList()) {
			if (animal.getName() == animalName) {
				animal.feed(item);
			}
		}
		gameFarm.getItems().getInventory().remove(item);
	}


	/**
	 * Tends to all given crops with the given item and removes the item from the
	 * users inventory
	 * @param item - Item
	 * @param cropName - String
	 */
	public void tendToCrops(Item item, String cropName) {
		for (Crop crop: gameFarm.getCrops().getCropList()) {
			if (crop.getName() == cropName) {
				crop.tend(item);
			}
		}
		gameFarm.getItems().getInventory().remove(item);
	}



	/**
	 * Given a item type and purchase id, both strings, will purchase item if
	 * possible, deducting price and adding to inventory,
	 * returning true if able to purchase, false otherwise.
	 * 
	 * @param type     - String
	 * @param purch_id - String
	 * @return boolean
	 */
	public Boolean makePurchase(String type, String purch_id) {
		FarmObject item;
		switch (type) {
		case "item":
			item = gameFarm.createItem(purch_id);
			break;
		case "animal":
			item = gameFarm.createAnimal(purch_id);
			break;
		case "crop":
			item = gameFarm.createCrop(purch_id);
			break;
		default:
			throw new IllegalStateException("Unexpected value: "
		+ type);
		}
		if (item.getPurchasePrice() <= gameFarm.getBalance()) {
			// Make purchase
			gameFarm.modifyBalance(-(item.getPurchasePrice()));

			switch (type) {
			case "item":
				gameFarm.getItems().addItem((Item) item);
				break;
			case "animal":
				gameFarm.getAnimals().addAnimal((Animal) item);
				break;
			case "crop":
				gameFarm.getCrops().addCrop((Crop) item);
				break;
			default:
				throw new IllegalStateException("Un"
						+ "expected value: " + type);
			}

		} else {
			item = null; // Delete unused item from memory
			return false;
		}
		return true;
	}





	/**
	 * Sets the farmers age to the given input if it is valid
	 * @param age_input String
	 * @return - Boolean
	 */
	public Boolean setupFarmerAge(String age_input) {
		Boolean isValidInput = false;
		Scanner ageScan = new Scanner(age_input);
		Integer ageToCheck;
		if (ageScan.hasNextInt()){
			ageToCheck = ageScan.nextInt();
			isValidInput = this.gameFarmer.setAge(ageToCheck);
		}
		return isValidInput;
	}


	/**
	 * Sets up the farmers name from the given string input
	 * @param name_input - String
	 * @return isValidInput - Boolean
	 */
	public Boolean setupFarmerName(String name_input) {
		Boolean isValidInput = this.gameFarmer.setName(name_input);
		return isValidInput;
	}



	/**
	 * Sets up the farm type from the given input string
	 * @param farmType - String
	 */
	public void setupFarmType(String farmType) {
		if (farmType == "Plain") {
			gameFarm.setType("Plain");
		} else if (farmType == "Cold") {
			gameFarm.setType("Cold");
		} else if (farmType == "Hot") {
			gameFarm.setType("Hot");
		} else if (farmType == "Alpine") {
			gameFarm.setType("Alpine");
		} else {
			throw new IllegalArgumentException("Invalid farm type");
		}
	}

	/**
	 * Sets up farm name if the given name is valid
	 * @param nameInput - String
	 * @return isValidInput - Boolean
	 */
	public Boolean setupFarmName(String nameInput) {
		Boolean isValidInput = false;
		if ((nameInput.length() >= 3) && (nameInput.length() < 16)) {
			gameFarm.setName(nameInput);
			isValidInput = true;
		}
		return isValidInput;
	}


	/**
	 * Main function to run the game
	 * @param args
	 */
	public static void main(String[] args) {
		EnviromentGUI game = new EnviromentGUI();
		game.initializeGame();
		game.launchIntroScreen();
	}

	/**
	 * Closes the first setup screen and launches the second setup Screen
	 * @param setup_1Screen
	 */
	public void closeSetup_1Screen(Setup_1Screen setup_1Screen) {
		setup_1Screen.closeWindow();
		launchSetup2Screen();
	}

	/**
	 * Closes the intro screen and launches the first setupScreen
	 * @param introScreen
	 */
	public void closeIntroWindow(IntroScreen introScreen) {
		introScreen.closeWindow();
		launchSetup1Screen();
	}

	/**
	 * Closes the second setup screen and launches the main screen
	 * @param setup_2Screen
	 */
	public void closeSetup_2Screen(Setup_2Screen setup_2Screen) {
		setup_2Screen.closeWindow();
		launchMainScreen();
	}

	/**
	 * Given the mainScreen and a destination, closes the main screen and changes 
	 * to the given destination string
	 * @param mainScreen - MainScreen
	 * @param destination - String
	 */
	public void closeMainScreen(MainScreen mainScreen, String destination) {
		mainScreen.closeWindow();
		if (daysLeft < 0) {
			launchEndScreen();
		} else {
			switch (destination) {
			case "StoreScreen":
				launchStoreScreen();
				break;
			case "ViewCropAnimalScreen":
				launchViewCropAnimalScreen();
				break;
			case "ViewItemScreen":
//				launchViewItemScreen();
				break;
			case "EndScreen":
				launchEndScreen();
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + destination);
			}
		}
	}

	/**
	 * Closes the store screen and launches the main screen
	 * @param storeScreen
	 */
	public void closeStoreScreen(StoreScreen storeScreen) {
		storeScreen.closeWindow();
		launchMainScreen();
	}

	/**
	 * Closes the viewCropAndAnimal screen and launches the main screen
	 * @param viewCropAnimalScreen
	 */
	public void closeViewCropAnimalScreen(ViewCropAnimalScreen viewCropAnimalScreen) {
		viewCropAnimalScreen.closeWindow();
		launchMainScreen();
	}


	/**
	 * Closes the end screen
	 * @param endScreen
	 */
	public void closeEndScreen(EndScreen endScreen) {
		endScreen.closeWindow();
	}

	/**
	 * Launches the intro screen
	 */
	private void launchIntroScreen() {
		IntroScreen introScreen = new IntroScreen(this);
	}

	/**
	 * Launches the first setup screen
	 */
	private void launchSetup1Screen() {
		Setup_1Screen setup1window = new Setup_1Screen(this);
	}

	/**
	 * Launches the second setup Screen
	 */
	private void launchSetup2Screen() {
		Setup_2Screen setup2Windows = new Setup_2Screen(this);
	}

	/**
	 * Launches the main screen
	 */
	private void launchMainScreen() {
		MainScreen mainWindow = new MainScreen(this);
	}

	/**
	 * Launches the view crop and animal screen
	 */
	private void launchViewCropAnimalScreen() {
		ViewCropAnimalScreen viewCropAnimalWindow = new ViewCropAnimalScreen(this);
	}
	
	
	/**
	 * Launches the store screen
	 */
	private void launchStoreScreen() {
		StoreScreen storeWindow = new StoreScreen(this);
	}
	
	/**
	 * Launches the end screen
	 */
	private void launchEndScreen() {
		EndScreen endScreen = new EndScreen(this);
	}

}
