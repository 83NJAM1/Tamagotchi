package app.model;
 
/**
 * 
 * @author ben
 * Un état représenter par une JAUGE et un NOM
 */
public class Stat {
	public static final Double changeValue=0.01;
	
	private String keyName;
	private Double value;
	private Double malusFactor;
	private Double bonusFactor;
	 
	public Stat(String keyName) {
		this.keyName = keyName;
		value = 0.5;
		malusFactor = 1.0;
		bonusFactor = 1.0;
	}
	
	public String getKeyName() {
		return keyName;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
	public boolean setBonus(Double factor) {
		
		if ( factor > 0 ) 
			this.bonusFactor = factor;
		else 
			System.err.println("Bonuses must be a positive factor");
		
		return factor>0;
	}
	
	public boolean setMalus(Double factor) {
		
		if ( factor > 0 )
			this.malusFactor = factor;
		else
			System.err.println("Maluses must be a positive factor");
		
		return factor>0;
	}
	
	public Double dec() {
		return value-=changeValue*malusFactor;
	}
	
	public Double inc() {
		return value+=changeValue*bonusFactor;
	}
	
	public String toString() {
		return keyName + " : " + value;
	}
}
