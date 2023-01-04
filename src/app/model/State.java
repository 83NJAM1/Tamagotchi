package app.model;

import java.util.HashMap;

import app.Cleanable;

/**
 * 
 * @author ben
 * Un état représenter par une JAUGE et un NOM
 */
public class State implements Cleanable {
	
	public static final Double BASEFACTOR=0.01;
	
	private String keyName;
	private Double value;
	private HashMap<String,Double> malusFactor;
	private HashMap<String,Double> bonusFactor;
	
	/**
	 * constructeur
	 * @param keyName l'identifiant
	 */
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
	
	/**
	 * définit un bonus pour la state
	 * @param key
	 * @param factor
	 */
	public void setBonus(String key, Double factor) {
		
		if ( factor > 0 ) 
			bonusFactor.put(key, factor);
		else 
			System.err.println("must be a positive factor");
	}
	
	/**
	 * définit un malus pour la state
	 * @param key
	 * @param factor
	 */
	public void setMalus(String key, Double factor) {
		
		if ( factor > 0 )
			malusFactor.put(key, factor);
		else
			System.err.println("must be a positive factor");
	}
	
	/**
	 * applique le malus
	 * @param key
	 * @return
	 */
	public Double applyMalus(String key) {
		
		if ( malusFactor.containsKey(key) )
			value-=BASEFACTOR*malusFactor.get(key);
		
		if ( value < 0.0 )
			value = 0.0;
		
		return value;
	}
	
	/**
	 * applique le bonus
	 * @param key
	 * @return
	 */
	public Double applyBonus(String key) {
		
		if ( bonusFactor.containsKey(key) )
			value+=BASEFACTOR*bonusFactor.get(key);
		
		if ( value > 1.0 )
			value = 1.0;
		
		return value;
	}
	
	@Override
	public String toString() {
		return keyName + " : " + value;
	}
	
	@Override
	public void clean() {
		
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
