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

/**
 * 
 * @author ben
 * view.Action est de type HBox car elle sera vu en bas de l'écran de jeu
 * Elle permet de selectionner les actions du jeu et d'aller au menu
 */
public class Action extends HBox {
	 
	//########################### ATTRIBUTS #####################################
	
	// On est obligé de faire un menu perso car
	// le MenuBar de base n'est pas fait pour se 
	// positionner en bas de l'écrant
	private Button butMenu;
	private Button butStat;
	private Button butRoomMenu;
	private CustomMenuItem customMenuA;
	private CustomMenuItem customMenuB;
	private CustomMenuItem customMenuC;
	private CustomMenuItem customMenuD;
	private Button butKitchen;
	private Button butGarden;
	private Button butBathroom;
	private Button butLivingroom;
	private ContextMenu roomContextMenu;
	
	//######################### EVENT-ACTION ####################################
	
	private EventHandler<ActionEvent> click_room = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//butRoomMenu.setDisable(true);
			roomContextMenu.show(butRoomMenu, Side.TOP, 0, 0);
		}
	};
	
	/*private EventHandler<ActionEvent> hide_room = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//butRoomMenu.setDisable(false);
			roomContextMenu.hide();
		}
	};*/
	
	//############################ METHODES #####################################
	
	public Action() {
		
		//instanciations
		butMenu = new Button();
		butStat = new Button();
		butRoomMenu = new Button();
		butKitchen = new Button();
		butGarden = new Button();
		butBathroom = new Button();
		butLivingroom = new Button();
		customMenuA = new CustomMenuItem(butKitchen);
		customMenuB = new CustomMenuItem(butGarden);
		customMenuC = new CustomMenuItem(butBathroom);
		customMenuD = new CustomMenuItem(butLivingroom);
		roomContextMenu = new ContextMenu();
		
		//assignation action
		butRoomMenu.setOnAction(click_room); //Affiche les bouttons rooms quand on clique sur butRoomMenu
		
		//initalisations
		updateStyle();
		updateText();
		
		//constructions de la vue
		roomContextMenu.getItems().addAll(customMenuA, customMenuB, customMenuC, customMenuD);
		this.getChildren().addAll(butStat, butRoomMenu, butMenu);
	}
	
	public void updateText() {
		butMenu.setText(App.getString("button-menu"));
		butStat.setText(App.getString("button-stats"));
		butRoomMenu.setText(App.getString("button-rooms"));
		butKitchen.setText(App.getString("button-kitchen"));
		butGarden.setText(App.getString("button-garden"));
		butBathroom.setText(App.getString("button-bathroom"));
		butLivingroom.setText(App.getString("button-livingroom"));
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
	 * Utilisé par v.Main car alterne les vues game et menu
	 * @param e ActionEvent qui doit être déclencher par le bouton butMenu
	 */
	public void setActionMenu(EventHandler<ActionEvent> e) {
		butMenu.setOnAction(e);
	}
	
	/**
	 * Utilisé par v.Hud pour afficher masquer les stats
	 * @param e ActionEvent qui doit être déclencher par le bouton butStat
	 */
	public void setActionStat(EventHandler<ActionEvent> e) {
		butStat.setOnAction(e);
	}
	
	/**
	 * change de salle
	 * @param e ActionEvent qui doit être déclencher par le bouton butRoomA
	 */
	public void setActionButKitchen(EventHandler<ActionEvent> e) {
		butKitchen.setOnAction(e);
	}
	
	/**
	 * change de salle
	 * @param e ActionEvent qui doit être déclencher par le bouton butGarden
	 */
	public void setActionButGarden(EventHandler<ActionEvent> e) {
		butGarden.setOnAction(e);
	}
	
	/**
	 * change de salle
	 * @param e ActionEvent qui doit être déclencher par le bouton butBathroom
	 */
	public void setActionButBathroom(EventHandler<ActionEvent> e) {
		butBathroom.setOnAction(e);
	}
	
	/**
	 * change de salle
	 * @param e ActionEvent qui doit être déclencher par le bouton butLivingroom
	 */
	public void setActionButLivingroom(EventHandler<ActionEvent> e) {
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
	 * Autorise de cliquer sur les boutons suivants
	 * @param list liste de booleen
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
