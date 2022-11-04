package app.model.states;
import app.model.*;

public class Thirst extends State{
	
	//Vitesse : Il faut un jour pour être assoifé
	private static double speed = -1/(1*24*60*60*1000.);
	
	
	public void pass(Pet pet, double amount) {
		//Etre malade donne plus soif
		amount*=2-pet.sick().getValue();
		change(amount*speed);
	}

	//Etre assoifé est critique et fait perdre des points de vie
	public boolean isDamaging() {
		return value<=0;
	}
}
