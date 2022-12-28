package app.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.Random;

import app.Componable;
import app.Localisable;

/**
 * 
 * @author ben
 * permet de faire intéragire l'ensemble du jeu avec les actions à associer
 */
public class Game implements Componable, Localisable {
	
	//########################### ATTRIBUTS #####################################
	
	public static final String SAVEPATH = "res/saves/"; 
	public static final String GAMEIMAGEPATH = "res/game/images/"; 
	
	// données du jeu
	private app.model.Game gameModel;
	
	// interface utilisateur du jeu
	private app.view.Game gameView; //NOTE: reference partagé avec view.Main
	
	// le controller du pet à manipuler
	private Pet petController;
	
	// le controller de la pièce active
	private Room roomController;
	
	// le controller du mini jeu cuisiner
	private Cook cookControler;
		
	// le controller du mini jeu
	private MiniGame miniGameController;
		
	// données permanante du jeu
	private app.model.Save saveModel;
	 
	// déclencheur d'évenement lors d'un gameover
	public BooleanProperty gameover; //NOTE: lié à app.controller.Main 
	
	//######################### EVENT-ACTION ####################################

	/**
	 * gameLoop répété tout les x temps, avec x le tickrate
	 * déclendeur -> this
	 */ 
	private AnimationTimer gameLoop = new AnimationTimer() {
		
		long old_time=0;
		long tickrate=1_000_000_000;
		
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+tickrate;
				updateGame();
			}
        }
    };
    
	/**
	 * effectué pour aller dans la cuisine( kitchen )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoKitchen = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setControllerRoom(new Room("kitchen"));
		}
	};
	
	/**
	 * effectué pour aller dans le jardin ( garden )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoGarden = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setControllerRoom(new Room("garden"));
		}
	};
	
	/**
	 * effectué pour aller dans la salle de bain ( bathroom )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoBathroom = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setControllerRoom(new Room("bathroom"));
		}
	};
	
	/**
	 * effectué pour aller dans le salon ( livingroom )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoLivingroom = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setControllerRoom(new Room("livingroom"));
		}
	};
	
	/**
	 * effectué pour aller dans la chambre ( bedroom )
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> gotoBedroom = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			setControllerRoom(new Room("bedroom"));
		}
	};
	
	/**
	 * effectué pour faire boire le pet
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> makePetDrinking = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			petController.getModel().toogleDrinking();
		}
	};
	
	/**
	 * effectué pour faire manger le pet
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> makePetEating = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			petController.getModel().toogleEating();
		}
	};
	
	/**
	 * effectué pour faire prendre une douche au pet
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> makePetTakingShower = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			petController.getModel().toogleTakingShower();
		}
	};
	
	/**
	 * effectué pour cuisiner
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> makePetCook = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			cookControler.start();
		}
	};
	
	/**
	 * effectué pour faire jouer le pet
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> makePetPlaying = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			
			// instancie le jeu
			miniGameController = new MiniGame("throw-stick", petController);
			
			// initialise le jeu
			gameModel.setMiniGame(miniGameController.getModel());
			gameView.getChildHud().setActionBarMiniGame(miniGameController.getView());
			petController.getModel().tooglePlaying();
			
			// associe les actions pour le jeu
			miniGameController.setActionThrow(clickThrow);
			miniGameController.getView().setActionButtonStop(clickStop);
		}
	};
	
	/**
	 * effectué pour faire boire le pet
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> clickThrow = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			miniGameController.actionThrow();
		}
	};
	
	/**
	 * effectué pour faire manger le pet
	 * déclendeur -> v.Action
	 */ 
	private EventHandler<ActionEvent> clickStop = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			gameModel.setMiniGame(null);
			gameView.getChildHud().removeActionBarMiniGame();
			petController.getModel().tooglePlaying();
		}
	};
	
	//############################ METHODES #####################################
	
	/*
	 * Constructeur nouvelle partie
	 */
	public Game(String petType, String roomName, String saveName) {
		
		saveModel = new app.model.Save(SAVEPATH+saveName);
		
		petController = new Pet(petType);
		roomController = new Room(roomName);
		gameModel = new app.model.Game( petController.getModel(), roomController.getModel() );
		gameView = new app.view.Game( petController.getView(), roomController.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		cookControler = new Cook(new app.model.Cook(),gameView.getCookView());

		init();
	}
	
	/*
	 * Constructeur charger partie
	 */
	public Game(String saveName) {
		
		saveModel = new app.model.Save(SAVEPATH+saveName);
		saveModel.load(SAVEPATH+saveName);
		petController = new Pet(saveModel.getPetType());
		roomController = new Room(saveModel.getRoomId());
		gameModel = new app.model.Game( petController.getModel(), roomController.getModel() );
		gameView = new app.view.Game( petController.getView(), roomController.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		cookControler = new Cook(new app.model.Cook(),gameView.getCookView());
		
		petController.getControllerHunger().setValue(saveModel.getState("hunger"));
		petController.getControllerThirst().setValue(saveModel.getState("thirst"));
		petController.getControllerWeight().setValue(saveModel.getState("weight"));
		petController.getControllerHygiene().setValue(saveModel.getState("hygiene"));
		petController.getControllerMoral().setValue(saveModel.getState("moral"));
		
		init();
	}
	
	/*
	 * Initialisation commune entre constructeurs
	 */
	private void init() {
		saveModel.setGameInstance(gameModel);
		
		gameView.getChildHud().getChildAction().setActionButtonKitchen(gotoKitchen);
		gameView.getChildHud().getChildAction().setActionButtonBathroom(gotoBathroom);
		gameView.getChildHud().getChildAction().setActionButtonLivingroom(gotoLivingroom);
		gameView.getChildHud().getChildAction().setActionButtonBedroom(gotoBedroom);
		gameView.getChildHud().getChildAction().setActionButtonGarden(gotoGarden);
		
		gameView.getChildHud().getChildAction().setActionButtonDrink(makePetDrinking);
		gameView.getChildHud().getChildAction().setActionButtonEat(makePetEating);
		gameView.getChildHud().getChildAction().setActionButtonTakeShower(makePetTakingShower);
		gameView.getChildHud().getChildAction().setActionButtonCook(makePetCook);
		gameView.getChildHud().getChildAction().setActionButtonPlay(makePetPlaying);
		
		updateViewAllowedAction();
		gameLoop.start();
	}
	
	/**
	 * sauvegarde les données du jeu via le model Save
	 */
	public void save() {
		saveModel.save();
	}
	
	/**
	 * change le pet courant
	 * @param newPetController le nouveau pet pour le jeu
	 */
	public void setControllerPet(Pet newPetController) {
		petController = newPetController;
	}
	
	/**
	 * change la pièce courante
	 * @param newRoomController la nouvelle pièce pour le jeu
	 */
	public void setControllerRoom(Room newRoomController) {
		
		if ( gameModel.setCurrentModelRoom( newRoomController.getModel() ) ) {
			roomController = newRoomController;
			gameView.setChildRoom( roomController.getView() );
		}
		
		updateViewAllowedAction();
	}
	
	/**
	 * met a jour les données et la vue du jeu
	 */
	public void updateGame() {
		
		if ( gameModel.nextStep() ) {
			
			// met a jour toutes les vues dont les models sont modifiés sans action
			petController.updateView();
		}
		else {
			
			// le jeu est perdu
			gameover.setValue(true);
			gameView.startDrawingGameOver();
			gameLoop.stop();
			petController.updateView();
		}
	}
		
	/**
	 * met a jour la vue des actions authorisées
	 */
	public void updateViewAllowedAction() {
		
		gameView.getChildHud().getChildAction().setAllowedRoom (
				
			roomController.getModel().isAdjacent(app.model.Kitchen.getInstance()),
			roomController.getModel().isAdjacent(app.model.Garden.getInstance()),
			roomController.getModel().isAdjacent(app.model.Bathroom.getInstance()),
			roomController.getModel().isAdjacent(app.model.Livingroom.getInstance()),
			roomController.getModel().isAdjacent(app.model.Bedroom.getInstance())
		);
		
		gameView.getChildHud().getChildAction().setAllowedInteraction (
				
		    roomController.getModel().equals(app.model.Kitchen.getInstance()) ||
			roomController.getModel().equals(app.model.Bathroom.getInstance()),
		    roomController.getModel().equals(app.model.Kitchen.getInstance()), 
		    roomController.getModel().equals(app.model.Bathroom.getInstance()), 
		    roomController.getModel().equals(app.model.Garden.getInstance())
		);
		
		gameView.getChildHud().getChildAction().enableCooking(roomController.getModel().equals(app.model.Kitchen.getInstance()));

	}
	
	/**
	 * obtient le controller enfant pet
	 * @return Pet, le controller
	 */
	public Pet getControllerPet() {
		return petController;
	}
	
	/**
	 * obtient la vue du jeu
	 * @return Game la vue
	 */
	public app.view.Game getView() {
		return gameView;
	}
	
	/**
	 * obtient le model du jeu
	 * @return Game le model
	 */
	public app.model.Game getModel() {
		return gameModel;
	}
	
	@Override
	public void updateText() {
		petController.updateText();
		gameView.updateText();
	}
	
	@Override
	public void exit() {
		gameLoop.stop();
		gameLoop = null;
		
		petController.exit();
		petController = null;
		
		roomController.exit();
		roomController = null;
		
		gameView.exit();
		gameView = null;
		
		gameModel.exit();
		gameModel = null;
		
		saveModel = null;
	}	
}
