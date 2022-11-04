package app.model.states;
import app.model.*;

public class Moral extends State{
	
	//Vitesse : Il faut 7 jours de solitude au pet pour être déprimé
	private static double speed = -1/(7*24*60*60*1000.);
	

	public void pass(Pet pet, double amount) {
		
		//Si le pet n'est pas seul, il regagne 10 fois le moral qu'il aurait perdu en étant seul
		if(!pet.isSleeping())
			if(pet.getAloneTime()<5000 && !pet.isDoingNothing())
				amount=-10*amount;
		
		//Mauvais temps
		if(pet.getWeather().getWet()>0)
			amount*=amount>0?0.5:2;
		//Beau temps
		else if(pet.getWeather().getWet()<0)
			amount*=amount>0?2:0.5;
		
		//Le moral grimpe moins vite et est limité proportionelement à la gravité de la maladie
		amount*=Math.max(1,pet.sick().getValue()+1-Math.max(0, value));
			
		change(amount*speed);
	}
	
	//Etre déprimé est critique et fait perdre des points de vie
	public boolean isDamaging() {
		return value<=0;
	}

	
}
