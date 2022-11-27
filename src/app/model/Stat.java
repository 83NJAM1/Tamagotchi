package app.model;

/**
 * 
 * @author ben
 * Un état représenter par une JAUGE et un NOM
 */
public class Stat {
	private static final Double changeValue=0.01;
	
	private String keyName;
	private Double value;
	private Double negFactor;
	private Double posFactor;
	
	public Stat(String keyName) {
		this.keyName = keyName;
		value = 0.5;
		negFactor = 1.0;
		posFactor = 1.0;
	}
	
	public String getKeyName() {
		return keyName;
	}
	
	public Double getValue() {
		return value;
	}
	
	public void setPosFactor(Double factor) {
		this.posFactor = factor;
	}
	public void setNegFactor(Double factor) {
		this.negFactor = factor;
	}
	
	public Double dec() {
		return value-=changeValue*negFactor;
	}
	public Double inc() {
		return value+=changeValue*posFactor;
	}
}
