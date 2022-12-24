package app.model;

import app.Reinstanciable;

/**
 * 
 * @author ben
 * Le compagnon à prendre soin
 */
public abstract class Pet implements Reinstanciable  {
	
	protected String type;
	
	// ATTENTION: References partagées avec c.Stat
	protected State hunger;
	protected State thirst;
	protected State weight;
	protected State hygiene;
	protected State moral;
	
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
