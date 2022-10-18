package app.states;
import app.model.*;

public class Clean extends State{
	
	private static double speed = -1/(2*24*60*60*1000.);
	
	public Clean(Pet pet) {
		super(pet);
		text=Text.clean;
	}
	
	public void pass(double amount) {
		
		if(pet.isIn(Room.garden)) amount*=8;
		if(pet.is(Specie.cat)) amount*=value-0.5;
		
		change(amount*speed);
		
		
	}
	


}
