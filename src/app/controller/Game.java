package app.controller;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
 
import app.Reinstanciable;
import app.TextDisplayable;

/**
 * 
 * @author ben
 * permet de faire intéragire l'ensemble du jeu avec les actions à associer
 */
public class Game implements Reinstanciable, TextDisplayable {
	
	//########################### ATTRIBUTS #####################################
	
	// données du jeu
	private app.model.Game gameModel;
	
	// interface utilisateur du jeu
	private app.view.Game gameView; //NOTE: reference partagé avec view.Main
	
	// le controller du pet à manipuler
	private Pet petController;
	
	// le controller de la pièce active
	private Room roomController;
	
	// données permanante du jeu
	private app.model.Save saveModel;
	 
	// déclencheur d'évenement lors d'un gomeover
	public BooleanProperty gameover; //NOTE: lié à app.controller.Main 
	
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
		
		petController.getChildHunger().setValue(saveModel.getStat("hunger"));
		petController.getChildThirst().setValue(saveModel.getStat("thirst"));
		petController.getChildWeight().setValue(saveModel.getStat("weight"));
		petController.getChildHygiene().setValue(saveModel.getStat("hygiene"));
		petController.getChildMoral().setValue(saveModel.getStat("moral"));
		
		init();
	}
	
	/*
	 * Initialisation commune entre constructeurs
	 */
	private void init() {
		saveModel.setGameInstance(gameModel);
		
		gameView.getChildHud().getChildAction().setActionButtonKitchen(gotoKitchen);
		gameView.getChildHud().getChildAction().setActionButtonBathroom(gotoBathroomController);
		gameView.getChildHud().getChildAction().setActionButtonLivingroom(gotoLivingroomController);
		gameView.getChildHud().getChildAction().setActionButtonGarden(gotoGarden);
		
		checkRoomAllowedAction();
		actionLoop.start();
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
	public void setPet(Pet newPetController) {
		petController = newPetController;
	}
	
	/**
	 * change la pièce courante
	 * @param newRoomController la nouvelle pièce pour le jeu
	 */
	public void setRoom(Room newRoomController) {
		roomController = newRoomController;
		gameModel.setChildRoom(roomController.getModel());
		gameView.setChildRoom(roomController.getView());
	}
	
	/**
	 * met a jour les données du jeu
	 */
	public void updateGame() {
		
		petController.descreaseStatesValue();
		
		if ( petController.getChildHygiene().getModel().getValue() < 0.48 )
			petController.getChildHygiene().applyBonus(3.0, 10, "shower");
		if ( petController.getChildHygiene().getModel().getValue() < 0.48 )
			petController.getChildHygiene().applyBonus(2.0, 5, "brushing teeth");
		
		if ( isGameover() ) {
			gameView.startDrawingGameOver();
			actionLoop.stop();
		}
	}
	
	/**
	 * vérifie si la partie est perdu ou non
	 * @return vrai si partie perdu
	 */
	public boolean isGameover() {
		
		if ( petController.getChildHunger().getModel().getValue() < 0.0 
		  || petController.getChildThirst().getModel().getValue() < 0.0
		  || petController.getChildWeight().getModel().getValue() < 0.0
		  || petController.getChildMoral().getModel().getValue()  < 0.0 ) 
		{
			gameover.setValue(true);
			petController.setDead();
			return true;
		}
		
		return false;
	}
	
	/**
	 * définit les action authorisé dans chaque pièce
	 */
	public void checkRoomAllowedAction() {
		switch (roomController.getModel().toString()) {
			case "kitchen":
				gameView.getChildHud().getChildAction().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "livingroomController":
				gameView.getChildHud().getChildAction().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "garden":
				gameView.getChildHud().getChildAction().setAllowedButtons(true, true, false, false, true, true);
				break;
			case "test":
				gameView.getChildHud().getChildAction().setAllowedButtons(true, true, true, true, false, true);
				break;
			default:
				break;
		}
	}
	
	/**
	 * obtient le controller enfant pet
	 * @return Pet, le controller
	 */
	public Pet getChildPet() {
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
		actionLoop.stop();
		actionLoop = null;
		
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
