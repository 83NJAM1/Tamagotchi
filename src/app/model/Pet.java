package app.model;

/**
 * 
 * @author ben
 * Le compagnon à prendre soin
 */
public interface Pet {
	public void setHunger(Stat hunger);
	public void setThirst(Stat thirst);
	public void setWeight(Stat weight);
	public void setHygiene(Stat hygiene);
	public void setMoral(Stat moral);
	
	public Stat getHunger();
	public Stat getThirst();
	public Stat getWeight();
	public Stat getHygiene();
	public Stat getMoral();
}
