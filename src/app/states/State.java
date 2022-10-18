package app.states;
import app.model.*;

public abstract class State {
	
	protected Text text;
	
	protected Pet pet;
	protected double value;
	
	public State(Pet pet) {
		this.pet=pet;
		this.value=1;
	}
	
	public abstract void pass(double interval);
		
	public void change(double d) {
		value+=d;
		contain();
	}
	
	public void contain() {
		value=Math.max(Math.min(value, 1), 0);
	}
	
	public boolean isDamaging() {
		return false;
	}
		
	public Text getText() {
		return text;
	}
	
	public double getValue() {
		return value;
	}
	
}
