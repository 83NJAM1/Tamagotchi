package app.model.states;
import app.model.*;

public class Sick extends State{
	
	//Vitesse : Il faut un jour pour guérir completement
	private static double speed = 1/(1*24*60*60*1000.);

	
	public void pass(Pet pet, double amount) {
		change(amount*speed);
	}
	
	//Etre malade est critique et fait perdre des points de vie	
	public boolean isDamaging() {
		return value<1;
	}

}
