package app.controller;

import app.App;
import javafx.animation.AnimationTimer;

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
	
	private int bonusTime; 
	//######################### EVENT-ACTION ####################################

	/**
	 * activeBonus effectué toute les 1 second (1e+9 ns) s'arrte après 
	 * déclendeur -> this
	 */ 
	private AnimationTimer activeBonus = new AnimationTimer() {
		long old_time=0;
		double global_time=0;
		boolean doonce = false;
        public void handle(long new_time) {
			if (new_time > old_time ) {
				
				if ( doonce ) {
					global_time += ((new_time+(1<<30)-old_time)/1_000_000_000.0);
				}
				else {
					doonce = true;
				}
				old_time = new_time+(1<<30); // aproximativement une seconde
				//save.addData("Accès n°" + n_access++ + "\n");
				//System.out.print(save.getData("test")); // ne pas utiliser println pour des raisons obscure
				System.out.println("bonus "+ model.getKeyName() +" actif since: "+global_time);
				
				increaseValue();
				
				if ( global_time > bonusTime ) {
					this.stop();
					global_time=0;
					old_time=0;
					doonce=false;
					System.out.println("bonus ended");
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
	
	public void applyBonus(Double factor, int time) {
		bonusTime = time;
		model.setPosFactor(factor);
		activeBonus.start();
	}
	
	public app.model.Stat getModel() {
		return model;
	}
	
	public app.view.Stat getView() {
		return view;
	}
	
	public void exit() {
		activeBonus.stop();
		model = null;
		view = null;
	}
}
