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
 * view.Action est de type VBox car elle sera vu en bas de l'écran de jeu
 * Elle permet de selectionner les actions du jeu 
 */
public class Action extends HBox {
	
	private Button butMenu;
	private Button butRoomMenu;
	private CustomMenuItem butRoomA;
	private CustomMenuItem butRoomB;
	private CustomMenuItem butRoomC;
	private ContextMenu roomContextMenu;
	
	private EventHandler<ActionEvent> click_room = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//butRoomMenu.setDisable(true);
			roomContextMenu.show(butRoomMenu, Side.TOP, 0, 0);
		}
	};
	private EventHandler<ActionEvent> hide_room = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//butRoomMenu.setDisable(false);
			roomContextMenu.hide();
		}
	};
	
	public Action() {
		butMenu = new Button("Menu");
		butRoomMenu = new Button("Room");
		
		butRoomA = new CustomMenuItem(new Button("RoomA"));
		butRoomB = new CustomMenuItem(new Button("RoomB"));
		butRoomC = new CustomMenuItem(new Button("RoomC"));
		roomContextMenu = new ContextMenu();
		roomContextMenu.getItems().addAll(butRoomA, butRoomB, butRoomC);
		
		//roomContextMenu.setOnAction(hide_room);
		butRoomMenu.setOnAction(click_room);
		
		updateStyle();
		
		this.getChildren().addAll(butRoomMenu, butMenu);
		//roomContextMenu.setAnchorLocation(AnchorLocation.CONTENT_TOP_LEFT);

	}
	
	public void updateStyle() {
		this.setPrefHeight(92);
		butRoomMenu.setAlignment(Pos.CENTER);
		butMenu.setAlignment(Pos.CENTER_RIGHT);
	}
	
	public void setActionMenu(EventHandler<ActionEvent> e) {
		butMenu.setOnAction(e);
	}
	
	/*public void setActionMenu(EventHandler<ActionEvent> e) {
		butMenu.setOnAction(e);
	}*/
}
