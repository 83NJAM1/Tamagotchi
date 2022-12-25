package app.model;

import java.util.Random; 

public class ThrowStick extends MiniGame {

	public final static Double MAXDISTANCE = 100.0;
	
	Pet pet;
	
	Double stickDistance;
	Random generateDistance;
	String info;
	
	public ThrowStick(Pet pet) {
		super("thorw-stick");
		this.pet = pet;
		generateDistance = new Random();
		stickDistance = 0.0;
	}
	
	private boolean petWantContinue() {
		
		if ( pet.getHunger().getValue() < 0.2 ||
			 pet.getThirst().getValue() < 0.3 ||
			 pet.getWeight().getValue() < 0.1 ) {
			
			info = "the pet want to stop";
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
		
		if ( stickDistance > 0 ) {
			stickDistance -= 5.0;
			info = "the pet is fetching you the stick";
			return petWantContinue();
		}
		else {
			info = "you can throw the stick";
			return false;
		}
		
	}
	
	@Override
	public String getInfo() {
		return info;
	}
}
