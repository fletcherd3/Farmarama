package classes;

public class Animal extends FarmObject {
	

	private Integer health;
	private Integer happiness;
	private Double tendReturn;
	
	
	/**
	 * Feeds animal with given Item, increasing health 
	 * by Items healthValue
	 * @param item - input item
	 */
	public void feed(Item item) {
		if (health + item.getHealthValue() >= 100) {
			health = 100;
		} else {
			health = health + item.getHealthValue();
		}
	}
	
	
	/**
	 * Plays with Animal, increasing happiness by 10
	 */
	public void play() {
		if (happiness >= 90) {
			happiness = 100;
		} else {
			happiness += 10;
		}
	}
	
	
	/**
	 * Getter for Animals happiness
	 * @return - Integer, happiness
	 */
	public Integer getHappiness() {
		return happiness;
	}
	
	
	/**
	 * Setter for Animals happiness
	 * @param newHappiness - input Integer
	 */
	public void setHappiness(Integer newHappiness) {
		if (newHappiness >= 100) {
			newHappiness = 100;
		}
		happiness = newHappiness;
	}
	
	
	/**
	 * Getter for Animals health
	 * @return - Integer, health
	 */
	public Integer getHealth() {
		return health;
	}
	
	
	/**
	 * Setter for Animals health
	 * @param newHealth - input Integer
	 */
	public void setHealth(Integer newHealth) {
		health = newHealth;
	}
	
	
	/**
	 * Getter for Animals tendReturn
	 * @return - Double, tendReturn
	 */
	public Double getTendReturn() {
		return tendReturn;
	}
	
	
	/**
	 * Setter for Animals tendReturn
	 * @param newTendReturn - input Double
	 */
	public void setTendReturn(Double newTendReturn) {
		tendReturn = newTendReturn;
	}
}
