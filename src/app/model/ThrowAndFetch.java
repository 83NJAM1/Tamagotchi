package app.model;

import java.util.Random; 

public class ThrowAndFetch extends MiniGame {

	public final static Double MAXDISTANCE = 100.0;
	
	Pet pet;
	
	Double stickDistance;
	Random generateDistance;
	String info;
	
	public ThrowAndFetch(Pet pet) {
		super("throw-and-fetch");
		this.pet = pet;
		generateDistance = new Random();
		stickDistance = 0.0;
	}
	
	private boolean petWantContinue() {
		
		if ( pet.isHungry() || pet.isThirsty() ) {
			
			pet.tooglePlaying();
			return false;
		}
		
		return true;
	}
	
	public Double getDistance() {
		return stickDistance;
	}
	
	public void throwStick() {
		
		if ( stickDistance <= 0 ) {
			
			stickDistance = generateDistance.nextDouble(0.0, MAXDISTANCE);
			info = "you throw the stick to " + stickDistance; 
		}
	}
	
	@Override
	public boolean nextStep() {
		
		if ( !pet.getType().equals("dog") ) {
			
			info = "the " + pet.getType() + " don't undestand";
			return false;
		}
		else {
			
			if ( petWantContinue() && stickDistance > 0 ) {
				
				stickDistance -= 5.0;
				info = "the " + pet.getType() + " is fetching you the stick";
				return true;
			}
			else if (stickDistance <= 0) {
				
				info = "you can throw the stick";
				return false;
			}
			else {
				
				info = "the " + pet.getType() + " want to stop";
				return false;
			}
			
		}
	}
	
	@Override
	public String getInfo() {
		return info;
	}
}
