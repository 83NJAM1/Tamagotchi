package app.controller;

import javafx.animation.AnimationTimer;

public class StateBonusThread extends AnimationTimer {
	
	private boolean isActive;
	
	private long old_time=0;
	private long global_time=0;
	private boolean doonce = false;
	
	private State state;
	private String bonusName;
	private long delay;
	
	public StateBonusThread(State state, String bonusName, long delay) {
		super();
		this.state = state;
		this.bonusName = bonusName;
		this.delay = delay*1_000_000_000;
	}
	
    public void handle(long new_time) {
    	
    	long next_time = new_time+1_000_000_000;
    	
		if (new_time > old_time ) {
			
			
			// esquive la phase d'initialisation sinon valeur aberrante
			if ( doonce ) {
				global_time += next_time-old_time;
			}
			else {
				isActive = true;
				doonce = true;
				System.out.println("Bonus " + bonusName + " for " + state.getModel().getKeyName() + " activated");
			}
			
			// le nouveau temps à attendre
			old_time = next_time;
			
			// effet du bonus
			state.getModel().applyBonus(bonusName);
			
			// fin du bonus
			if ( global_time > delay ) {
				System.out.println( "Bonus " + bonusName + " for " + state.getModel().getKeyName() 
								  + " ended after: " + global_time/1_000_000_000.0 + " seconds");
				this.stop();
				global_time=0;
				old_time=0;
				isActive=false;
				doonce=false;
			}
		}
    }
}
