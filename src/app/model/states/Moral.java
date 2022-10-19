package app.model.states;
import app.model.*;

public class Moral extends State{
	
	private static double speed = -1/(7*24*60*60*1000.);
	
	public Moral(Pet pet) {
		super(pet);
		text=Text.moral;
	}
	
	public void pass(double amount) {
		
		if(!pet.isSleeping())
			if(pet.getAloneTime()<5000 && !pet.isDoingNothing())
				amount=-10*amount;

		if(pet.getWeather().getWet()>0)
			amount*=amount>0?0.5:2;
		else if(pet.getWeather().getWet()<0)
			amount*=amount>0?2:0.5;
			
		change(amount*speed);
	}
	
	public boolean isDamaging() {
		return value<=0;
	}

	public void contain() {
		value=Math.max(Math.min(value, pet.sick().getValue()), 0);
	}
	
}
