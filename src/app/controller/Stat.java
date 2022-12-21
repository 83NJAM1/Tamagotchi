package app.controller;

import javafx.animation.AnimationTimer;

import app.App;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le statModel
 */
public class Stat {
	
	//########################### ATTRIBUTS #####################################
 
	// ATTENTION: Reference partagé avec model.Pet
	private app.model.Stat statModel;
	
	// ATTENTION: Reference partagé avec view.Hud + view.Pet
	private app.view.Stat statView;
	
	// Bonus
	private long bonusTime; // en nanosecondes
	private String bonusName;
	private boolean bonusIsActivate;
	
	//######################### EVENT-ACTION ####################################

	/**
	 * Action effectué toute les 1 seconde (1e+9 ns), s'arrête après bonusTime secondes
	 * déclencheur -> this
	 */ 
	private AnimationTimer activeBonus = new AnimationTimer() {
		
		long old_time=0;
		long global_time=0;
		boolean doonce = false;
		
        public void handle(long new_time) {
        	
        	long next_time = new_time+1_000_000_000;
        	
			if (new_time > old_time ) {
				
				
				// esquive la phase d'initialisation sinon valeur aberrante
				if ( doonce ) {
					global_time += next_time-old_time;
				}
				else {
					doonce = true;
					System.out.println("Bonus " + bonusName + " for " + statModel.getKeyName() + " activated");
				}
				
				// le nouveau temps à attendre
				old_time = next_time;
				
				// effet du bonus
				increaseValue();
				
				// fin du bonus
				if ( global_time > bonusTime ) {
					System.out.println( "Bonus " + bonusName + " for " + statModel.getKeyName() 
									  + " ended after: " + global_time/1_000_000_000.0 + " seconds");
					this.stop();
					global_time=0;
					old_time=0;
					bonusIsActivate=false;
					doonce=false;
				}
			}
        }
    };
    
	//############################ METHODES #####################################
	
	public Stat(String name) {
		statModel = new app.model.Stat(name);
		statView = new app.view.Stat(App.getString(name));
		bonusTime=0;
	}
	
	public void updateText() {
		statView.updateText(statModel.getKeyName());
	}
	
	public void decreaseValue() {
		statModel.dec();
		statView.updateValue(statModel.getValue());
	}
	
	public void increaseValue() {
		statModel.inc();
		statView.updateValue(statModel.getValue());
	}
	
	public void applyBonus(Double factor, long seconds, String name) {
		
		if ( !bonusIsActivate && statModel.setBonus(factor) ) {
			
			bonusTime = seconds*1_000_000_000;
			bonusName = name;
			bonusIsActivate = true;
			
			activeBonus.start();
		}
		else {
			
			System.err.println("Bonuses are not stackable: imposible to apply \"" + name + "\"");
		}
	}
	
	public void setValue(Double value) {
		statModel.setValue(value);
		statView.updateValue(value);
	}
	
	public app.model.Stat getModel() {
		return statModel;
	}
	
	public app.view.Stat getView() {
		return statView;
	}
	
	public void exit() {
		activeBonus.stop();
		activeBonus = null;
		statModel = null;
		statView = null;
	}
}
