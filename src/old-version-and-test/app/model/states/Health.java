package app.model.states;
import app.model.*;

public class Health extends State{
	
	//Vitesse : Il faut 1 jour pour regagner sa vie
	private static double speed = -1/(24*60*60*1000.);
	
	
	public void pass(Pet pet, double amount) {
		
		//Perte de vie en cas d'états critiques
		boolean fine = true;
		for(State state : pet.getStateList())
			if(state.isDamaging()) {
				fine = false;
				change(amount*speed);
			}
		
		
		if(fine) {
			//Gain de vie multiplié par deux en dormant
			if(pet.isSleeping()) amount*=2;
			//Le gain de vie proportionel au moral
			amount*=pet.moral().getValue();
			change(-amount*speed);
		}
	}
	


}
