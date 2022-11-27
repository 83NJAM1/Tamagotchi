package app.view;

import app.App;
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
	private Button butStat;
	private Button butRoomMenu;
	private CustomMenuItem customMenuA;
	private CustomMenuItem customMenuB;
	private CustomMenuItem customMenuC;
	private Button butRoomA;
	private Button butRoomB;
	private Button butRoomC;
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
		butRoomA = new Button();
		butRoomB = new Button();
		butRoomC = new Button();
		customMenuA = new CustomMenuItem(butRoomA);
		customMenuB = new CustomMenuItem(butRoomB);
		customMenuC = new CustomMenuItem(butRoomC);
		roomContextMenu = new ContextMenu();
		
		//assignation action
		butRoomMenu.setOnAction(click_room); //Affiche les bouttons rooms quand on clique sur butRoomMenu
		
		//initalisations
		updateStyle();
		updateText();
		
		//constructions de la vue
		roomContextMenu.getItems().addAll(customMenuA, customMenuB, customMenuC);
		this.getChildren().addAll(butStat, butRoomMenu, butMenu);
	}
	
	public void updateText() {
		butMenu.setText(App.language.getString("button-menu"));
		butStat.setText(App.language.getString("button-stats"));
		butRoomMenu.setText(App.language.getString("button-rooms"));
		butRoomA.setText(App.language.getString("button-kitchen"));
		butRoomB.setText(App.language.getString("button-garden"));
		butRoomC.setText(App.language.getString("button-bathroom"));
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
	
	public void setActive(boolean stats, boolean rooms, boolean kitchen, boolean garden, boolean bathroom, boolean menu) {
		butStat.setDisable(!stats);
		butRoomMenu.setDisable(!rooms);
		butRoomA.setDisable(!kitchen);
		butRoomB.setDisable(!garden);
		butRoomC.setDisable(!bathroom);
		butMenu.setDisable(!menu);
	}
}
