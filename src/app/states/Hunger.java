package app.states;
import app.model.*;

public class Hunger extends State{
	
	private static double speed = -1/(24*60*60*1000.);
	
	public Hunger(Pet pet) {
		super(pet);
		text=Text.hunger;
	}
	
	public void pass(double amount) {
		amount*=pet.sick().getValue();
		change(amount*speed);
	}
	
	public boolean isDamaging() {
		return value<=0;
	}


}
