package app.controller;

import javafx.animation.AnimationTimer;

import app.Reinstanciable;
import app.TextDisplayable;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le statModel
 */
public class State implements Reinstanciable, TextDisplayable {
	
	//########################### ATTRIBUTS #####################################
 
	// données de l'état
	private app.model.State stateModel; //NOTE: Reference partagé avec model.Pet
	
	// affichage de l'état
	private app.view.State stateView; //NOTE: Reference partagé avec view.Hud + view.Pet
	
	// bonus, augmente la valeur
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
					System.out.println("Bonus " + bonusName + " for " + stateModel.getKeyName() + " activated");
				}
				
				// le nouveau temps à attendre
				old_time = next_time;
				
				// effet du bonus
				increaseValue();
				
				// fin du bonus
				if ( global_time > bonusTime ) {
					System.out.println( "Bonus " + bonusName + " for " + stateModel.getKeyName() 
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
	
    /**
     * constructeur
     * @param name la chaîne de charactère identifiant l'état
     */
	public State(String key) {
		stateModel = new app.model.State(key);
		stateView = new app.view.State(key);
		bonusTime=0;
	}
	
	/**
	 * decremente la valeur via le model
	 * met à jour l'affiche via la vue
	 */
	public void decreaseValue() {
		stateModel.dec();
		stateView.changeValue(stateModel.getValue());
	}
	
	/**
	 * incremente la valeur via le model
	 * met à jour l'affiche via la vue
	 */
	public void increaseValue() {
		stateModel.inc();
		stateView.changeValue(stateModel.getValue());
	}
	
	/**
	 * applique un bonus à l'état
	 * @param factor plus la valeur est haute plus le bonus est important
	 * @param seconds la durée du bonus
	 * @param name l'identifiant du bonus
	 */
	public void applyBonus(Double factor, long seconds, String name) {
		
		if ( !bonusIsActivate && stateModel.setBonus(factor) ) {
			
			bonusTime = seconds*1_000_000_000;
			bonusName = name;
			bonusIsActivate = true;
			
			activeBonus.start();
		}
		else {
			
			System.err.println("Bonuses are not stackable: imposible to apply \"" + name + "\"");
		}
	}
	
	/**
	 * change la valeur de l'état
	 * @param value la valeur souhaité
	 */
	public void setValue(Double value) {
		stateModel.setValue(value);
		stateView.changeValue(value);
	}
	
	/**
	 * obtient le model State
	 * @return State, le model
	 */
	public app.model.State getModel() {
		return stateModel;
	}
	
	/**
	 * obtient la vue State
	 * @return State, la vue
	 */
	public app.view.State getView() {
		return stateView;
	}
	
	@Override
	public void updateText() {
		stateView.updateText();
	}
	
	@Override
	public void exit() {
		activeBonus.stop();
		activeBonus = null;
		
		stateModel = null;
		
		stateView.exit();
		stateView = null;
	}
}
