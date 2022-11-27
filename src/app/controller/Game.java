package app.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

/**
 * 
 * @author ben
 * permet de faire intéragire l'ensemble du jeu avec les actions à associer
 */
public class Game {
	
	//########################### ATTRIBUTS #####################################

	private app.model.Game model;
	
	//ATTENTION: reference partagé avec view.Main
	private app.view.Game view;
	
	// le pet a manipuler
	private Pet pet;
	
	
	private Room room;
	
	public BooleanProperty gameover;
	
	//######################### EVENT-ACTION ####################################

	/**
	 * ActionLoop effectué toute les 1 second (1e+9 ns)
	 * déclendeur -> this
	 */ 
	private AnimationTimer actionLoop = new AnimationTimer() {
		long old_time=0;
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+(1<<30); // aproximativement une seconde
				
				updateGame();
			}
        }
    };
	
	//############################ METHODES #####################################
	
	public Game() {
		pet = new Pet();
		room = new Room();
		model = new app.model.Game( pet.getModel(), room.getModel() );
		view = new app.view.Game( pet.getView(), room.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		
		actionLoop.start();
	}
	
	public void updateGame() {
		pet.statsDecreaseOvertime();
		
		if ( pet.getHygiene().getModel().getValue() < 0.48 ) {
			pet.getHygiene().applyBonus(3.0, 10);
		}
		if ( pet.getHunger().getModel().getValue() < 0.0 ) {
			view.startDrawingGameOver();
			actionLoop.stop();
			gameover.setValue(true);
		}
	}
	
	public Pet getPet() {
		return pet;
	}
	
	public app.view.Game getView() {
		return view;
	}
	
	public app.model.Game getModel() {
		return model;
	}
	
	public void exit() {
		actionLoop.stop();
		pet.exit();
		room.exit();
		view = null;
		model = null;
		pet = null;
		room = null;
		actionLoop = null;
	}
	
}
