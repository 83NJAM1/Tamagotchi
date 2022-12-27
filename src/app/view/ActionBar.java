package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.layout.HBox;

import app.App;
import app.Componable;
import app.Localisable;

/**
 * 
 * @author ben
 * permet de selectionner les actions du jeu et d'aller au menu
 */
public class ActionBar extends HBox implements Componable, Localisable {
	 
	//########################### ATTRIBUTS #####################################
	
	// bouton affiche menu
	private Button butMenu;
	// bouton affiche/masque états
	private Button butStat;
	// bouton affiche sous menu pièces
	private Button butMenuRoom;
	// bouton affiche sous menu action du pet
	private Button butMenuPetAction;
	// bouton cuisiner
	private Button butCook;
		
	// sous menu pièces
	private ContextMenu roomContextMenu;
	// cuisine
	private CustomMenuItem customMenuRoomA;
	private Button butKitchen;
	// jardin
	private CustomMenuItem customMenuRoomB;
	private Button butGarden;
	// salle de bain
	private CustomMenuItem customMenuRoomC;
	private Button butBathroom;
	// salon
	private CustomMenuItem customMenuRoomD;
	private Button butLivingroom;
	// salon
	private CustomMenuItem customMenuRoomE;
	private Button butBedroom;
	
	// sous menu action pet
	private ContextMenu petActionContextMenu;
	// boire
	private CustomMenuItem customMenuActionDrink;
	private Button butDrink;
	// manger
	private CustomMenuItem customMenuActionEat;
	private Button butEat;
	// doucher
	private CustomMenuItem customMenuActionTakeShower;
	private Button butTakeShower;
	// jouer
	private CustomMenuItem customMenuActionPlay;
	private Button butPlay;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action qui Affiche le sous menu des pièces
	 */
	private EventHandler<ActionEvent> click_room = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			roomContextMenu.show(butMenuRoom, Side.TOP, 0, 0);
		}
	};
	
	/**
	 * Action qui Affiche le sous menu des pièces
	 */
	private EventHandler<ActionEvent> click_petaction = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			petActionContextMenu.show(butMenuPetAction, Side.TOP, 0, 0);
		}
	};
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public ActionBar() {
		
		// instancie les boutons principaux
		butMenu = new Button();
		butStat = new Button();
		butMenuRoom = new Button();
		butMenuPetAction = new Button();
		butCook = new Button();
		
		// instancie le sous menu pièces
		roomContextMenu = new ContextMenu();
		// instancie le contenu du sous menu
		butKitchen = new Button();
		customMenuRoomA = new CustomMenuItem(butKitchen);
		butGarden = new Button();
		customMenuRoomB = new CustomMenuItem(butGarden);
		butBathroom = new Button();
		customMenuRoomC = new CustomMenuItem(butBathroom);
		butLivingroom = new Button();
		customMenuRoomD = new CustomMenuItem(butLivingroom);
		butBedroom = new Button();
		customMenuRoomE = new CustomMenuItem(butBedroom);
		
		// instancie le sous menu pièces
		petActionContextMenu = new ContextMenu();
		// instancie le contenu du sous menu
		butDrink = new Button();
		customMenuActionDrink = new CustomMenuItem(butDrink);
		butEat = new Button();
		customMenuActionEat = new CustomMenuItem(butEat);
		butTakeShower = new Button();
		customMenuActionTakeShower = new CustomMenuItem(butTakeShower);
		butPlay = new Button();
		customMenuActionPlay = new CustomMenuItem(butPlay);
		
		//assignation action
		butMenuRoom.setOnAction(click_room); 
		butMenuPetAction.setOnAction(click_petaction);
		
		//initalisations
		updateStyle();
		updateText();
		
		//constructions de la vue
		roomContextMenu.getItems().addAll(customMenuRoomA, customMenuRoomB, customMenuRoomC, customMenuRoomD, customMenuRoomE);
		petActionContextMenu.getItems().addAll(customMenuActionDrink, customMenuActionEat, customMenuActionTakeShower, customMenuActionPlay);
		this.getChildren().addAll(butMenu, butStat, butMenuRoom, butMenuPetAction, butCook);
	}
	
	/**
	 * Met à jour le style de tous les éléments
	 */
	public void updateStyle() {
		//this.setPrefHeight(92);
		butMenuRoom.setAlignment(Pos.CENTER);
		butMenu.setAlignment(Pos.CENTER_RIGHT);		
	}
	
	/**
	 * Utilisé par v.Main
	 * @param e ce qui doit être déclencher par le bouton butMenu
	 */
	public void setActionButtonMenu(EventHandler<ActionEvent> e) {
		butMenu.setOnAction(e);
	}
	
	/**
	 * Utilisé par v.Hud
	 * @param e ce qui doit être déclencher par le bouton butStat
	 */
	public void setActionButtonStat(EventHandler<ActionEvent> e) {
		butStat.setOnAction(e);
	}
	
	/**
	 * utilisé par c.Game
	 * @param e ce qui doit être déclencher par le bouton butRoomA
	 */
	public void setActionButtonKitchen(EventHandler<ActionEvent> e) {
		butKitchen.setOnAction(e);
	}
	
	/**
	 * utilisé par c.Game
	 * @param e ce qui doit être déclencher par le bouton butGarden
	 */
	public void setActionButtonGarden(EventHandler<ActionEvent> e) {
		butGarden.setOnAction(e);
	}
	
	/**
	 * utilisé par c.Game
	 * @param e ce qui doit être déclencher par le bouton butBathroom
	 */
	public void setActionButtonBathroom(EventHandler<ActionEvent> e) {
		butBathroom.setOnAction(e);
	}
	
	/**
	 * utilisé par c.Game
	 * @param e ce qui doit être déclencher par le bouton butLivingroom
	 */
	public void setActionButtonLivingroom(EventHandler<ActionEvent> e) {
		butLivingroom.setOnAction(e);
	}
	
	/**
	 * utilisé par c.Game
	 * @param e ce qui doit être déclencher par le bouton butBedroom
	 */
	public void setActionButtonBedroom(EventHandler<ActionEvent> e) {
		butBedroom.setOnAction(e);
	}
	
	/**
	 * définit l'action pour le bouton boire
	 * @param e ce qui doit être déclencher par le bouton butDrink
	 */
	public void setActionButtonDrink(EventHandler<ActionEvent> e) {
		butDrink.setOnAction(e);
	}
	/**
	 * définit l'action pour le bouton manger
	 * @param e ce qui doit être déclencher par le bouton butEat
	 */
	public void setActionButtonEat(EventHandler<ActionEvent> e) {
		butEat.setOnAction(e);
	}
	/**
	 * définit l'action pour le bouton prendre une douche
	 * @param e ce qui doit être déclencher par le bouton butTakeShower
	 */
	public void setActionButtonTakeShower(EventHandler<ActionEvent> e) {
		butTakeShower.setOnAction(e);
	}
	/**
	 * définit l'action pour le bouton prendre une douche
	 * @param e ce qui doit être déclencher par le bouton butTakeShower
	 */
	public void setActionButtonCook(EventHandler<ActionEvent> e) {
		butCook.setOnAction(e);
	}
	/**
	 * définit l'action pour le bouton jouer avec pet
	 * @param e ce qui doit être déclencher par le bouton butPlay
	 */
	public void setActionButtonPlay(EventHandler<ActionEvent> e) {
		butPlay.setOnAction(e);
	}
	
	public void enableCooking(boolean canCook) {
		butCook.setDisable(!canCook);
	}
	
	public void setAllowedMainAction(boolean canToogleStates, boolean canChangeRoom, boolean canPetInterection, boolean canShowMenu) {
		
		butStat.setDisable(!canToogleStates);
		butMenuRoom.setDisable(!canChangeRoom);
		if ( !canChangeRoom )
			roomContextMenu.hide();
		butMenuPetAction.setDisable(!canPetInterection);
		if (!canPetInterection )
			petActionContextMenu.hide();
		butMenu.setDisable(!canShowMenu);
	}
	
	public void setAllowedRoom(boolean canGotoKitchen,  boolean canGotoGarden, boolean canGotoBthroom, 
							   boolean canGotoLivingroom, boolean canGotoBedroom) {
		
		butKitchen.setDisable(!canGotoKitchen);
		butGarden.setDisable(!canGotoGarden);
		butBathroom.setDisable(!canGotoBthroom);
		butLivingroom.setDisable(!canGotoLivingroom);
		butBedroom.setDisable(!canGotoBedroom);
	}
	
	public void setAllowedInteraction( boolean canDrink,  boolean canEat, boolean canTakeShower, boolean canPlay ) {

		butDrink.setDisable(!canDrink);
		butEat.setDisable(!canEat);
		butTakeShower.setDisable(!canTakeShower);
		butPlay.setDisable(!canPlay);
	}
	
	@Override
	public void updateText() {
		butMenu.setText(App.getString("button-menu"));
		butStat.setText(App.getString("button-stats"));
		butMenuRoom.setText(App.getString("button-rooms"));
		butKitchen.setText(App.getString("button-kitchen"));
		butGarden.setText(App.getString("button-garden"));
		butBathroom.setText(App.getString("button-bathroom"));
		butLivingroom.setText(App.getString("button-livingroom"));
		butBedroom.setText(App.getString("button-bedroom"));
		butMenuPetAction.setText(App.getString("button-petaction"));
		butDrink.setText(App.getString("button-drink"));
		butTakeShower.setText(App.getString("button-takeshower"));
		butCook.setText(App.getString("button-cook"));
		butEat.setText(App.getString("button-eat"));
		butPlay.setText(App.getString("button-play"));
	}
	
	@Override
	public void exit() {
		butMenu = null;
		butStat = null;
		butMenuRoom = null;
		customMenuRoomA = null;
		customMenuRoomB = null;
		customMenuRoomC = null;
		customMenuRoomD = null;
		butKitchen = null;
		butGarden = null;
		butBathroom = null;
		butLivingroom = null;
		butBedroom = null;
		roomContextMenu = null;
	}
}
