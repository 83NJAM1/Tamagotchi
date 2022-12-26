package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ActionBarThrowAndFetch extends MiniGameActionBar {

	// bouton affiche menu
	private Button butThrow;
	
	public ActionBarThrowAndFetch() {
		butThrow = new Button("button-throw");
		this.getChildren().addAll(butThrow);
	}
	
	/**
	 * Utilisé par v.Main
	 * @param e ce qui doit être déclencher par le bouton butMenu
	 */
	public void setActionButtonThrow(EventHandler<ActionEvent> e) {
		butThrow.setOnAction(e);
	}
}
