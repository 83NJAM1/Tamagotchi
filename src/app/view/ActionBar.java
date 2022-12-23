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
import app.Reinstanciable;
import app.TextDisplayable;

/**
 * 
 * @author ben
 * permet de selectionner les actions du jeu et d'aller au menu
 */
public class ActionBar extends HBox implements Reinstanciable, TextDisplayable {
	 
	//########################### ATTRIBUTS #####################################
	
	// bouton affiche menu
	private Button butMenu;
	// bouton affiche/masque états
	private Button butStat;
	// bouton affiche sous menu pièces
	private Button butRoomMenu;
	
	// sous menu pièces
	private ContextMenu roomContextMenu;
	// cuisine
	private CustomMenuItem customMenuA;
	private Button butKitchen;
	// jardin
	private CustomMenuItem customMenuB;
	private Button butGarden;
	// salle de bain
	private CustomMenuItem customMenuC;
	private Button butBathroom;
	// salon
	private CustomMenuItem customMenuD;
	private Button butLivingroom;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action qui Affiche le sous menu des pièces
	 */
	private EventHandler<ActionEvent> click_room = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			roomContextMenu.show(butRoomMenu, Side.TOP, 0, 0);
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
		butRoomMenu = new Button();
		
		// instancie le sous menu pièces
		roomContextMenu = new ContextMenu();
		// instancie le contenu du sous menu
		butKitchen = new Button();
		customMenuA = new CustomMenuItem(butKitchen);
		butGarden = new Button();
		customMenuB = new CustomMenuItem(butGarden);
		butBathroom = new Button();
		customMenuC = new CustomMenuItem(butBathroom);
		butLivingroom = new Button();
		customMenuD = new CustomMenuItem(butLivingroom);
		
		//assignation action
		butRoomMenu.setOnAction(click_room); 
		
		//initalisations
		updateStyle();
		updateText();
		
		//constructions de la vue
		roomContextMenu.getItems().addAll(customMenuA, customMenuB, customMenuC, customMenuD);
		this.getChildren().addAll(butStat, butRoomMenu, butMenu);
	}
	
	/**
	 * Met à jour le style de tous les éléments
	 */
	public void updateStyle() {
		//this.setPrefHeight(92);
		butRoomMenu.setAlignment(Pos.CENTER);
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
	 * Autorise de cliquer sur les boutons suivants
	 * @param stats true si autorisé false sinon
	 * @param rooms true si autorisé false sinon
	 * @param kitchen true si autorisé false sinon
	 * @param garden true si autorisé false sinon
	 * @param bathroom true si autorisé false sinon
	 * @param menu true si autorisé false sinon
	 */
	public void setAllowedButtons(boolean stats, boolean rooms, boolean kitchen, boolean garden, boolean bathroom, boolean menu) {
		butStat.setDisable(!stats);
		butRoomMenu.setDisable(!rooms);
		butKitchen.setDisable(!kitchen);
		butGarden.setDisable(!garden);
		butBathroom.setDisable(!bathroom);
		butMenu.setDisable(!menu);
	}

	/**
	 * Autorise de cliquer sur les boutons
	 * @param list liste de booleen dans le même ordre que l'affichage des boutons
	 */
	public void setAllowedButtons(boolean ... list) {
		
		Button butlist[] = {butStat, butRoomMenu, butKitchen, butGarden, butBathroom, butMenu, butLivingroom};
		
		try {
			for ( int i = 0; i < list.length; i++) {
				butlist[i].setDisable(!list[i]);
			}
		}
		catch (Exception e) {
			System.err.println(e);
		}
	}
	
	@Override
	public void updateText() {
		butMenu.setText(App.getString("button-menu"));
		butStat.setText(App.getString("button-stats"));
		butRoomMenu.setText(App.getString("button-rooms"));
		butKitchen.setText(App.getString("button-kitchen"));
		butGarden.setText(App.getString("button-garden"));
		butBathroom.setText(App.getString("button-bathroom"));
		butLivingroom.setText(App.getString("button-livingroom"));
	}
	
	@Override
	public void exit() {
		butMenu = null;
		butStat = null;
		butRoomMenu = null;
		customMenuA = null;
		customMenuB = null;
		customMenuC = null;
		customMenuD = null;
		butKitchen = null;
		butGarden = null;
		butBathroom = null;
		butLivingroom = null;
		roomContextMenu = null;
	}
}
