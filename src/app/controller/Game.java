package app.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
 
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
	
	// le pet à manipuler
	private Pet pet;
	
	// la pièce active
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
				old_time = new_time+1_000_000_000;

				updateGame();
			}
        }
    };
    
	private EventHandler<ActionEvent> gotoRoomA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("kitchen"));
			checkRoomAllowedAction();
		}
	};
	
	private EventHandler<ActionEvent> gotoRoomB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("livingroom"));
			checkRoomAllowedAction();
		}
	};
	
	private EventHandler<ActionEvent> gotoRoomC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("test"));
			checkRoomAllowedAction();
		}
	};
	
	//############################ METHODES #####################################
	
	public Game(String petType, String roomName) {
		
		pet = new Pet(petType);
		room = new Room(roomName);
		model = new app.model.Game( pet.getModel(), room.getModel() );
		view = new app.view.Game( pet.getView(), room.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		
		view.getActionBar().setActionRoomA(gotoRoomA);
		view.getActionBar().setActionRoomB(gotoRoomB);
		view.getActionBar().setActionRoomC(gotoRoomC);
		
		checkRoomAllowedAction();
		actionLoop.start();
	}
	
	public void setPet(Pet new_pet) {
		pet = new_pet;
	}
	public void setRoom(Room new_room) {
		room = new_room;
		model.setRoom(room.getModel());
		view.setRoom(room.getView());
	}
	
	public void updateGame() {
		
		pet.statsDecreaseOvertime();
		
		if ( pet.getHygiene().getModel().getValue() < 0.48 )
			pet.getHygiene().applyBonus(3.0, 10, "shower");
		if ( pet.getHygiene().getModel().getValue() < 0.48 )
			pet.getHygiene().applyBonus(2.0, 5, "brushing teeth");
		
		if ( isGameover() ) {
			view.startDrawingGameOver();
			actionLoop.stop();
		}
	}
	
	public boolean isGameover() {
		
		if ( pet.getHunger().getModel().getValue() < 0.0 
		  || pet.getThirst().getModel().getValue() < 0.0
		  || pet.getWeight().getModel().getValue() < 0.0
		  || pet.getMoral().getModel().getValue()  < 0.0 ) 
		{
			gameover.setValue(true);
			return true;
		}
		
		return false;
	}
	
	public void checkRoomAllowedAction() {
		switch (room.getModel().toString()) {
			case "kitchen":
				view.getActionBar().setActive(true, true, false, false, true, true);
				break;
			case "livingroom":
				view.getActionBar().setActive(true, true, false, false, true, true);
				break;
			case "test":
				view.getActionBar().setActive(true, true, true, true, false, true);
				break;
			default:
				break;
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
