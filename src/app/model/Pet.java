package app.model;

import app.Componable;

/**
 * 
 * @author ben
 * Le compagnon à prendre soin
 */
public abstract class Pet implements Componable  {
	
	protected String type;
	
	protected State hunger; // \
	protected State thirst; //  |
	protected State weight; //  |--> NOTE: Reference partagé avec c.State
	protected State hygiene;//  |
	protected State moral;  // /
	
	protected State health;
	
	protected boolean takingShower;
	protected boolean eating;
	protected boolean drinking;
	protected boolean playing;
	protected boolean sleeping;
	protected boolean dying;
	
	public Pet(String type) {
		
		this.type = type;
		
		health = new State("health");
		health.setValue(1.0);
		health.setMalus("dying", 1.5);
		health.setBonus("living", 1.5);
		
		takingShower = false;
		eating = false;
		drinking = false;
		playing = false;
	}
	
	public void init(State hunger, State thirst, State weight, State hygiene, State moral) {
		
		this.hunger = hunger;
		this.thirst = thirst;
		this.weight = weight;
		this.hygiene = hygiene;
		this.moral = moral;
		
		setMalusForAllStates("overtime", 0.1);
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
	public boolean isDying() {
		return dying;
	}
	
	public boolean isStinking() {
		return hygiene.getValue() < 0.25;
	}
	public boolean isHungry() {
		return hunger.getValue() < 0.25;
	}
	public boolean isThirsty() {
		return hunger.getValue() < 0.25;
	}
	
	public boolean isDead() {
		
		applyMalus("overtime");
		
		if ( getWeight().getValue() <= 0 &&
			 getThirst().getValue() <= 0 &&
			 getHunger().getValue() <= 0    ) {
			
			dying = true;
			health.applyMalus("dying");
		}
		else {
			dying = false;
			health.applyBonus("living");
		}
		
		return health.getValue() <= 0.0;
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
	public State getHealth() {
		return health;
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
