package app.model.states;
import app.model.*;

public class Weight extends State{
	
	//Le temps n'influe pas directement sur le poids
	private static double speed = 0;
	
	
	public void pass(Pet pet, double amount) {
		change(amount*speed);
	}
	
	//Le poids n'est pas limité.
	public void contain() {
		value=Math.max(value, 0);
	}

}
