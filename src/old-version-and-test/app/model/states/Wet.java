package app.model.states;
import app.model.*;

public class Wet extends State{
	
	//Il faut 1 heure pour se secher après avoir été mouillé à 100%
	private static double speedDry = 1/(60*60*1000.);
	//Il faut 3 minutes pour être mouillé à 100% sous la pluie
	private static double speedWet = -1/(3*60*1000.);
	
	
	public void pass(Pet pet, double amount) {
		
		if(pet.isOutside()) {
			//Ajustement du mouillage en fonction de l'intensité de la pluie
			amount*=speedWet*pet.getWeather().getWet();
			//Le robot se mouille 10 fois plus vite
			if(pet.is(Specie.robot)) amount*=10;
		}
		else {
			amount*=speedDry;
			//On se seche 5 fois plus vite auprès du feu
			if(pet.isIn(Room.livingRoom)) amount*=5;
		}
		
		change(amount);
	}
	

}
