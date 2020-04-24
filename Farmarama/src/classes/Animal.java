package classes;

public class Animal {
	
	public String name;
	public Double purchasePrice;
	public Integer health;
	public Integer happiness;
	public Double tendReturn;
	
	
	public void feed(Item item) {
		health = (int)(health * item.healthValue);
	}
	
	public void play() {
		happiness += 10;
	}
	
	public Integer getHappiness() {
		return happ
	}
	
}
