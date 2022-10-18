package app.states;
import app.model.*;

public class Sick extends State{
	
	private static double speed = 1/(1*24*60*60*1000.);
	
	public Sick(Pet pet) {
		super(pet);
		text=Text.sick;
	}
	
	public void pass(double amount) {
		change(amount*speed);
	}
	
	public boolean isDamaging() {
		return value<1;
	}

}
