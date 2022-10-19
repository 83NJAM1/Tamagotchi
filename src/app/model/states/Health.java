package app.model.states;
import app.model.*;

public class Health extends State{
	
	private static double speed = -1/(24*60*60*1000.);
	
	public Health(Pet pet) {
		super(pet);
		text=Text.health;
	}
	
	public void pass(double amount) {
		
		boolean fine = true;
		for(State state : pet.getStateList())
			if(state.isDamaging()) {
				fine = false;
				change(amount*speed);
			}
		
		if(fine) {
			if(pet.isSleeping()) amount*=2;
			amount*=pet.moral().getValue();
			change(-amount*speed);
		}
	}
	


}
