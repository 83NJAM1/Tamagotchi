package app.model;

import java.util.HashMap;

import app.Componable;

/**
 * 
 * @author ben
 * Un état représenter par une JAUGE et un NOM
 */
public class State implements Componable {
	
	public static final Double BASEFACTOR=0.001;
	
	private String keyName;
	private Double value;
	private HashMap<String,Double> malusFactor;
	private HashMap<String,Double> bonusFactor;
	 
	public State(String keyName) {
		this.keyName = keyName;
		value = 0.5;
		malusFactor = new HashMap<>();
		bonusFactor = new HashMap<>();
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
	
	public void setBonus(String key, Double factor) {
		
		if ( factor > 0 ) 
			bonusFactor.put(key, factor);
		else 
			System.err.println("must be a positive factor");
	}
	
	public void setMalus(String key, Double factor) {
		
		if ( factor > 0 )
			malusFactor.put(key, factor);
		else
			System.err.println("must be a positive factor");
	}
	
	public Double applyMalus(String key) {
		
		if ( malusFactor.containsKey(key) )
			return value-=BASEFACTOR*malusFactor.get(key);
		
		return value;
	}
	
	public Double applyBonus(String key) {
		
		if ( bonusFactor.containsKey(key) )
			return value+=BASEFACTOR*bonusFactor.get(key);
		
		return value;
	}
	
	@Override
	public String toString() {
		return keyName + " : " + value;
	}
	
	@Override
	public void exit() {
		
		if ( malusFactor != null ) {
			malusFactor.clear();
			malusFactor = null;
		}
		if ( bonusFactor != null ) {
			bonusFactor.clear();
			bonusFactor = null;
		}
	}
}
