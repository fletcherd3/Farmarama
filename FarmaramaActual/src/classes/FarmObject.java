package classes;

public class FarmObject {

	private String name;
	private Double purchasePrice;
	
	/**
	 * Getter for the farmObject name
	 * @return - String, name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Setter for farm Objects name
	 * @param name - input String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Getter for the farmObjects purchasePrice
	 * @return - Double, purchasePrice
	 */
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	
	/**
	 * Setter for the farmObjects purchasePrice
	 * @param purchasePrice - input Double
	 */
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
}
