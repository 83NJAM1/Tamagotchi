package app.model;

import app.Componable;

/**
 * 
 * @author ben
 * Le compagnon à prendre soin
 */
public abstract class Pet implements Componable  {
	
	protected String type;
	
	// ATTENTION: References partagées avec c.Stat
	protected State hunger;
	protected State thirst;
	protected State weight;
	protected State hygiene;
	protected State moral;
	
	protected boolean takingShower;
	protected boolean eating;
	protected boolean drinking;
	protected boolean playing;
	
	public Pet(String type) {
		this.type = type;
		takingShower = false;
		eating = false;
		drinking = false;
		playing = false;
	}
	
	public void toogleTakingShower() {
		takingShower = !takingShower;
	}
	public void toogleEating() {
		eating = !eating;
	}
	public void toogleDrinking() {
		drinking = !drinking;
	}
	public void tooglePlaying() {
		playing = !playing;
	}
	
	public boolean isTakingShower() {
		return takingShower;
	}
	public boolean isEating() {
		return eating;
	}
	public boolean isDrinking() {
		return drinking;
	}
	public boolean isPlaying() {
		return playing;
	}
	
	public void setHunger(State hunger) {
		this.hunger = hunger;
	}
	public void setThirst(State thirst) {
		this.thirst = thirst;
	}
	public void setWeight(State weight) {
		this.weight = weight;
	}
	public void setHygiene(State hygiene) {
		this.hygiene = hygiene;
	}
	public void setMoral(State moral) {
		this.moral = moral;
	}
	
	public String getType() {
		return type;
	}
	
	public State getHunger() {
		return hunger;
	}
	public State getThirst() {
		return thirst;
	}
	public State getWeight() {
		return weight;
	}
	public State getHygiene() {
		return hygiene;
	}
	public State getMoral() {
		return moral;
	}
	
	public void setMalusForAllStates(String key, Double factor) {
		hunger.setMalus(key, factor);
		hygiene.setMalus(key, factor);
		moral.setMalus(key, factor);
		thirst.setMalus(key, factor);
		weight.setMalus(key, factor);
	}
	
	public void setBonusForAllStates(String key, Double factor) {
		hunger.setBonus(key, factor);
		hygiene.setBonus(key, factor);
		moral.setBonus(key, factor);
		thirst.setBonus(key, factor);
		weight.setBonus(key, factor);
	}
	
	public void applyBonus(String key){
		hunger.applyBonus(key);
		hygiene.applyBonus(key);
		moral.applyBonus(key);
		thirst.applyBonus(key);
		weight.applyBonus(key);
	}
	
	public void applyMalus(String key){
		hunger.applyMalus(key);
		hygiene.applyMalus(key);
		moral.applyMalus(key);
		thirst.applyMalus(key);
		weight.applyMalus(key);
	}
	
	public void applyEffect(String key) {
		applyBonus(key);
		applyMalus(key);
	}
	
	public int getStatsNumber() {
		return 5;
	}
	
	@Override
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
	
	@Override
	public void exit() {
		hunger = null;
		thirst = null;
		weight = null;
		hygiene = null;
		moral = null;
	}
}
