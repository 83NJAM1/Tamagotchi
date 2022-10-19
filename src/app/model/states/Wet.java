package app.model.states;
import app.model.*;

public class Wet extends State{
	
	private static double speedDry = 1/(60*60*1000.);
	private static double speedWet = -1/(3*60*1000.);
	
	public Wet(Pet pet) {
		super(pet);
		text=Text.wet;
	}
	
	public void pass(double amount) {
		
		if(pet.isOutside()) {
			amount*=speedWet*pet.getWeather().getWet();
			if(pet.is(Specie.robot)) amount*=10;
		}
		else {
			amount*=speedDry;
			if(pet.isIn(Room.livingRoom)) amount*=5;
		}
		
		change(amount);
	}
	

}
