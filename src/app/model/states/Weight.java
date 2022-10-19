package app.model.states;
import app.model.*;

public class Weight extends State{
	
	private static double speed = 0;
	
	public Weight(Pet pet) {
		super(pet);
		text=Text.weight;
	}
	
	public void pass(double amount) {
		change(amount*speed);
	}
	

	public void contain() {
		value=Math.max(value, 0);
	}

}
