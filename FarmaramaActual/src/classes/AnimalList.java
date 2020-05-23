package classes;

import java.util.ArrayList;

public class AnimalList {

	private ArrayList<Animal> animalList = new ArrayList<Animal>();
	
	/**
	 * Setter for animal list
	 * @param newList - ArrayList of Animal
	 */
	public void setAnimalList(ArrayList<Animal> newList) {
		animalList = newList;
	}
	

	/**
	 * Getter for animal list
	 * @return - ArrayList of Animal
	 */
	public ArrayList<Animal> getAnimalList() {
		return animalList;
	}
	
	
	/**
	 * Adds the given animal to the animal list
	 * @param animal - Animal
	 */
	public void addAnimal(Animal animal) {
		animalList.add(animal);
	}
	
	
	/**
	 * Return the Length of the animal list
	 * @return - Integer, length of animal list
	 */
	public Integer getAnimalCount() {
		return animalList.size();
	}


	/**
	 * Returns an ArrayList of integers that correspond to the quantity of
	 * each item output by getUniqueAnimalStrings()
	 * @return - ArrayList of Integers
	 */
	public ArrayList<Integer> getEachAnimalCount() {
		ArrayList<Integer> animalCounts = new ArrayList<Integer>();
		ArrayList<String> uniqueAnimals = this.getUniqueAnimalStrings();
		Integer index = 0;
		for ( String uAnimal: uniqueAnimals) {
			animalCounts.add(0);
			for (Animal animal: animalList) {
				if (uAnimal == animal.getName()) {
					Integer currCount = animalCounts.get(index);
					animalCounts.set(index, currCount + 1);
				}
			}
			index ++;
		}
		return animalCounts;
	}


	/**
	 * Returns an ArrayList of unique animal names
	 * in the order they appear
	 * @return - ArrayList of Strings
	 */
	public ArrayList<String> getUniqueAnimalStrings() {
		 ArrayList<String> result = new ArrayList<String>();
		 for (Animal animal: animalList) {
		 	if (!result.contains(animal.getName())) {
		 		result.add(animal.getName());
			}
		 }
		 return result;
	}
	
	
}
