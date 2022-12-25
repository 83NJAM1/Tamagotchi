package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ActionBarThrowStick extends HBox {

	// bouton affiche menu
	private Button butThrow;
	// bouton affiche/masque états
	private Button butStop;
	
	public ActionBarThrowStick() {
		butThrow = new Button("button-throw");
		butStop = new Button("button-stop");
		
		this.getChildren().addAll(butThrow, butStop);
	}
	
	/**
	 * Utilisé par v.Main
	 * @param e ce qui doit être déclencher par le bouton butMenu
	 */
	public void setActionButtonThrow(EventHandler<ActionEvent> e) {
		butThrow.setOnAction(e);
	}
	
	/**
	 * Utilisé par v.Hud
	 * @param e ce qui doit être déclencher par le bouton butStat
	 */
	public void setActionButtonStop(EventHandler<ActionEvent> e) {
		butStop.setOnAction(e);
	}
}
