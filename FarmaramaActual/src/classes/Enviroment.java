package classes;

import java.util.*;

import GUI.IntroScreen;
import GUI.Setup_1Screen;
import GUI.Setup_2Screen;

public class Enviroment {

	private Integer daysLeft = 0;
	private Integer totalDays = 0;
	private Integer actionsLeft = 0;
	private Farmer gameFarmer;
	private Farm gameFarm;
	private static Scanner userInput = new Scanner(System.in);

	/**
	 * Initializes everything to start the game
	 */
	public void initializeGame() {
		gameFarmer = new Farmer();
		gameFarm = new Farm();
		setDaysLeft();
		setupFarmer();
		setupFarm();
		gameFarm.setType("Alpine");
		gameFarm.getItems().addItem(gameFarm.createItem("magic"));
		gameFarm.getItems().addItem(gameFarm.createItem("mineral"));
		gameFarm.getItems().addItem(gameFarm.createItem("mineral"));
		gameFarm.getCrops().addCrop(gameFarm.createCrop("tomato"));
		gameFarm.getCrops().addCrop(gameFarm.createCrop("jack"));
		gameFarm.getCrops().addCrop(gameFarm.createCrop("jack"));
		gameFarm.getAnimals().addAnimal(gameFarm.createAnimal("cow"));
		gameFarm.getItems().addItem(gameFarm.createItem("feed"));
	}

	/**
	 * Main game loop that is repeated until the game needs to end
	 */
	public void gameLoop() {
		actionsLeft = 2;
		Boolean dayPassed = false;
		while (!dayPassed) {
			System.out.println("\n");
			ArrayList<String> availableOptions = new ArrayList<String>();
			addAvalibleOptions(availableOptions, actionsLeft);
			printOutOptions(availableOptions);
			Integer optionNum = readOptionInput(availableOptions);
			switch (optionNum) {
			case 1:
				// View Crop & Animal Status
				viewCropAndAnimalStatus();
				break;
			case 2:
				// View Farm Status (Balance)
				System.out.println("Current farm balance: $" + gameFarm.getBalance());
				break;
			case 3:
				// Visit General Store
				visitStore();
				break;
			case 4:
				// Move on to next day
				dayPassed = true;
				break;
			case 5:
				// Tend to Crops
				tendToCrops();
				actionsLeft--;
				break;
			case 6:
				// Feed animals
				feedAnimals();
				actionsLeft--;
				break;
			case 7:
				// Play with animals
				playWithAnimals();
				actionsLeft--;
				break;
			case 8:
				// Harvest Grown Crops
				gameFarm.setBalance(gameFarm.getBalance() + gameFarm.getCrops().harvestGrownCrops());
				actionsLeft--;
				break;
			case 9:
				// Tend to farm land
				gameFarm.tendToFarm();
				actionsLeft--;
				break;
			}

		}
	}

	private void viewCropAndAnimalStatus() {
		CropList crops = gameFarm.getCrops();
		AnimalList animals = gameFarm.getAnimals();
		if (crops.getCropCount() == 0 && (animals.getAnimalCount() == 0)) {
			System.out.println("Oops, you don't have any Crops or Animals!");
			System.out.println("Visit the general store to purchase some.");
		} else {
			if (crops.getCropCount() != 0) {
				System.out.println("Current Crop Status:");
				for (Crop crop : crops.getCropList()) {
					System.out.println(crop.getName() + ", Days till harvestable: " + crop.getHarvestTime());
				}
			}

			if (animals.getAnimalCount() != 0) {
				System.out.println("Current Animal Status:");
				for (Animal animal : animals.getAnimalList()) {
					System.out.println(animal.getName() + ", Happiness level: " + animal.getHappiness()
							+ ". Health level: " + animal.getHealth());
				}
			}
		}
	}

	
	/**
	 * If the user has any animals, gets user input to decide what type of animal to
	 * play with, then calls .play() on all instances of that type of animal
	 */
	private void playWithAnimals() {
		ArrayList<String> avalibleAnimals = gameFarm.getAnimals().getUniqueAnimalStrings();
		if (avalibleAnimals.size() == 0) {
			System.out.println("You have no animals to play with. Try visiting the general store to purchase some");
		} else {
			printOutOptions(avalibleAnimals);
			Integer selectedIndex = readOptionInput(avalibleAnimals) - 1;
			String selectedAnimal = avalibleAnimals.get(selectedIndex);
			for (Animal animal : gameFarm.getAnimals().getAnimalList()) {
				if (animal.getName() == selectedAnimal) {
					animal.play();
				}
			}
			System.out.println("You have played with your " + selectedAnimal + "s");
		}
	}

	/**
	 * Based on the given type of Item that is desired, will provide options to user
	 * that suit said type and gets input from the user, returning the appropriate
	 * result.
	 * 
	 * @param type - String of "Food" or "Crop"
	 * @return Item - Item that will be used
	 */
	private Item getItemToBeUsed(String type) {
		ArrayList<Integer> itemCounts = gameFarm.getItems().getEachItemCounts();
		ArrayList<String> uniqueItems = gameFarm.getItems().getUniqueItemStrings();
		for (int i = 0; i < itemCounts.size(); i++) {
			Item currItem = gameFarm.getItems().getItemFromString(uniqueItems.get(i));
			if (type == "Food") {
				if (currItem.getHealthValue() == null) {
					itemCounts.remove(i);
					uniqueItems.remove(i);
				}
			} else if (type == "Crop") {
				if (currItem.getHarvestDecValue() == null) {
					itemCounts.remove(i);
					uniqueItems.remove(i);
				}
			}
		}
		if (uniqueItems.size() == 0) {
			Item selectedItem = new Item();
			selectedItem.setName(null);
			return selectedItem;
		} else {
			System.out.println("Select which item you would like to use: ");
			printOutOptionCounts(uniqueItems, itemCounts);
			Integer chosenItemIndex = readOptionInput(uniqueItems) - 1;
			String chosenItemName = uniqueItems.get(chosenItemIndex);
			Item selectedItem = gameFarm.getItems().getItemFromString(chosenItemName);
			gameFarm.getItems().getInventory().remove(selectedItem);
			return selectedItem;
		}
	}

	/**
	 * Runs all required methods to get user to use Item on a certain type of animal
	 */
	private void feedAnimals() {
		Item selectedItem = getItemToBeUsed("Food");
		if (gameFarm.getAnimals().getAnimalList().size() == 0) {
			System.out.println("You have no animals to feed.");
		} else if (selectedItem.getName() == null) {
			System.out.println("You have no food. Try visiting the general store to purchase some.");
		} else {
			System.out.println("Please enter which animal you would like to use " + selectedItem.getName() + " on.");
			ArrayList<String> animalOptions = gameFarm.getAnimals().getUniqueAnimalStrings();
			printOutOptions(animalOptions);
			Integer chosenAnimalIndex = readOptionInput(animalOptions) - 1;
			String chosenAnimalName = animalOptions.get(chosenAnimalIndex);
			for (Animal animal : gameFarm.getAnimals().getAnimalList()) {
				if (animal.getName() == chosenAnimalName) {
					animal.feed(selectedItem);
				}
			}
			System.out.println(selectedItem.getName() + " has been used on your " + chosenAnimalName + "s");
		}

	}

	/**
	 * Runs all required methods to tend to crops, Getting user input where
	 * necessary
	 */
	private void tendToCrops() {
		Item selectedItem = getItemToBeUsed("Crop");
		if (gameFarm.getCrops().getCropList().size() == 0) {
			System.out.println("You have no crops to tend to. Try visiting the general store to purchase some");
		} else if (selectedItem.getName() == null) {
			System.out.println("You have nothing to tend to you crops with. Try visiting the general store.");
		} else {
			System.out.println("Please enter which crop you would like to use " + selectedItem.getName() + " on.");
			ArrayList<String> cropOptions = gameFarm.getCrops().getUniqueCropStrings();
			printOutOptions(cropOptions); // Prints out unique Crops
			Integer chosenCropIndex = readOptionInput(cropOptions) - 1;
			String chosenCropName = cropOptions.get(chosenCropIndex);
			for (Crop crop : gameFarm.getCrops().getCropList()) {
				if (crop.getName() == chosenCropName) {
					crop.tend(selectedItem);
				}
			}
			System.out.println(selectedItem.getName() + " has been used on your " + chosenCropName + "s");
		}

	}

	/**
	 * Prints out numbered items and their corresponding quantities.
	 */
	private void printOutOptionCounts(ArrayList<String> items, ArrayList<Integer> itemCounts) {
		Integer itemIndex = 1;
		for (String item : items) {
			if (itemCounts.get(itemIndex - 1) != null) {
				System.out.println(itemIndex + ") " + item + " - " + itemCounts.get(itemIndex - 1));
				itemIndex++;
			}
		}
	}

	/**
	 * Prints all required prompts for visiting the shop and calls the appropriate
	 * method when an option is selected
	 */
	private void visitStore() {
		Boolean shopping = true;
		System.out.println("Welcome to the General Store!");
		ArrayList<String> store_options = new ArrayList<String>();
		store_options.add("Purchase a Cow");
		store_options.add("Purchase a Chicken");
		store_options.add("Purchase a Pig");
		store_options.add("Purchase Magic Dust");
		store_options.add("Purchase Mineral Water");
		store_options.add("Purchase Animal Feed");
		store_options.add("Purchase Frosted Cake");
		store_options.add("Purchase a Carrot");
		store_options.add("Purchase a Tomato");
		store_options.add("Purchase an Alpine Strawberry");
		store_options.add("Purchase a Jack Fruit");
		store_options.add("Exit Store");
		while (shopping) {
			System.out.printf("You have $%.2f to spend\n\n", gameFarm.getBalance());
			printOutOptions(store_options);
			Integer optionNum = readOptionInput(store_options);
			// Complete purchase
			switch (optionNum) {
			case 1:
				// Purchase a Cow
				makePurchase("animal", "cow");
				break;
			case 2:
				// Purchase a Chicken
				makePurchase("animal", "chicken");
				break;
			case 3:
				// Purchase a Pig
				makePurchase("animal", "pig");
				break;
			case 4:
				// Purchase Magic Dust
				makePurchase("item", "magic");
				break;
			case 5:
				// Purchase Mineral Water
				makePurchase("item", "mineral");
				break;
			case 6:
				// Purchase Animal Feed
				makePurchase("item", "feed");
				break;
			case 7:
				// Purchase Fr595d5da9-fe38-8f41-8ea5-a31e3353ce54osted Cake
				makePurchase("item", "cake");
				break;
			case 8:
				// Purchase a Carrot
				makePurchase("crop", "carrot");
				break;
			case 9:
				// Purchase a Tomato
				makePurchase("crop", "tomato");
				break;
			case 10:
				// Purchase an Alpine Strawberry
				makePurchase("crop", "alpine");
				break;
			case 11:
				// Purchase a Jack Fruit
				makePurchase("crop", "jack");
				break;
			case 12:
				// Exit Store
				shopping = false;
				break;
			}
		}
	}

	/**
	 * Given a item type and purchase id, both strings, will purchase item if
	 * possible, deducting price and adding to inventory
	 * 
	 * @param type     - String
	 * @param purch_id - String
	 */
	private void makePurchase(String type, String purch_id) {
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
			throw new IllegalStateException("Unexpected value: " + type);
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
				throw new IllegalStateException("Unexpected value: " + type);
			}

		} else {
			System.out.printf("Sorry, you don't have enough money for a %s\n", item.getName());
			item = null; // Delete unused item from memory
		}
	}

	/**
	 * Terminates the game and produces end info
	 */
	public void endGame() {
		System.out.println("Game Over");
		System.out.println("Farm name - " + gameFarm.getName());
		System.out.println("Farmer name - " + gameFarmer.getName());
		System.out.println("Game duration - " + totalDays);
		Double totalProfit = gameFarm.getBalance() - gameFarm.getStartingBalance();
		System.out.println("Total profit - $" + totalProfit);
		System.out.println("Total score - " + (totalProfit / totalDays));
	}

	/**
	 * Reads out options, prompting user till a valid one is given
	 * 
	 * @param avalibleOptions - ArrayList of Strings
	 * @return - Integer, selectedOption
	 */
	public Integer readOptionInput(ArrayList<String> avalibleOptions) {
		Integer selectedOption = 0;
		Boolean validInput = false;
		while (!validInput) {
			if (userInput.hasNextInt()) {
				Integer input = userInput.nextInt();
				userInput.nextLine(); // Read newline character
				if ((input > 0) && (input <= avalibleOptions.size())) {
					validInput = true;
					selectedOption = input;
				}
			} else {
				userInput.nextLine();
			}
			if (!validInput) {
				System.out.print("Please enter a valid input: ");
			}
		}
		return selectedOption;
	}

	/**
	 * Prints out all given availableOptions
	 * 
	 * @param availableOptions - ArrayList of Strings
	 */
	public void printOutOptions(ArrayList<String> availableOptions) {
		System.out.println("Select Option");
		for (int i = 0; i < availableOptions.size(); i++) {
			Integer optionNum = i + 1;
			System.out.println(optionNum + ") " + availableOptions.get(i));
		}
		System.out.print("Enter corresponding number: ");
	}

	/**
	 * Adds Strings to availableOptions depending on how many actions are left.
	 * 
	 * @param avalibleOptions - ArrayList of Strings
	 * @param actionsLeft     - Integer
	 * @return - Integer, availableOptions
	 */
	public ArrayList<String> addAvalibleOptions(ArrayList<String> avalibleOptions, Integer actionsLeft) {
		avalibleOptions.add("View Crop/Animal Status");
		avalibleOptions.add("View Farm Status");
		avalibleOptions.add("View General Store");
		avalibleOptions.add("Move on to next day");
		if (actionsLeft != 0) {
			avalibleOptions.add("Tend to Crops - Costs 1 Action");
			avalibleOptions.add("Feed animals - Costs 1 Action");
			avalibleOptions.add("Play with animals - Costs 1 Action");
			avalibleOptions.add("Harvest Grown Crops - Costs 1 Action");
			avalibleOptions.add("Tend to farm Land - Costs 1 Action");
		}
		return avalibleOptions;
	}

	/**
	 * Prompts user for input on how many days they wish to play if a valid input is
	 * not given, it will re-prompt the user until one is
	 */
	public void setDaysLeft() {
		Boolean isValidInput = false;
		System.out.print("Enter the number of days(5-10) you want the game to last: ");
		while (!isValidInput) {
			if (userInput.hasNextInt()) {
				Integer input = userInput.nextInt();
				userInput.nextLine(); // Read newline character
				if ((input > 4) && (input < 11)) {
					isValidInput = true;
					this.daysLeft = input;
					this.totalDays = input;
				}
			} else {
				userInput.nextLine();
			}
			if (!isValidInput) {
				System.out.print("Please enter a valid input: ");
			}
		}

	}

	/**
	 * Sets up the Farmer to be used for the game, including prompting user for a
	 * valid farmer name input and age input, repeating until they are given
	 */
	public void setupFarmer() {

		// Read input from user and set farmers name to a valid input.
		String name_input = "";
		System.out.print("Enter your farmers name: ");
		if (userInput.hasNextLine()) {
			name_input = userInput.nextLine();
		}
		Boolean isValidInput = this.gameFarmer.setName(name_input); // Check if valid
		while (!isValidInput) { // While input isn't valid
			System.out.println("Oops, that name was invalid!");
			System.out.println("Try again without numbers, special characters and keep it between 3-15 characters.");
			System.out.print("Enter your farmers name: ");
			if (userInput.hasNextLine()) {
				name_input = userInput.nextLine();
			}
			isValidInput = this.gameFarmer.setName(name_input); // Check if valid
		}

		// Read input from user and set farmers age to a valid input.
		Integer age_input = -1; // Set input to a failing case
		System.out.print("Enter your farmers age: ");
		isValidInput = this.gameFarmer.setAge(age_input); // Check if valid
		while (!isValidInput) { // While input isn't valid
			if (userInput.hasNextInt()) {
				age_input = userInput.nextInt();
				userInput.nextLine();
			} else {
				userInput.nextLine();
			}
			isValidInput = this.gameFarmer.setAge(age_input);
			if (!isValidInput) {
				System.out.println("Oops, that name was invalid!");
				System.out.println("Try again with a valid age.");
				System.out.print("Enter your farmers age: ");
			}

		}
	}

	/**
	 * Calls methods required to setup the farm
	 */
	public void setupFarm() {
		setupFarmType();
		setupFarmName();
	}

	/**
	 * Sets up the farm type by prompting for user input and the changing farms type
	 * accordingly
	 */
	public void setupFarmType() {
		Boolean isValidInput = false;
		ArrayList<String> farmTypes = new ArrayList<String>();
		farmTypes.add("Plain");
		farmTypes.add("Cold");
		farmTypes.add("Hot");
		farmTypes.add("Alpine");
		System.out.println("Choose your farm type my entering in the corresponding number: ");
		for (int i = 0; i < farmTypes.size(); i++) {
			System.out.print((i + 1) + ") ");
			System.out.println(farmTypes.get(i));
		}
		while (!isValidInput) {
			if (userInput.hasNextInt()) {
				Integer input = userInput.nextInt();
				userInput.nextLine(); // avoiding scanner bug
				if ((input > 0) && (input < 5)) {
					isValidInput = true;
					if (input == 1) {
						gameFarm.setType("Plain");
					} else if (input == 2) {
						gameFarm.setType("Cold");
					} else if (input == 3) {
						gameFarm.setType("Hot");
					} else if (input == 4) {
						gameFarm.setType("Alpine");
					}
				} else {
					System.out.print("Please enter a valid input: ");
				}
			} else {
				userInput.nextLine();
				System.out.print("Please enter a valid input: ");
			}
		}

	}

	/**
	 * Sets up the farm name, prompting for input until a valid one if given.
	 */
	public void setupFarmName() {
		Boolean isValidInput = false;
		System.out.print("Enter your farm name: ");
		while (!isValidInput) {
			if (userInput.hasNextLine()) {
				isValidInput = true;
				String response = userInput.nextLine();
				gameFarm.setName(response);
			} else {
				System.out.print("Please enter a valid name: ");
			}
		}

	}


	/**
	 * Main function to run game
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Enviroment game = new Enviroment();
		game.initializeGame();
		while (game.daysLeft >= 0) {
			System.out.println("Remaining Days: " + game.daysLeft);
			game.gameLoop();
			game.daysLeft --;
			game.gameFarm.growCrops();
			game.gameFarm.getDailyBonus();
			game.gameFarm.decreaseAnimalWellbeing();
		}
		game.endGame();
		
	}


	

}
