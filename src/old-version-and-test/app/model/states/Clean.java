package app.model.states;
import app.model.*;

public class Clean extends State{
	
	//Vitesse : Il faut deux jours pour passer de 100% propre à 0%
	private static double speed = -1/(2*24*60*60*1000.);
	
	
	public void pass(Pet pet,double amount) {
		
		//On se salit 8 fois plus vite dans le jardin
		if(pet.isIn(Room.garden)) amount*=8;
		
		//Le chat se nettoie s'il est à moins de 50%
		if(pet.is(Specie.cat)) amount*=value-0.5;
		
		change(amount*speed);
		
		
	}
	


}
