package app.model;
 
/**
 * 
 * @author ben
 * Le compagnon à prendre soin
 */
public abstract class Pet {
	
	protected String type;
	
	// ATTENTION: References partagées avec c.Stat
	protected Stat hunger;
	protected Stat thirst;
	protected Stat weight;
	protected Stat hygiene;
	protected Stat moral;
	
	public void setHunger(Stat hunger) {
		this.hunger = hunger;
	}
	public void setThirst(Stat thirst) {
		this.thirst = thirst;
	}
	public void setWeight(Stat weight) {
		this.weight = weight;
	}
	public void setHygiene(Stat hygiene) {
		this.hygiene = hygiene;
	}
	public void setMoral(Stat moral) {
		this.moral = moral;
	}
	
	public String getType() {
		return type;
	}
	
	public Stat getHunger() {
		return hunger;
	}
	public Stat getThirst() {
		return thirst;
	}
	public Stat getWeight() {
		return weight;
	}
	public Stat getHygiene() {
		return hygiene;
	}
	public Stat getMoral() {
		return moral;
	}
	
	public String toString() {
		String output = getType() + System.lineSeparator() 
					  +	getStatsNumber() + System.lineSeparator()
					  +	getHunger().toString() + System.lineSeparator()
		  			  + getThirst().toString() + System.lineSeparator()
		  			  + getWeight().toString() + System.lineSeparator()
		  			  + getHygiene().toString() + System.lineSeparator()
		  			  + getMoral().toString() + System.lineSeparator();
		return output;
	}
	
	public int getStatsNumber() {
		return 5;
	}
	
}
