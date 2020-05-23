package classes;

import java.util.ArrayList;
import java.util.Hashtable;

public class CropList {
	
	private ArrayList<Crop> cropList = new ArrayList<Crop>();
	
	
	/**
	 * Setter for crop list
	 * @param newList - ArrayList of Crop
	 */
	public void setCropList(ArrayList<Crop> newList) {
		cropList = newList;
	}
	

	/**
	 * Getter for crop list
	 * @return ArrayList of Crop
	 */
	public ArrayList<Crop> getCropList() {
		return cropList;
	}
	
	
	
	/**
	 * Adds a given crop to the crop list 
	 * @param crop - Crop to add to list
	 */
	public void addCrop(Crop crop) {
		cropList.add(crop);
	}
	
	
	/**
	 * Return the length of the crop list
	 * @return - Integer, crop list length
	 */
	public Integer getCropCount() {
		return cropList.size();
	}
		
	
	/**
	 * Returns an integer array corresponding to the crop counts
	 * @return ArrayList - of Integers
	 */
	public ArrayList<Integer> getEachCropCount() {
		ArrayList<Integer> cropCounts = new ArrayList<Integer>();
        ArrayList<String> uniqueCrops = this.getUniqueCropStrings();
        Integer index = 0;
        // Loop though crops in unique crops
        for ( String uCrop: uniqueCrops) {
            cropCounts.add(0);
            for (Crop crop: cropList) {
                if (uCrop == crop.getName()) {
                    Integer currCount = cropCounts.get(index);
                    cropCounts.set(index, currCount + 1);
                }
            }
            index ++;
        }
        return cropCounts;
	}

	
	/**
	 * Return the value of all fully grown crops
	 * and removes them from the crop list
	 * @return - double
	 */
	public double harvestGrownCrops() {
		Double result = 0.0;
		for (int i = 0; i < this.getCropCount(); i++) {
			Crop currentCrop = cropList.get(i);
			if (currentCrop.getHarvestTime() == 0) {
				result += currentCrop.getSellPrice();
				cropList.remove(i);
				i--;
			}
		}
		return result;
	}
	
	/**
	 * Return the amount of harvestable crops
	 * @return - integer
	 */
	public int harvestAmount() {
		int count = 0;
		for (int i = 0; i < this.getCropCount(); i++) {
			Crop currentCrop = cropList.get(i);
			if (currentCrop.getHarvestTime() == 0) {
				count ++;
			}
		}
		
		return count;
	}


	/**
	 * Returns an ArrryList of Strings of unique owned Items names
	 * @return ArrayList of Strings
	 */
	public ArrayList<String> getUniqueCropStrings() {
		ArrayList<String> result = new ArrayList<String>();
		for (Crop crop: cropList) {
			if (!result.contains(crop.getName())) {
				result.add(crop.getName());
			}
		}
		return result;
	}
	
}
