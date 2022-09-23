package app.useless;

public class Characteristic {
	
	private double value;
	
	public Characteristic(double value) {
		
		this.value = value; 
	}
	
	public double getValue() {
		
		return value;
	}
	
	public void setValue(double new_value) {
		
		value = new_value;
	}
	
	public void autoDecrement() {
		
		value = value - 0.001;
	}
}
