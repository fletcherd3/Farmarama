package classes;

public class Crop extends FarmObject {
	
	private Double sellPrice;
	private Integer harvestTime;
	
	
	/**
	 * Tends to a crop, changing harvetTime by the items
	 * harvestDecValue
	 * @param item - input Item
	 */
	public void tend(Item item) {
		if (item.getHarvestDecValue() >= harvestTime) {
			harvestTime = 0;
		} else {
			harvestTime = harvestTime - item.getHarvestDecValue();
		}
	}

	
	/**
	 * Getter for the crops sell price
	 * @return - Double, sellPrice
	 */
	public Double getSellPrice() {
		return sellPrice;
	}

	
	/**
	 * Setter for the crops sellPrice
	 * @param sellPrice - input Double
	 */
	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	
	/**
	 * Getter for the crops harvestTime
	 * @return - Integer, harvestTime
	 */
	public Integer getHarvestTime() {
		return harvestTime;
	}

	
	/**
	 * Setter for the crops harvestTime
	 * @param harvestTime - input Integer
	 */
	public void setHarvestTime(Integer harvestTime) {
		this.harvestTime = harvestTime;
	}

}
