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
	
	// le controller du pet à manipuler
	private Pet pet;
	
	// le controller de la pièce active
	private Room room;
	
	// gestion des données
	private app.model.Save save;
	 
	// lié à c.Main
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
    
	/**
	 * ActionLoop effectué pour aller dans la pièce A ( kitchen )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoKitchen = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("kitchen"));
			checkRoomAllowedAction();
		}
	};
	
	/**
	 * ActionLoop effectué pour aller dans la pièce A ( kitchen )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoGarden = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("garden"));
			checkRoomAllowedAction();
		}
	};
	
	/**
	 * ActionLoop effectué pour aller dans la pièce B ( livingroom )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoBathroom = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("bathroom"));
			checkRoomAllowedAction();
		}
	};
	
	/**
	 * ActionLoop effectué pour aller dans la pièce C ( test )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoLivingroom = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("test"));
			checkRoomAllowedAction();
		}
	};
	
	//############################ METHODES #####################################
	
	/*
	 * Constructeur nouvelle partie
	 */
	public Game(String petType, String roomName, String saveName) {
		
		save = new app.model.Save("res/"+saveName);
		
		pet = new Pet(petType);
		room = new Room(roomName);
		model = new app.model.Game( pet.getModel(), room.getModel() );
		view = new app.view.Game( pet.getView(), room.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		
		init();
	}
	
	/*
	 * Constructeur charger partie
	 */
	public Game(String saveName) {
		
		save = new app.model.Save("res/"+saveName);
		save.load("res/"+saveName);
		pet = new Pet(save.getPetType());
		room = new Room(save.getRoomId());
		model = new app.model.Game( pet.getModel(), room.getModel() );
		view = new app.view.Game( pet.getView(), room.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		
		pet.getHunger().setValue(save.getStat("hunger"));
		pet.getThirst().setValue(save.getStat("thirst"));
		pet.getWeight().setValue(save.getStat("weight"));
		pet.getHygiene().setValue(save.getStat("hygiene"));
		pet.getMoral().setValue(save.getStat("moral"));
		
		init();
	}
	
	/*
	 * Initialisation commune entre constructeurs
	 */
	private void init() {
		
		save.setGameInstance(model);
		
		view.getActionBar().setActionButKitchen(gotoKitchen);
		view.getActionBar().setActionButBathroom(gotoBathroom);
		view.getActionBar().setActionButLivingroom(gotoLivingroom);
		view.getActionBar().setActionButGarden(gotoGarden);
		
		checkRoomAllowedAction();
		actionLoop.start();
	}
	
	public void save() {
		save.save();
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
			pet.setDead();
			return true;
		}
		
		return false;
	}
	
	public void checkRoomAllowedAction() {
		switch (room.getModel().toString()) {
			case "kitchen":
				view.getActionBar().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "livingroom":
				view.getActionBar().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "garden":
				view.getActionBar().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "test":
				view.getActionBar().setAllowedButtons(true, true, true, true, false, true);
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
		save = null;
		view = null;
		model = null;
		pet = null;
		room = null;
		actionLoop = null;
	}
	
}
