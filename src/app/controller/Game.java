package app.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.io.File;
import java.util.Date;
import java.util.Random;

import app.App;
import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * permet de faire intéragire l'ensemble du jeu avec les actions à associer
 */
public class Game implements Cleanable, Localisable {
	
	//########################### ATTRIBUTS #####################################
	
	public static final String SAVEPATH = Main.USERPATH+"saves/"; 
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
	 * répété tout les x temps, avec x le tickrate
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
	 * répété tout les x temps, avec x la durée météo
	 * déclendeur -> this
	 */ 
	private AnimationTimer weatherLoop = new AnimationTimer() {
		
		long old_time=0;
		final long DELAY_MIN = 1_000_000_000;
		final long DELAY_MAX = DELAY_MIN*120; // NOTE 1 minute maximum
		final Random DELAY_SEED = new Random();
		
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+DELAY_SEED.nextLong(DELAY_MIN, DELAY_MAX);
				applyWeather(gameModel.getWeather(), (old_time-new_time)/1_000_000_000);
				
				// affiche prochain climat
				//System.out.println("the weather is " + gameModel.getWeather() + " for " + (old_time-new_time)/1_000_000_000 + " seconds");
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
			miniGameController = new MiniGame("throw-and-fetch", petController);
			
			// initialise le jeu
			gameModel.setMiniGame(miniGameController.getModel());
			gameView.getViewHud().setActionBarMiniGame(miniGameController.getView());
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
			gameView.getViewHud().removeActionBarMiniGame();
			petController.getModel().tooglePlaying();
		}
	};
	
	//############################ METHODES #####################################
	
	/*
	 * Constructeur nouvelle partie
	 */
	public Game(String petType, String roomName, String saveName) {
		
		checkPath();
		
		saveModel = new app.model.Save(SAVEPATH+saveName);
		
		petController = new Pet(petType);
		roomController = new Room(roomName);
		
		startInit();
		endInit();
	}
	
	/*
	 * Constructeur charger partie
	 */
	public Game(String saveName) {
		
		checkPath();
		
		saveModel = new app.model.Save(SAVEPATH+saveName);
		
		saveModel.load(SAVEPATH+saveName);
		petController = new Pet(saveModel.getPetType());
		roomController = new Room(saveModel.getRoomId());
		
		startInit();
		
		Date date = new Date();
		
		double difftime = ( ( date.getTime() - saveModel.getDate().getTime() ) / 1000.0 ) / 60 / 60 / 24 / 2;
		
		petController.getControllerHunger().setValue(saveModel.getState("hunger")   - difftime);
		petController.getControllerThirst().setValue(saveModel.getState("thirst")   - difftime);
		petController.getControllerWeight().setValue(saveModel.getState("weight")   - difftime);
		petController.getControllerHygiene().setValue(saveModel.getState("hygiene") - difftime);
		petController.getControllerMoral().setValue(saveModel.getState("moral")     - difftime);
		petController.getModel().getHealth().setValue(saveModel.getState("health")  - difftime);
		petController.getModel().getEnergy().setValue(saveModel.getState("energy")  - difftime);
		
		endInit();
	}
	
	private void checkPath() {
		
		File saveDir = new File(SAVEPATH);
		
		if ( !saveDir.exists() ) {
			saveDir.mkdir();
		}
	}
	
	/*
	 * Initialisation commune entre constructeurs
	 */
	private void startInit() {
		gameModel = new app.model.Game( petController.getModel(), roomController.getModel() );
		gameView = new app.view.Game( petController.getView(), roomController.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		cookControler = new Cook(new app.model.Cook(),gameView.getViewCook());
		cookControler.getView().setActionShare(makePetEating);
	}
	
	/*
	 * Initialisation commune entre constructeurs
	 */
	private void endInit() {
		
		saveModel.setGameInstance(gameModel);
		
		gameView.getViewHud().getViewAction().setActionButtonKitchen(gotoKitchen);
		gameView.getViewHud().getViewAction().setActionButtonBathroom(gotoBathroom);
		gameView.getViewHud().getViewAction().setActionButtonLivingroom(gotoLivingroom);
		gameView.getViewHud().getViewAction().setActionButtonBedroom(gotoBedroom);
		gameView.getViewHud().getViewAction().setActionButtonGarden(gotoGarden);
		
		gameView.getViewHud().getViewAction().setActionButtonDrink(makePetDrinking);
		gameView.getViewHud().getViewAction().setActionButtonEat(makePetEating);
		gameView.getViewHud().getViewAction().setActionButtonTakeShower(makePetTakingShower);
		gameView.getViewHud().getViewAction().setActionButtonCook(makePetCook);
		gameView.getViewHud().getViewAction().setActionButtonPlay(makePetPlaying);

		gameLoop.start();
		weatherLoop.start();
		
		//applyWeather("rainy");
		petController.saveOrigin();
		updateViewWeather();
		updateViewAllowedAction();
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
			gameView.setViewRoom( roomController.getView() );
		}
		
		
		
		petController.saveOrigin();
		updatePetVisibility();
		updateViewWeather();
		updateViewAllowedAction();
	}
	
	public void updatePetVisibility() {
		if ( petController.getModel().isSleeping() && !roomController.getModel().equals(app.model.Bedroom.getInstance()) ) {
			petController.getView().setVisible(false);
		}
		else {
			petController.getView().setVisible(true);
		}
	}
	
    public void applyWeather(String name, long time) {
		gameView.setWeather( gameModel.getWeather() );
		gameModel.nextWeather();
		System.out.println("the weather is " + name + " for " + time + " seconds");
    }
	
	public void updateViewWeather() {
		
		if ( roomController.getModel().equals(app.model.Garden.getInstance())) {
			gameView.setWeatherTo(true);
		}
		else {
			gameView.setWeatherTo(false);
		}
	}
	/**
	 * met a jour les données et la vue du jeu
	 */
	public void updateGame() {
		
		
		
		if ( gameModel.nextStep() || App.DEBUG ) {

			// met a jour toutes les vues dont les models sont modifiés sans action
			if ( miniGameController != null )
				miniGameController.updateView();
			
			updatePetVisibility();
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
		
		gameView.getViewHud().getViewAction().setAllowedRoom (
				
			roomController.getModel().isAdjacent(app.model.Kitchen.getInstance()),
			roomController.getModel().isAdjacent(app.model.Garden.getInstance()),
			roomController.getModel().isAdjacent(app.model.Bathroom.getInstance()),
			roomController.getModel().isAdjacent(app.model.Livingroom.getInstance()),
			roomController.getModel().isAdjacent(app.model.Bedroom.getInstance())
		);
		
		gameView.getViewHud().getViewAction().setAllowedInteraction (
				
		    roomController.getModel().equals(app.model.Kitchen.getInstance()) ||
			roomController.getModel().equals(app.model.Bathroom.getInstance()),
		    roomController.getModel().equals(app.model.Kitchen.getInstance()), 
		    roomController.getModel().equals(app.model.Bathroom.getInstance()), 
		    roomController.getModel().equals(app.model.Garden.getInstance())
		);
		
		gameView.getViewHud().getViewAction().enableCooking(roomController.getModel().equals(app.model.Kitchen.getInstance()));

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
	public void clean() {
		weatherLoop.stop();
		weatherLoop = null;
		
		gameLoop.stop();
		gameLoop = null;
		
		petController.clean();
		petController = null;
		
		roomController.clean();
		roomController = null;
		
		gameView.clean();
		gameView = null;
		
		gameModel.clean();
		gameModel = null;
		
		saveModel = null;
	}	
}
