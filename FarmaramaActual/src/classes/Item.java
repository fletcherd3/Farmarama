package classes;

public class Item extends FarmObject {
	
	private Integer healthValue;
	private Integer harvestDecValue;

	
	/**
	 * Getter for the Items healthValue
	 * @return - Integer, healthValue
	 */
	public Integer getHealthValue() {
		return healthValue;
	}
	
	
	/**
	 * Setter for the Items healthValue
	 * @param newHealth - input Integer
	 */
	public void setHealthValue(Integer newHealth) {
		this.healthValue = newHealth;
	}
	
	
	/**
	 * Getter for the Items harvestDecValue
	 * @return - Integer harvestDecValue
	 */
	public Integer getHarvestDecValue() {
		return harvestDecValue;
	}
	
	
	/**
	 * Setter for the Items harvestDecValue
	 * @param harvestDecValue - input Integer
	 */
	public void setHarvestDecValue(Integer harvestDecValue) {
		this.harvestDecValue = harvestDecValue;
	}

}
