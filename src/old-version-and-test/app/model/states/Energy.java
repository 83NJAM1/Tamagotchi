package app.model.states;
import app.model.*;

public class Energy extends State{
	
	//Vitesse : Il faut une heure pour perdre toute son energie
	private static double speed = -1/(1*60*60*1000.);
	
	public void pass(Pet pet, double amount) {
		
		//On multiplie ça par le nombre d'heures d'activité des animaux : un chat dormira 16 heures et sera eveillé 8 heures
		switch(pet.getSpecie()) {
			case cat : amount/=8;
				break;
			case dog : amount/=13;
				break;
			case rabbit : amount/=17;
				break;
			case robot : amount/=0.5;
				break;
	
		}
		
		//Lorsque l'animal est malade, l'energie max et le gain d'energie est limité en fonction de la gravité de la maladie.
		amount*=Math.max(1,pet.sick().getValue()+1-Math.max(0, value));
		
		change(amount*speed);
	}


}
