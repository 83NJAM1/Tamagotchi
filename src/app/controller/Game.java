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

	private app.model.Game gameModel;
	
	//ATTENTION: reference partagé avec gameView.Main
	private app.view.Game gameView;
	
	// le controller du petController à manipuler
	private Pet petController;
	
	// le controller de la pièce active
	private Room roomController;
	
	// gestion des données
	private app.model.Save saveModel;
	 
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
	 * ActionLoop effectué pour aller dans la pièce B ( livingroomController )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoBathroomController = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("bathroomController"));
			checkRoomAllowedAction();
		}
	};
	
	/**
	 * ActionLoop effectué pour aller dans la pièce C ( test )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoLivingroomController = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setRoom(new Room("test"));
			checkRoomAllowedAction();
		}
	};
	
	//############################ METHODES #####################################
	
	/*
	 * Constructeur nouvelle partie
	 */
	public Game(String petControllerType, String roomControllerName, String saveName) {
		
		saveModel = new app.model.Save("res/"+saveName);
		
		petController = new Pet(petControllerType);
		roomController = new Room(roomControllerName);
		gameModel = new app.model.Game( petController.getModel(), roomController.getModel() );
		gameView = new app.view.Game( petController.getView(), roomController.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		
		init();
	}
	
	/*
	 * Constructeur charger partie
	 */
	public Game(String saveName) {
		
		saveModel = new app.model.Save("res/"+saveName);
		saveModel.load("res/"+saveName);
		petController = new Pet(saveModel.getPetType());
		roomController = new Room(saveModel.getRoomId());
		gameModel = new app.model.Game( petController.getModel(), roomController.getModel() );
		gameView = new app.view.Game( petController.getView(), roomController.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		
		petController.getHunger().setValue(saveModel.getStat("hunger"));
		petController.getThirst().setValue(saveModel.getStat("thirst"));
		petController.getWeight().setValue(saveModel.getStat("weight"));
		petController.getHygiene().setValue(saveModel.getStat("hygiene"));
		petController.getMoral().setValue(saveModel.getStat("moral"));
		
		init();
	}
	
	/*
	 * Initialisation commune entre constructeurs
	 */
	private void init() {
		saveModel.setGameInstance(gameModel);
		
		gameView.getActionBar().setActionButKitchen(gotoKitchen);
		gameView.getActionBar().setActionButBathroom(gotoBathroomController);
		gameView.getActionBar().setActionButLivingroom(gotoLivingroomController);
		gameView.getActionBar().setActionButGarden(gotoGarden);
		
		checkRoomAllowedAction();
		actionLoop.start();
	}
	
	public void save() {
		saveModel.save();
	}
	
	public void setPet(Pet new_petController) {
		petController = new_petController;
	}
	public void setRoom(Room new_roomController) {
		roomController = new_roomController;
		gameModel.setChildRoom(roomController.getModel());
		gameView.setChildRoom(roomController.getView());
	}
	
	public void updateGame() {
		
		petController.statsDecreaseOvertime();
		
		if ( petController.getHygiene().getModel().getValue() < 0.48 )
			petController.getHygiene().applyBonus(3.0, 10, "shower");
		if ( petController.getHygiene().getModel().getValue() < 0.48 )
			petController.getHygiene().applyBonus(2.0, 5, "brushing teeth");
		
		if ( isGameover() ) {
			gameView.startDrawingGameOver();
			actionLoop.stop();
		}
	}
	
	public boolean isGameover() {
		
		if ( petController.getHunger().getModel().getValue() < 0.0 
		  || petController.getThirst().getModel().getValue() < 0.0
		  || petController.getWeight().getModel().getValue() < 0.0
		  || petController.getMoral().getModel().getValue()  < 0.0 ) 
		{
			gameover.setValue(true);
			petController.setDead();
			return true;
		}
		
		return false;
	}
	
	public void checkRoomAllowedAction() {
		switch (roomController.getModel().toString()) {
			case "kitchen":
				gameView.getActionBar().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "livingroomController":
				gameView.getActionBar().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "garden":
				gameView.getActionBar().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "test":
				gameView.getActionBar().setAllowedButtons(true, true, true, true, false, true);
				break;
			default:
				break;
		}
	}
	
	public Pet getChildPet() {
		return petController;
	}
	
	public app.view.Game getView() {
		return gameView;
	}
	
	public app.model.Game getgameModel() {
		return gameModel;
	}
	
	public void exit() {
		actionLoop.stop();
		petController.exit();
		roomController.exit();
		saveModel = null;
		gameView = null;
		gameModel = null;
		petController = null;
		roomController = null;
		actionLoop = null;
	}
	
}
