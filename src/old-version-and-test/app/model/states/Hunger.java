package app.model.states;
import app.model.*;

public class Hunger extends State{
	
	//Vitesse : Il faut 1 jour pour être affammé
	private static double speed = -1/(24*60*60*1000.);

	
	public void pass(Pet pet, double amount) {
		//L'appétit diminue proportionelement à la gravité de la maladie du pet
		amount*=pet.sick().getValue();
		change(amount*speed);
	}
	
	//Etre affammé est critique et fait perdre des points de vie
	public boolean isDamaging() {
		return value<=0;
	}


}
