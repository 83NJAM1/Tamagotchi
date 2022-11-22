package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.PopupWindow.AnchorLocation;

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
	private Button butRoomMenu;
	private CustomMenuItem butRoomA;
	private CustomMenuItem butRoomB;
	private CustomMenuItem butRoomC;
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
		butMenu = new Button("Menu");
		butRoomMenu = new Button("Room");
		butRoomA = new CustomMenuItem(new Button("RoomA"));
		butRoomB = new CustomMenuItem(new Button("RoomB"));
		butRoomC = new CustomMenuItem(new Button("RoomC"));
		roomContextMenu = new ContextMenu();
		
		//assignation action
		butRoomMenu.setOnAction(click_room); //Affiche les bouttons rooms quand on clique sur butRoomMenu
		
		//initalisations
		updateStyle();
		
		//constructions de la vue
		roomContextMenu.getItems().addAll(butRoomA, butRoomB, butRoomC);
		this.getChildren().addAll(butRoomMenu, butMenu);
	}
	
	/**
	 * Met à jour le style de tous les éléments
	 */
	public void updateStyle() {
		this.setPrefHeight(92);
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
}
