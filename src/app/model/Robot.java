package app.model;

/**
 * 
 * @author ben
 * L'animal à prendre soin
 */
public class Robot implements Pet {

	// ATTENTION: Reference partagé avec constroller.Stat	
	private Stat hunger;
	private Stat thirst;
	private Stat weight;
	private Stat hygiene;
	private Stat moral;
	
	public Robot () {
	}
	
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
}