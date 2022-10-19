package app.model.states;
import app.model.*;

public class Thirst extends State{
	
	private static double speed = -1/(1*24*60*60*1000.);
	
	public Thirst(Pet pet) {
		super(pet);
		text=Text.thirst;
	}
	
	public void pass(double amount) {
		amount*=2-pet.sick().getValue();
		change(amount*speed);
	}
	
	public boolean isDamaging() {
		return value<=0;
	}
}
