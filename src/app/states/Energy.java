package app.states;
import app.model.*;

public class Energy extends State{
	
	private static double speed = -1/(1*60*60*1000.);
	
	public Energy(Pet pet) {
		super(pet);
		text=Text.energy;
	}
	
	public void pass(double amount) {
		
		switch(pet.getSpecie()) {
			case cat : amount/=8;
				break;
			case dog : amount/=13;
				break;
			case rabbit : amount/=17;
				break;
			case robot : amount/=0.5;
				break;
	
		}
		
		change(amount*speed);
	}
	
	public void contain() {
		value=Math.max(Math.min(value, pet.sick().getValue()), 0);
	}

}
