package app.controller;

import javafx.animation.AnimationTimer;

import app.App;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Stat {
	
	//########################### ATTRIBUTS #####################################
 
	// ATTENTION: Reference partagé avec model.Pet
	private app.model.Stat model;
	
	// ATTENTION: Reference partagé avec view.Hud
	private app.view.Stat view;
	
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
					System.out.println("Bonus " + bonusName + " for " + model.getKeyName() + " activated");
				}
				
				// le nouveau temps à attendre
				old_time = next_time;
				
				// effet du bonus
				increaseValue();
				
				// fin du bonus
				if ( global_time > bonusTime ) {
					System.out.println( "Bonus " + bonusName + " for " + model.getKeyName() 
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
		model = new app.model.Stat(name);
		view = new app.view.Stat(App.language.getString(name));
		bonusTime=0;
	}
	
	public void updateText() {
		view.updateText(model.getKeyName());
	}
	
	public void decreaseValue() {
		model.dec();
		view.updateValue(model.getValue());
	}
	
	public void increaseValue() {
		model.inc();
		view.updateValue(model.getValue());
	}
	
	public void applyBonus(Double factor, long seconds, String name) {
		
		if ( !bonusIsActivate && model.setBonus(factor) ) {
			
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
		model.setValue(value);
		view.updateValue(value);
	}
	
	public app.model.Stat getModel() {
		return model;
	}
	
	public app.view.Stat getView() {
		return view;
	}
	
	public void exit() {
		activeBonus.stop();
		activeBonus=null;
		model = null;
		view = null;
	}
}
