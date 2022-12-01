package app.model;

/**
 * 
 * @author ben
 * Le compagnon à prendre soin
 */
public abstract class Pet {
	public abstract void setHunger(Stat hunger);
	public abstract void setThirst(Stat thirst);
	public abstract void setWeight(Stat weight);
	public abstract void setHygiene(Stat hygiene);
	public abstract void setMoral(Stat moral);
	
	public abstract Stat getHunger();
	public abstract Stat getThirst();
	public abstract Stat getWeight();
	public abstract Stat getHygiene();
	public abstract Stat getMoral();
	
	public String toString() {
		String output = getHunger().toString() + System.lineSeparator()
		  			  + getThirst().toString() + System.lineSeparator()
		  			  + getWeight().toString() + System.lineSeparator()
		  			  + getHygiene().toString() + System.lineSeparator()
		  			  + getMoral().toString() + System.lineSeparator();
		return output;
	}
}
