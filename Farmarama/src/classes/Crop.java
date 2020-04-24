package classes;

public class Crop {
	
	public String name;
	public Double purchasePrice;
	public Double sellPrice;
	public Integer harvestTime;
	
	public void tend(Item item) {
		harvestTime = harvestTime * item.harvestDecValue;
	}

}
