package app.model;

import java.util.Random; 

public class ThrowAndFetch extends MiniGame {

	public final static Double MAXDISTANCE = 50.0;
	
	Pet pet;
	
	Double objectDistance;
	Random generateDistance;
	boolean throwOnce;
	String info;
	
	public ThrowAndFetch(Pet pet) {
		super("throw-and-fetch");
		this.pet = pet;
		throwOnce = false;
		generateDistance = new Random();
		objectDistance = -MAXDISTANCE;
	}
	
	public Double getDistance() {
		return objectDistance;
	}
	public Double getProgress() {
		
		System.out.println(objectDistance/MAXDISTANCE);
		
		if ( pet.wantPlay() ) {
			
			return Math.abs(objectDistance/MAXDISTANCE);
		}
		
		return 1.0;
	}
	
	public void throwObject() {
		
		if ( objectDistance <= -MAXDISTANCE || objectDistance >= MAXDISTANCE ) {
			
			objectDistance = generateDistance.nextDouble(-MAXDISTANCE, 0.0);
			info = "you throw the object to " + objectDistance; 
		}
	}
	
	@Override
	public boolean nextStep() {
				
		if ( pet.wantPlay() && ( objectDistance < MAXDISTANCE && objectDistance > -MAXDISTANCE ) ) {
			
			objectDistance += 5.0;
			
			if ( objectDistance <= 0.0 )
				info = "the " + pet.getType() + " try to catch the object";
			else if ( objectDistance < MAXDISTANCE )
				info = "the " + pet.getType() + " is fetching you the object";
			
			pet.setCatch(false);
			pet.setFetch(true);
			
			return true;
		}
		else if ( objectDistance <= -MAXDISTANCE || objectDistance >= MAXDISTANCE ) {
			
			pet.setCatch(true);
			pet.setFetch(false);
			info = "you can throw the object";
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public String getInfo() {
		return info;
	}
}
